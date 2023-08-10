package com.burgerplace.bproduct.repositroy.search;

import com.burgerplace.bproduct.dto.PageRequestDTO;
import com.burgerplace.bproduct.dto.PageResponseDTO;
import com.burgerplace.bproduct.dto.ProductListDTO;

public interface ProductSearch {
    
    PageResponseDTO<ProductListDTO> list(PageRequestDTO pageRequestDTO);
    PageResponseDTO<ProductListDTO> listWithReview(PageRequestDTO pageRequestDTO);
    PageResponseDTO<ProductListDTO> listSearchWithReview(PageRequestDTO pageRequestDTO);
}
