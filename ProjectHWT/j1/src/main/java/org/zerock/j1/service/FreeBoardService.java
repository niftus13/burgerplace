package org.zerock.j1.service;

import org.zerock.j1.dto.FreeBoardDTO;
import org.zerock.j1.dto.FreeBoardListRcntDTO;
import org.zerock.j1.dto.PageRequestDTO;
import org.zerock.j1.dto.PageResponseDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface FreeBoardService {

    PageResponseDTO<FreeBoardListRcntDTO> listRcnt(PageRequestDTO pageRequestDTO);

    FreeBoardDTO getOne(Long fBno);

}
