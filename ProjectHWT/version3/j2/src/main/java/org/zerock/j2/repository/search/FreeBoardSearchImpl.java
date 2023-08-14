package org.zerock.j2.repository.search;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.j2.dto.FreeBoardListRcntDTO;
import org.zerock.j2.dto.PageRequestDTO;
import org.zerock.j2.dto.PageResponseDTO;
import org.zerock.j2.entity.FreeBoard;
import org.zerock.j2.entity.QFreeBoard;
import org.zerock.j2.entity.QFreeReply;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class FreeBoardSearchImpl extends QuerydslRepositorySupport implements FreeBoardSearch {

    // 생성자를 만들어준다.
    public FreeBoardSearchImpl() {
        super(FreeBoard.class);
    }

    // interface 메소드를 구현
    @Override
    public Page<FreeBoard> search1(String searchType, String keyword, Pageable pageable) {

        QFreeBoard board = QFreeBoard.freeBoard;


        JPQLQuery<FreeBoard> query = from(board);
        // select * from FreeBoard

       
        if (keyword != null && searchType != null) { 

            String[] searchArr = searchType.split("");
            // 공백을 기준으로 문자열을 자르고 searchArr 배열에 넣는다.
            // Hello World => searchArr['Hello','World']

            // BooleanBuilder 생성
            // where 절에 포함될 쿼리문을 동적으로 생성하기 위해 만든다.
            BooleanBuilder searchBuilder = new BooleanBuilder();

            for (String type : searchArr) {
                // 들어온 모든 배열을 type이름으로 돌려버린다.
                switch (type) {
                    case "t" -> searchBuilder.or(board.freeTitle.contains(keyword));
                    case "c" -> searchBuilder.or(board.freeContent.contains(keyword));
                    case "w" -> searchBuilder.or(board.nickname.contains(keyword));
                }

            } // end for
              // for문 끝난후 where 로 searchBuilder 추가
            query.where(searchBuilder);
            // ex) type에 t가 있으면 title 칼럼에 keyword로 들어온 것이 있나 확인하고 있으면 내보낸다.
        }

        this.getQuerydsl().applyPagination(pageable, query);

        // list를 가져오는 방법
        List<FreeBoard> list = query.fetch();

        // 카운트를 가져오는 방법
        long count = query.fetchCount();

        log.info(list);
        log.info("count: " + count);
        // 동적쿼리까지 처리된 list
        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public Page<Object[]> searchWithRcnt(String searchType, String keyword, Pageable pageable) {

        QFreeBoard board = QFreeBoard.freeBoard;
        QFreeReply reply = QFreeReply.freeReply;

        JPQLQuery<FreeBoard> query = from(board);
        // select * from FreeBoard

        query.leftJoin(reply).on(reply.freeBoard.eq(board));
        // reply을 기준으로 조인을 하는데 조건은 reply의 freeBoard가 board와 같아야 한다.

        if (keyword != null && searchType != null) {

            String[] searchArr = searchType.split("");

            BooleanBuilder searchBuilder = new BooleanBuilder();

            for (String type : searchArr) {

                switch (type) {
                    case "t" -> searchBuilder.or(board.freeTitle.contains(keyword));
                    case "c" -> searchBuilder.or(board.freeContent.contains(keyword));
                    case "w" -> searchBuilder.or(board.nickname.contains(keyword));
                    case "r" -> searchBuilder.or(reply.replyText.contains(keyword));
                }

            } 

            query.where(searchBuilder);
        }

        query.groupBy(board);

        // JPQL tuple 타입으로 뽑아줘야된다.
        // select에 들어가는거는 실제로 추출하는 데이터 column
        // count 와 countdistinct는 중복된걸 배제하기위해서 사용되고, 조인이 곱의 방식이기때문
        JPQLQuery<Tuple> tupleQuery = query.select(board.freeBno, board.freeTitle, board.nickname, reply.countDistinct());

        // Paging 처리
        this.getQuerydsl().applyPagination(pageable, tupleQuery);

        log.info("1------------------------");
        List<Tuple> tuples = tupleQuery.fetch();

        List<Object[]> arrList = tuples.stream().map(tuple -> tuple.toArray()).collect(Collectors.toList());

        log.info("2------------------------");
        log.info(tuples);
        log.info("3------------------------");
        long count = tupleQuery.fetchCount();

        log.info("count: " + count);


        // Page까지 처리완료 
        return new PageImpl<>(arrList, pageable, count);

    }

    @Override
    public PageResponseDTO<FreeBoardListRcntDTO> searchDTORcnt(PageRequestDTO requestDTO) {

        log.info("-=-=-=-=-=-=-=-=---=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-==--=");
        log.info("로그 확인용 service-------------------------------------------------------");

        log.info(requestDTO);
        
        Pageable pageable = makePageable(requestDTO);

        log.info(pageable);

        QFreeBoard board = QFreeBoard.freeBoard;
        QFreeReply reply = QFreeReply.freeReply;

        JPQLQuery<FreeBoard> query = from(board);
        log.info(query+ " query");
        
        query.leftJoin(reply).on(reply.freeBoard.eq(board));

        String keyword = requestDTO.getKeyword();
        String searchType = requestDTO.getType();

        if (keyword != null && searchType != null) {

            String[] searchArr = searchType.split("");

            BooleanBuilder searchBuilder = new BooleanBuilder();

            for (String type : searchArr) {

                switch (type) {
                    case "t" -> searchBuilder.or(board.freeTitle.contains(keyword));
                    case "c" -> searchBuilder.or(board.freeContent.contains(keyword));
                    case "w" -> searchBuilder.or(board.nickname.contains(keyword));
                }

            } // end for

              // for문 끝난후 where 로 searchBuilder 추가
            query.where(searchBuilder);
        }
        // paging 처리
        this.getQuerydsl().applyPagination(pageable, query);
        log.info(this.getQuerydsl().applyPagination(pageable, query));

        // group by
        query.groupBy(board);

        log.info("-------지금 board값");
        log.info(board);

        // 어제 했던 tuple 뽑는거 까진 똑같음
        // JPQL Query를 바로 BoardListRcntDTO로 추출하는 쿼리
        JPQLQuery<FreeBoardListRcntDTO> listQuery =
        query.select(Projections.bean(
            FreeBoardListRcntDTO.class, 
            board.freeTitle, 
            board.freeBno,
            board.nickname,
            board.regDate, 
            reply.countDistinct().as("replyCount"))
            ); 



            

        // 쿼리를 List<BoardListRcntDTO>로 추출
        List<FreeBoardListRcntDTO> list = listQuery.fetch();

        log.info("--------------------------");
        log.info(list);

        long totalCount = listQuery.fetchCount();

        log.info(requestDTO);

        log.info(list);
        log.info(totalCount);
        log.info(requestDTO);


        return new PageResponseDTO<>(list, totalCount, requestDTO);

    }

    
}
