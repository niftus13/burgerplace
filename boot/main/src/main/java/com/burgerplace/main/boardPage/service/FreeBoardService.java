package com.burgerplace.main.boardPage.service;



import com.burgerplace.main.boardPage.dto.FreeBoardListRcntDTO;
import com.burgerplace.main.boardPage.dto.FreeGetBoardDTO;
import com.burgerplace.main.boardPage.dto.PageRequestDTO;
import com.burgerplace.main.boardPage.dto.PageResponseDTO;

import jakarta.transaction.Transactional;


@Transactional
public interface FreeBoardService {

    PageResponseDTO<FreeBoardListRcntDTO> listRcnt(PageRequestDTO pageRequestDTO);

    FreeGetBoardDTO getOne(Long freeBno);

    Long Register(FreeGetBoardDTO freeGetBoardDTO);

    void remove(Long freeBno);

    void modify(FreeGetBoardDTO freeGetBoardDTO);

}
