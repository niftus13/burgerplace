package com.burgerplace.bproduct.service;

import org.springframework.transaction.annotation.Transactional;

import com.burgerplace.bproduct.dto.PageRequestDTO;
import com.burgerplace.bproduct.dto.PageResponseDTO;
import com.burgerplace.bproduct.dto.ProductDTO;
import com.burgerplace.bproduct.dto.ProductListDTO;

@Transactional
public interface ProductService {
    
    PageResponseDTO<ProductListDTO> list(PageRequestDTO pageRequestDTO);

    Long register(ProductDTO productDTO);
}
