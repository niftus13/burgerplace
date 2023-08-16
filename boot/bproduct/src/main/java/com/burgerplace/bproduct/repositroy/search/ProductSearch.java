package com.burgerplace.bproduct.repositroy.search;

import com.burgerplace.bproduct.dto.PageRequestDTO;
import com.burgerplace.bproduct.dto.PageResponseDTO;
import com.burgerplace.bproduct.dto.ProductListDTO;

public interface ProductSearch {
    
    PageResponseDTO<ProductListDTO> list(PageRequestDTO pageRequestDTO);
    PageResponseDTO<ProductListDTO> listWithReply(PageRequestDTO pageRequestDTO);
    PageResponseDTO<ProductListDTO> listSearchWithReply(PageRequestDTO pageRequestDTO);

    PageResponseDTO<ProductListDTO> listSearchWithReplyAndHashTag(PageRequestDTO pageRequestDTO);
}
