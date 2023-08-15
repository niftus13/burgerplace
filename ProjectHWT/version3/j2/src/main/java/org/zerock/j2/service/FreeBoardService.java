package org.zerock.j2.service;



import org.zerock.j2.dto.FreeBoardDTO;
import org.zerock.j2.dto.FreeBoardListRcntDTO;
import org.zerock.j2.dto.FreeGetBoardDTO;
import org.zerock.j2.dto.PageRequestDTO;
import org.zerock.j2.dto.PageResponseDTO;

import jakarta.transaction.Transactional;


@Transactional
public interface FreeBoardService {

    PageResponseDTO<FreeBoardListRcntDTO> listRcnt(PageRequestDTO pageRequestDTO);

    FreeGetBoardDTO getOne(Long freeBno);

    Long Register(FreeGetBoardDTO freeGetBoardDTO);

    void remove(Long freeBno);

    void modify(FreeGetBoardDTO freeGetBoardDTO);

}
