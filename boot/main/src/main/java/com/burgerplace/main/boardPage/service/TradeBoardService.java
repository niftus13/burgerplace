package com.burgerplace.main.boardPage.service;

import com.burgerplace.main.boardPage.dto.PageRequestDTO;
import com.burgerplace.main.boardPage.dto.PageResponseDTO;
import com.burgerplace.main.boardPage.dto.TradeBoardListRcntDTO;
import com.burgerplace.main.boardPage.dto.TradeGetBoardDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface TradeBoardService {

    PageResponseDTO<TradeBoardListRcntDTO> listRcnt(PageRequestDTO pageRequestDTO);

    TradeGetBoardDTO getOne(Long tradeBno);

    Long Register(TradeGetBoardDTO tradeGetBoardDTO);

    void remove(Long tradeBno);

    void modify(TradeGetBoardDTO tradeGetBoardDTO);
    
}
