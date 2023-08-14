package org.zerock.j2.service;

import org.zerock.j2.dto.PageRequestDTO;
import org.zerock.j2.dto.PageResponseDTO;
import org.zerock.j2.dto.ProductDTO;
import org.zerock.j2.dto.ProductListDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface ProductService {
    
    PageResponseDTO<ProductListDTO> list (PageRequestDTO requestDTO);
    // controller로 부터 매개변수를 받아온다.
    

    Long register(ProductDTO productDTO);
    // controller로부터 productDTO가 들어온다.
    

    ProductDTO readOne(Long pno);


    void remove(Long pno);


    void modify(ProductDTO productDTO);
    
}
