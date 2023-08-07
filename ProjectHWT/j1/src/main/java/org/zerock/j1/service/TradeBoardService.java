package org.zerock.j1.service;

import org.zerock.j1.dto.PageRequestDTO;
import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.dto.TradeBoardDTO;
import org.zerock.j1.dto.TradeBoardListRcntDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface TradeBoardService {

    PageResponseDTO<TradeBoardListRcntDTO> listRcnt(PageRequestDTO pageRequestDTO);

    TradeBoardDTO getOne(Long tBno);

}
