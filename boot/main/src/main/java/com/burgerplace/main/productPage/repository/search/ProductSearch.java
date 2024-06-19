package com.burgerplace.main.productPage.repository.search;

import com.burgerplace.main.common.pageDto.PageRequestDTO;
import com.burgerplace.main.common.pageDto.PageResponseDTO;
import com.burgerplace.main.productPage.dto.ProductListDTO;

public interface ProductSearch {
    
    PageResponseDTO<ProductListDTO> list(PageRequestDTO pageRequestDTO);
    PageResponseDTO<ProductListDTO> listWithReply(PageRequestDTO pageRequestDTO);
    PageResponseDTO<ProductListDTO> listSearchWithReply(PageRequestDTO pageRequestDTO);

    PageResponseDTO<ProductListDTO> listSearchWithReplyAndHashTag(PageRequestDTO pageRequestDTO);
}
