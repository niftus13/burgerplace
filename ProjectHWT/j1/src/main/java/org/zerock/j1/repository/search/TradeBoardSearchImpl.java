package org.zerock.j1.repository.search;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.j1.domain.QTradeBoard;
import org.zerock.j1.domain.QTradeReply;
import org.zerock.j1.domain.TradeBoard;
import org.zerock.j1.dto.PageRequestDTO;
import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.dto.TradeBoardListRcntDTO;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class TradeBoardSearchImpl extends QuerydslRepositorySupport implements TradeBoardSearch {

    public TradeBoardSearchImpl() {
        super(TradeBoard.class);
    }

    @Override
    public Page<TradeBoard> search1(String searchType, String keyword, Pageable pageable) {

        QTradeBoard tBoard = QTradeBoard.tradeBoard;

        JPQLQuery<TradeBoard> query = from(tBoard);

        if (keyword != null && searchType != null) {
            // tc => [t,c]
            String[] searchArr = searchType.split("");
            // BooleanBuilder 생성
            BooleanBuilder searchBuilder = new BooleanBuilder();

            for (String type : searchArr) {

                switch (type) {
                    case "t" -> searchBuilder.or(tBoard.tTitle.contains(keyword));
                    case "c" -> searchBuilder.or(tBoard.tContent.contains(keyword));
                    case "w" -> searchBuilder.or(tBoard.nickname.contains(keyword));
                }

            } // end for
              // for문 끝난후 where 로 searchBuilder 추가
            query.where(searchBuilder);
        }

        this.getQuerydsl().applyPagination(pageable, query);
        // list를 가져오는 방법
        List<TradeBoard> list = query.fetch();
        long count = query.fetchCount();

        log.info(list);
        log.info("count: " + count);

        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public Page<Object[]> searchWithRcnt(String searchType, String keyword, Pageable pageable) {

        QTradeBoard tBoard = QTradeBoard.tradeBoard;
        QTradeReply tReply = QTradeReply.tradeReply;

        JPQLQuery<TradeBoard> query = from(tBoard);

        query.leftJoin(tReply).on(tReply.tradeBoard.eq(tBoard));

        // 검색조건 추가
        if (keyword != null && searchType != null) {
            // tc => [t,c]
            String[] searchArr = searchType.split("");
            // BooleanBuilder 생성 ()
            BooleanBuilder searchBuilder = new BooleanBuilder();

            for (String type : searchArr) {

                switch (type) {
                    case "t" -> searchBuilder.or(tBoard.tTitle.contains(keyword));
                    case "c" -> searchBuilder.or(tBoard.tContent.contains(keyword));
                    case "w" -> searchBuilder.or(tBoard.nickname.contains(keyword));
                    case "r" -> searchBuilder.or(tReply.replyText.contains(keyword));
                }

            } // end for

            query.where(searchBuilder);
        }
        query.groupBy(tBoard);

        JPQLQuery<Tuple> tupleQuery = query.select(tBoard.tBno, tBoard.tTitle, tBoard.nickname, tReply.countDistinct());

        this.getQuerydsl().applyPagination(pageable, tupleQuery);

        log.info("1------------------------");
        List<Tuple> tuples = tupleQuery.fetch();

        List<Object[]> arrList = tuples.stream().map(tuple -> tuple.toArray()).collect(Collectors.toList());
        // tuple 내부에는 toArray가 나온다.

        log.info("2------------------------");
        log.info(tuples);
        log.info("3------------------------");
        long count = tupleQuery.fetchCount();

        log.info("count: " + count);

        return new PageImpl<>(arrList, pageable, count);

    }

    @Override
    public PageResponseDTO<TradeBoardListRcntDTO> searchDTORcnt(PageRequestDTO requestDTO) {
       
        log.info(requestDTO+" hwt2");
        
        Pageable pageable = makePageable(requestDTO);
        log.info(pageable+" hwt3");

        QTradeBoard tBoard = QTradeBoard.tradeBoard;
        QTradeReply tReply = QTradeReply.tradeReply;

        // JPQL로 보드 관련 테이블 만드는데 board에서 만든다
        JPQLQuery<TradeBoard> query = from(tBoard);
        // left join 항상 left join거는 쪽을 기준으로 잡는다.
        query.leftJoin(tReply).on(tReply.tradeBoard.eq(tBoard));

        String keyword = requestDTO.getKeyword();
        String searchType = requestDTO.getType();

        // 검색조건 추가
        if (keyword != null && searchType != null) {
            // tc => [t,c]
            String[] searchArr = searchType.split("");
            // BooleanBuilder 생성 ()
            BooleanBuilder searchBuilder = new BooleanBuilder();

            for (String type : searchArr) {

                switch (type) {
                    case "t" -> searchBuilder.or(tBoard.tTitle.contains(keyword));
                    case "c" -> searchBuilder.or(tBoard.tContent.contains(keyword));
                    case "w" -> searchBuilder.or(tBoard.nickname.contains(keyword));
                }

            } // end for

              // for문 끝난후 where 로 searchBuilder 추가
            query.where(searchBuilder);
        }
        // paging 처리
        this.getQuerydsl().applyPagination(pageable, query);

        // group by
        query.groupBy(tBoard);

        // 어제 했던 tuple 뽑는거 까진 똑같음
        // JPQL Query를 바로 BoardListRcntDTO로 추출하는 쿼리
        JPQLQuery<TradeBoardListRcntDTO> listQuery =
        query.select(Projections.bean(
            TradeBoardListRcntDTO.class, 
            tBoard.tBno, 
            tBoard.tTitle,
            tBoard.nickname,
            tBoard.regDate, 
            tReply.countDistinct().as("replyCount"))
            );


        // 쿼리를 List<BoardListRcntDTO>로 추출
        List<TradeBoardListRcntDTO> list = listQuery.fetch();

        log.info("--------------------------");

        long totalCount = listQuery.fetchCount();

        log.info(list+"hwtList");
        log.info(totalCount+"totalCount Hwt~~");
        log.info(requestDTO+"hwtList~~~!!!");


        return new PageResponseDTO<>(list, totalCount, requestDTO);

    }

}
