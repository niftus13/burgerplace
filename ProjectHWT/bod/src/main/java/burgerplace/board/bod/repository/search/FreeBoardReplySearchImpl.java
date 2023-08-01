package burgerplace.board.bod.repository.search;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;

import burgerplace.board.bod.dto.FreeBoardReplyListDTO;
import burgerplace.board.bod.dto.PageRequestDTO;
import burgerplace.board.bod.dto.PageResponseDTO;
import burgerplace.board.bod.entity.FreeReply;
import burgerplace.board.bod.entity.QFReplyImage;
import burgerplace.board.bod.entity.QFreeReply;

public class FreeBoardReplySearchImpl extends QuerydslRepositorySupport implements FreeBoardReplySearch {

    public FreeBoardReplySearchImpl() {
        super(FreeReply.class);

    }

    @Override
    public PageResponseDTO<FreeBoardReplyListDTO> list(PageRequestDTO pageRequestDTO) {

        QFreeReply fReply = QFreeReply.freeReply;
        QFReplyImage frImage = QFReplyImage.fReplyImage;

        JPQLQuery<FreeReply> query = from(fReply);

        query.leftJoin(fReply.frImages, frImage);

        query.where(frImage.ord.eq(0));

        int pageNum = pageRequestDTO.getPage() - 1 < 0 ? 0 : pageRequestDTO.getPage() - 1;

        Pageable pageable = PageRequest.of(
                pageNum,
                pageRequestDTO.getSize(),
                Sort.by("bno").descending());

        this.getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<FreeBoardReplyListDTO> listQuery = query.select(
                Projections.bean(FreeBoardReplyListDTO.class,
                        fReply.fRno,
                        fReply.nickname,
                        frImage.uuid,
                        frImage.pName));

        List<FreeBoardReplyListDTO> dtoList = listQuery.fetch();
        Long totalCount = listQuery.fetchCount();

        return new PageResponseDTO<>(dtoList,totalCount,pageRequestDTO);

    }

}
