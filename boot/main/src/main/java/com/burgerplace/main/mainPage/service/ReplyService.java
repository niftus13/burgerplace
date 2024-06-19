package com.burgerplace.main.mainPage.service;

import org.springframework.transaction.annotation.Transactional;

import com.burgerplace.main.common.pageDto.PageResponseDTO;
import com.burgerplace.main.mainPage.dto.ReplyDTO;
import com.burgerplace.main.mainPage.dto.ReplyPageRequestDTO;


@Transactional
public interface ReplyService {
    
    PageResponseDTO<ReplyDTO> list(ReplyPageRequestDTO requestDTO);
    
}