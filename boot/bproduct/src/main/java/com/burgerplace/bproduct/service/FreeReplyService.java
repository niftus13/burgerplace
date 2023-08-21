package com.burgerplace.bproduct.service;


import org.zerock.j2.dto.FreeGetReplyDTO;
import org.zerock.j2.dto.FreeReplyDTO;
import org.zerock.j2.dto.FreeReplyPageRequestDTO;
import org.zerock.j2.dto.PageResponseDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface FreeReplyService {

    PageResponseDTO<FreeReplyDTO> list(FreeReplyPageRequestDTO requestDTO);

    FreeGetReplyDTO read(Long freeRno);

    Long register(FreeGetReplyDTO replyDTO);

    void remove(Long freeRno);

    void modify(FreeGetReplyDTO replyDTO);

}