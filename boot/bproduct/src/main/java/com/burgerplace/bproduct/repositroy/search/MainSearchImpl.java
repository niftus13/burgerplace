package com.burgerplace.bproduct.repositroy.search;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.burgerplace.main.dto.MainListRcntDTO;
import com.burgerplace.main.dto.PageRequestDTO;
import com.burgerplace.main.dto.PageResponseDTO;
import com.burgerplace.main.entity.Main;
import com.burgerplace.main.entity.QMain;
import com.burgerplace.main.entity.QReply;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MainSearchImpl extends QuerydslRepositorySupport implements MainSearch{
    
    public MainSearchImpl() {

        super(Main.class);
    }

    @Override
    public Page<Main> search1(String searchType, String keyword, Pageable pageable) {

        QMain main = QMain.main;

        JPQLQuery<Main> query = from(main);

        if (keyword != null && searchType != null) {

            // tc -> [t,c]
            String[] searchArr = searchType.split("");

            BooleanBuilder searchBuilder = new BooleanBuilder();

            for (String type : searchArr) {
                switch (type) {
                    case "t" -> searchBuilder.or(main.title.contains(keyword));
                    case "c" -> searchBuilder.or(main.content.contains(keyword));
                    case "w" -> searchBuilder.or(main.writer.contains(keyword));
                }
            }
            query.where(searchBuilder);
        }

        // query.where(main.title.contains("1"));

        this.getQuerydsl().applyPagination(pageable, query);

        List<Main> list = query.fetch();

        long count = query.fetchCount();

        log.info(list);
        log.info("count: " + count);

        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public Page<Object[]>searchWithRcnt(String searchType, String keyword, Pageable pageable) {

        QMain main = QMain.main;
        QReply reply = QReply.reply;

        JPQLQuery<Main> query = from(main);

        if (keyword != null && searchType != null) {

            // tc -> [t,c]
            String[] searchArr = searchType.split("");

            BooleanBuilder searchBuilder = new BooleanBuilder();

            for (String type : searchArr) {
                switch (type) {
                    case "t" -> searchBuilder.or(main.title.contains(keyword));
                    case "c" -> searchBuilder.or(main.content.contains(keyword));
                    case "w" -> searchBuilder.or(main.writer.contains(keyword));
                }
            }
            query.where(searchBuilder);
        }

        query.leftJoin(reply).on(reply.main.eq(main));
        query.groupBy(main);

        JPQLQuery<Tuple> tupleQuery = query.select(main.bno, main.title, main.writer, reply.countDistinct());

        this.getQuerydsl().applyPagination(pageable, tupleQuery);

        log.info("1--------------------------------");

        List<Tuple> tuples = tupleQuery.fetch();

        List<Object[]> arrList = tuples.stream().map(tuple -> tuple.toArray()).collect(Collectors.toList());

        log.info("2--------------------------------");

        log.info(tuples);

        long count = tupleQuery.fetchCount();

        log.info("3--------------------------------");

        log.info("count: " + count);

        return new PageImpl<>(arrList, pageable, count);
    }

    @Override
    public PageResponseDTO<MainListRcntDTO> searchDTORcnt(PageRequestDTO requestDTO) {

        Pageable pageable = makePageable(requestDTO);

        QMain main = QMain.main;
        QReply reply = QReply.reply;

        JPQLQuery<Main> query = from(main);

        String keyword = requestDTO.getKeyword();
        String searchType = requestDTO.getType();

        if (keyword != null && searchType != null) {

            // tc -> [t,c]
            String[] searchArr = searchType.split("");

            BooleanBuilder searchBuilder = new BooleanBuilder();

            for (String type : searchArr) {
                switch (type) {
                    case "t" -> searchBuilder.or(main.title.contains(keyword));
                    case "c" -> searchBuilder.or(main.content.contains(keyword));
                    case "w" -> searchBuilder.or(main.writer.contains(keyword));
                }
            }
            query.where(searchBuilder);
        }

        this.getQuerydsl().applyPagination(pageable, query);
        query.leftJoin(reply).on(reply.main.eq(main));
        query.groupBy(main);

        JPQLQuery<MainListRcntDTO> listQuery = query.select(
            Projections.bean(MainListRcntDTO.class,
                main.bno,
                main.title,
                main.writer,
                reply.countDistinct().as("replyCount")));

        List<MainListRcntDTO> list = listQuery.fetch();

        log.info("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        log.info(list);

        long totalCount = listQuery.fetchCount();

        return new PageResponseDTO<>(list, totalCount, requestDTO);
    }
}
