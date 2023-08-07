package com.burgerplace.sales.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.burgerplace.sales.domain.SalesBoard;
import com.fasterxml.jackson.databind.util.ArrayBuilders.BooleanBuilder;

public class SalesBoardSearchImpl extends QuerydslRepositorySupport implements SalesBoardSearch {

    // 생성자를 만들어준다.
    public SalesBoardSearchImpl() {
        super(SalesBoard.class);
    }

    @Override
    public Page<SalesBoard> search1(String searchType, String searchKeyword, Pageable pageable) {
        
        /// QueryDomain 이 필요하다
        QSalesBoard board = QSalesBoard.board;
        // Query를 동적으로 만들어내는 작업
        // SQL 문을 객체화 시켜놓은것
        JPQLQuery<SalesBoard> query = from(board);

        // query.where(board.title.contains("1"));

        // 키워드 와 타입이 있는지 확인 후 
        if(keyword != null && searchType !=null){
            //tc => [t,c]
            String[] searchArr = searchType.split("");
            // BooleanBuilder 생성
            BooleanBuilder searchBuilder = new BooleanBuilder();

            for (String  type : searchArr) {

                switch(type){
                    case "t" -> searchBuilder.or(board.title.contains(keyword));
                    case "c" -> searchBuilder.or(board.content.contains(keyword));
                    case "w" -> searchBuilder.or(board.writer.contains(keyword));
                }


            }// end for
            // for문 끝난후 where 로 searchBuilder 추가
            query.where(searchBuilder);
        }

        this.getQuerydsl().applyPagination(pageable, query);
        // list를 가져오는 방법
        List<Board> list = query.fetch();
        long count = query.fetchCount();

        log.info(list);
        log.info("count: " + count);
        // 동적쿼리까지 처리된 list
        return new PageImpl<>(list, pageable, count);
    }
    
}
