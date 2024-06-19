package com.burgerplace.main.boardPage.service;


import com.burgerplace.main.boardPage.dto.PageResponseDTO;
import com.burgerplace.main.boardPage.dto.TradeGetReplyDTO;
import com.burgerplace.main.boardPage.dto.TradeReplyDTO;
import com.burgerplace.main.boardPage.dto.TradeReplyPageRequestDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface TradeReplyService {

    PageResponseDTO<TradeReplyDTO> list(TradeReplyPageRequestDTO requestDTO);

    TradeGetReplyDTO read(Long tradeRno);

    Long register(TradeGetReplyDTO replyDTO);

    void remove(Long tradeRno);

    void modify(TradeGetReplyDTO replyDTO);

}
