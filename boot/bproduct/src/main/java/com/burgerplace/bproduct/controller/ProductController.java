package com.burgerplace.bproduct.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.burgerplace.bproduct.dto.PageRequestDTO;
import com.burgerplace.bproduct.dto.PageResponseDTO;
import com.burgerplace.bproduct.dto.ProductDTO;
import com.burgerplace.bproduct.dto.ProductListDTO;
import com.burgerplace.bproduct.service.ProductService;
import com.burgerplace.bproduct.util.FileUploader;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin
@RequestMapping("/api/products/")
@RequiredArgsConstructor
@Log4j2
public class ProductController {

    private final ProductService service;
    private final FileUploader uploader;

    @GetMapping("list")
    public PageResponseDTO<ProductListDTO> list(PageRequestDTO pageRequestDTO) {

        log.info(pageRequestDTO);

        return service.list(pageRequestDTO);
    }

    @PostMapping("")
    public Map<String, Long> register(ProductDTO productDTO) {

        log.info(productDTO);

        List<String> fileNames = uploader.uploadFiles(productDTO.getFiles(), true);
        productDTO.setImages(fileNames);

        Long pno = service.register(productDTO);

        // return Map.of("result", 123L);
        return Map.of("result", pno);
    }

    @GetMapping("{pno}")
    public ProductDTO getOne(@PathVariable("pno") Long pno) {

        log.info("PNO..............." + pno);

        return service.readOne(pno);
    }

    @DeleteMapping("{pno}")
    public Map<String, Long> delete(@PathVariable("pno") Long pno) {

        log.info("PNO..............." + pno);
        service.remove(pno);
        return Map.of("result", pno);
    }
}
