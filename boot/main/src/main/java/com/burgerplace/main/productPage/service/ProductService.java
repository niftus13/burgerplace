package com.burgerplace.main.productPage.service;

import org.springframework.transaction.annotation.Transactional;

import com.burgerplace.main.common.pageDto.PageRequestDTO;
import com.burgerplace.main.common.pageDto.PageResponseDTO;
import com.burgerplace.main.productPage.dto.ProductDTO;
import com.burgerplace.main.productPage.dto.ProductListDTO;

@Transactional
public interface ProductService {
    
    PageResponseDTO<ProductListDTO> list(PageRequestDTO pageRequestDTO);

    Long register(ProductDTO productDTO);

    void parsing();

    ProductDTO readOne(Long pno);

    void remove(Long pno);

    void modify(ProductDTO productDTO);




}
