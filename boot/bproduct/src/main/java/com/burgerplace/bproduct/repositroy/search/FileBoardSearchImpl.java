package com.burgerplace.bproduct.repositroy.search;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.burgerplace.bproduct.dto.FileBoardListDTO;
import com.burgerplace.bproduct.dto.PageRequestDTO;
import com.burgerplace.bproduct.dto.PageResponseDTO;
import com.burgerplace.bproduct.entity.FileBoard;
import com.burgerplace.bproduct.entity.QFileBoard;
import com.burgerplace.bproduct.entity.QFileBoardImage;

import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class FileBoardSearchImpl extends QuerydslRepositorySupport implements FileBoardSearch{

    public FileBoardSearchImpl() {
        super(FileBoard.class);
    }

    @Override
    public PageResponseDTO<FileBoardListDTO> list(PageRequestDTO pageRequestDTO) {

        // Join을 사용할수 없기때문에
        // Batch Size를 이용해서 사용한다.
        QFileBoard board = QFileBoard.fileBoard;
        QFileBoardImage boardImage = QFileBoardImage.fileBoardImage;

        JPQLQuery<FileBoard> query = from(board);
        
        query.leftJoin(board.images, boardImage);
        query.where(boardImage.ord.eq(0));


        int pageNum = pageRequestDTO.getPage()-1 <0?0: pageRequestDTO.getPage()-1;

        Pageable pageable = PageRequest.of(
                pageNum,
                pageRequestDTO.getSize(),
                Sort.by("bno").descending());

        this.getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<FileBoardListDTO> listQuery = query.select(
            Projections.bean(FileBoardListDTO.class,
            board.bno,
            board.title,
            boardImage.uuid,
            boardImage.fname
            )
        );

        List<FileBoardListDTO> list = listQuery.fetch();
        Long totalCount = listQuery.fetchCount();
        // List<FileBoard> list = query.fetch();

        // list.forEach(fb -> {
        //     log.info(fb);
        //     log.info(fb.getImages());
        // });

        // return null;
        return new PageResponseDTO<>(list, totalCount, pageRequestDTO);

    }

}
