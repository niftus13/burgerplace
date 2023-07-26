package com.burgerplace.main.service;

import org.springframework.transaction.annotation.Transactional;

import com.burgerplace.main.dto.PageResponseDTO;
import com.burgerplace.main.dto.ReplyDTO;
import com.burgerplace.main.dto.ReplyPageRequestDTO;


@Transactional
public interface ReplyService {
    
    PageResponseDTO<ReplyDTO> list(ReplyPageRequestDTO requestDTO);
    
}