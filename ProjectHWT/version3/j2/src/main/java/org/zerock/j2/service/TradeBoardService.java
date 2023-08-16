package org.zerock.j2.service;

import org.zerock.j2.dto.PageRequestDTO;
import org.zerock.j2.dto.PageResponseDTO;
import org.zerock.j2.dto.TradeBoardListRcntDTO;
import org.zerock.j2.dto.TradeGetBoardDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface TradeBoardService {

    PageResponseDTO<TradeBoardListRcntDTO> listRcnt(PageRequestDTO pageRequestDTO);

    TradeGetBoardDTO getOne(Long tradeBno);

    Long Register(TradeGetBoardDTO tradeGetBoardDTO);

    void remove(Long tradeBno);

    void modify(TradeGetBoardDTO tradeGetBoardDTO);
    
}
