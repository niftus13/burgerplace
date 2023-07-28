package com.burgerplace.bproduct.service;

import org.springframework.stereotype.Service;

import com.burgerplace.bproduct.dto.PageRequestDTO;
import com.burgerplace.bproduct.dto.PageResponseDTO;
import com.burgerplace.bproduct.dto.ProductDTO;
import com.burgerplace.bproduct.dto.ProductListDTO;
import com.burgerplace.bproduct.entity.Product;
import com.burgerplace.bproduct.repositroy.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public PageResponseDTO<ProductListDTO> list(PageRequestDTO requestDTO) {

        return productRepository.listWithReview(requestDTO);
    }

    @Override
    public Long register(ProductDTO productDTO) {
        Product product = Product.builder()
                .pname(productDTO.getPname())
                .pdesc(productDTO.getPdesc())
                .price(productDTO.getPrice())
                .build();

        productDTO.getImages().forEach(fname -> {
            product.addImage(fname);
        });

        return productRepository.save(product).getPno();
    }

}
