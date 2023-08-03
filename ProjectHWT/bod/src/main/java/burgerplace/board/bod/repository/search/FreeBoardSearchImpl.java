package burgerplace.board.bod.repository.search;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

import burgerplace.board.bod.dto.FreeBoardListDTO;
import burgerplace.board.bod.dto.PageRequestDTO;
import burgerplace.board.bod.dto.PageResponseDTO;
import burgerplace.board.bod.entity.FreeBoard;
import burgerplace.board.bod.entity.QFreeBoard;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class FreeBoardSearchImpl extends QuerydslRepositorySupport implements FreeBoardSearch {

    public FreeBoardSearchImpl() {
        super(FreeBoard.class);

    }

    @Override
    public Page<FreeBoard> search1(String searchType, String keyword, Pageable pageable) {

        QFreeBoard fboard = QFreeBoard.freeBoard;

        JPQLQuery<FreeBoard> query = from(fboard);

        // 키워드 와 타입이 있는지 확인 후
        if (keyword != null && searchType != null) {
            // tc => [t,c]
            String[] searchArr = searchType.split("");
            // BooleanBuilder 생성
            BooleanBuilder searchBuilder = new BooleanBuilder();

            for (String type : searchArr) {

                switch (type) {
                    case "t" -> searchBuilder.or(fboard.fTitle.contains(keyword));
                    case "c" -> searchBuilder.or(fboard.fContent.contains(keyword));
                    case "w" -> searchBuilder.or(fboard.nickname.contains(keyword));
                }

            } // end for
              // for문 끝난후 where 로 searchBuilder 추가
            query.where(searchBuilder);
        }

        this.getQuerydsl().applyPagination(pageable, query);
        // list를 가져오는 방법
        List<FreeBoard> list = query.fetch();
        long count = query.fetchCount();

        log.info(list);
        log.info("count: " + count);
        // 동적쿼리까지 처리된 list
        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public Page<Object[]> searchWithRcnt(String searchType, String searchKeyword, Pageable pageable) {
        
        

        return null;
    }

    @Override
    public PageResponseDTO<FreeBoardListDTO> searchDTORcnt(PageRequestDTO requestDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchDTORcnt'");
    }

}
