package com.burgerplace.bproduct.service;

import com.burgerplace.bproduct.dto.PageResponseDTO;
import com.burgerplace.bproduct.dto.ProductReplyDTO;
import com.burgerplace.bproduct.dto.ReplyPageRequestDTO;

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
