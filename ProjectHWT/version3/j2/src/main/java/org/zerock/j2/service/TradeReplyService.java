package org.zerock.j2.service;


import org.zerock.j2.dto.PageResponseDTO;
import org.zerock.j2.dto.TradeGetReplyDTO;
import org.zerock.j2.dto.TradeReplyDTO;
import org.zerock.j2.dto.TradeReplyPageRequestDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface TradeReplyService {

    PageResponseDTO<TradeReplyDTO> list(TradeReplyPageRequestDTO requestDTO);

    TradeGetReplyDTO read(Long tradeRno);

    Long register(TradeGetReplyDTO replyDTO);

    void remove(Long tradeRno);

    void modify(TradeGetReplyDTO replyDTO);

}
