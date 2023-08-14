package org.zerock.j2.repository.search;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.j2.dto.FileBoardListDTO;
import org.zerock.j2.dto.PageRequestDTO;
import org.zerock.j2.dto.PageResponseDTO;
import org.zerock.j2.entity.FileBoard;
import org.zerock.j2.entity.QFileBoard;
import org.zerock.j2.entity.QFileBoardImage;// JOIN을 못하기에 의미가 없다.

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class FileBoardSearchImpl extends QuerydslRepositorySupport implements FileBoardSearch{

    public FileBoardSearchImpl() {
        super(FileBoard.class);
    }

    @Override
    public PageResponseDTO<FileBoardListDTO> list(PageRequestDTO pageRequestDTO) {
        
        
        QFileBoard board = QFileBoard.fileBoard;
        QFileBoardImage boardImage =QFileBoardImage.fileBoardImage;

        
        JPQLQuery<FileBoard> query = from(board);

        query.leftJoin(board.images,boardImage);

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
        )); 

        List<FileBoardListDTO> list = listQuery.fetch();
        Long totalCount = listQuery.fetchCount();

        // 모든 데이터를 뽑아오려고 할때
        // List<FileBoard> list = query.fetch();
       
        // list.forEach(fb -> {
        //     log.info(fb);
        //     log.info(fb.getImages());
        // });
        log.info(list+" list HWT");

        log.info(totalCount + "totalCount HWT ");
        
        log.info(pageRequestDTO + " PageRequestDTO HWT");

        return new PageResponseDTO<>(list, totalCount, pageRequestDTO);

    }
    
}