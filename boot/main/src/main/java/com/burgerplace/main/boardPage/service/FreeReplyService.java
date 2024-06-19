package com.burgerplace.main.boardPage.service;


import com.burgerplace.main.boardPage.dto.FreeGetReplyDTO;
import com.burgerplace.main.boardPage.dto.FreeReplyDTO;
import com.burgerplace.main.boardPage.dto.FreeReplyPageRequestDTO;
import com.burgerplace.main.boardPage.dto.PageResponseDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface FreeReplyService {

    PageResponseDTO<FreeReplyDTO> list(FreeReplyPageRequestDTO requestDTO);

    FreeGetReplyDTO read(Long freeRno);

    Long register(FreeGetReplyDTO replyDTO);

    void remove(Long freeRno);

    void modify(FreeGetReplyDTO replyDTO);

}