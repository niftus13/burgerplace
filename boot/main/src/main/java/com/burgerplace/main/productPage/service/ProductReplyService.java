package com.burgerplace.main.productPage.service;


import com.burgerplace.main.common.pageDto.PageResponseDTO;

import com.burgerplace.main.productPage.dto.ProductReplyDTO;
import com.burgerplace.main.productPage.dto.ReplyPageRequestDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface ProductReplyService {

    PageResponseDTO<ProductReplyDTO> list(ReplyPageRequestDTO requestDTO);

    // reply insert
    Long register(ProductReplyDTO replyDTO);

    // reply read
    ProductReplyDTO read(Long prno);

    // reply delete
    void remove(Long prno);

    // reply modify
    void modify(ProductReplyDTO replyDTO);

    
}
