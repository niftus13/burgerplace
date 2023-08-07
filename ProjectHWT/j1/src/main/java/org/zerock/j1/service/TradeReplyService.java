package org.zerock.j1.service;

import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.dto.TradeReplyDTO;
import org.zerock.j1.dto.TradeReplyPageRequestDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface TradeReplyService {

    PageResponseDTO<TradeReplyDTO> list(TradeReplyPageRequestDTO requestDTO);

    Long register(TradeReplyDTO replyDTO);

    TradeReplyDTO read(Long tRno);

    void remove(Long tRno);

    void modify(TradeReplyDTO replyDTO);

}
