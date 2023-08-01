package burgerplace.board.bod.repository.search;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;

import burgerplace.board.bod.dto.FreeBoardListDTO;
import burgerplace.board.bod.dto.PageRequestDTO;
import burgerplace.board.bod.dto.PageResponseDTO;
import burgerplace.board.bod.entity.FreeBoard;
import burgerplace.board.bod.entity.QFBoardImage;
import burgerplace.board.bod.entity.QFreeBoard;

public class FreeBoardSearchImpl extends QuerydslRepositorySupport implements FreeBoardSearch {

    public FreeBoardSearchImpl() {
        super(FreeBoard.class);
    }

    @Override
    public PageResponseDTO<FreeBoardListDTO> list(PageRequestDTO pageRequestDTO) {

        QFreeBoard fBoard = QFreeBoard.freeBoard;
        QFBoardImage fbImage = QFBoardImage.fBoardImage;

        JPQLQuery<FreeBoard> query = from(fBoard);

        query.leftJoin(fBoard.fImages, fbImage);

        query.where(fbImage.ord.eq(0));

        int pageNum = pageRequestDTO.getPage() - 1 < 0 ? 0 : pageRequestDTO.getPage() - 1;

        Pageable pageable = PageRequest.of(
                pageNum,
                pageRequestDTO.getSize(),
                Sort.by("bno").descending());

        this.getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<FreeBoardListDTO> listQuery = query.select(
                Projections.bean(FreeBoardListDTO.class,
                        fBoard.fBno,
                        fBoard.fTitle,
                        fbImage.uuid,
                        fbImage.pName));

        List<FreeBoardListDTO> dtoList = listQuery.fetch();
        Long totalCount = listQuery.fetchCount();

        return new PageResponseDTO<>(dtoList,totalCount,pageRequestDTO);
    }

}
