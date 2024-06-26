package com.burgerplace.main.productPage.contoller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.burgerplace.main.common.pageDto.PageRequestDTO;
import com.burgerplace.main.common.pageDto.PageResponseDTO;
import com.burgerplace.main.common.util.FileUploader;
import com.burgerplace.main.productPage.dto.ProductDTO;
import com.burgerplace.main.productPage.dto.ProductListDTO;
import com.burgerplace.main.productPage.service.ProductService;

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

    @PostMapping("modify")
    public Map<String, Long> modify(ProductDTO productDTO) {

        log.info("----------------modify-----------------");
        log.info("----------------modify-----------------");
        log.info("----------------modify-----------------");
        log.info(productDTO);
        // 기존 파일 및 업로드된 파일까지 추가하는 배열
        if (productDTO.getFiles() != null && productDTO.getFiles().size() > 0) {
            
            List<String> uploadFileNames = uploader.uploadFiles(productDTO.getFiles(), true);

            List<String> oldFileNames = productDTO.getImages();

            uploadFileNames.forEach(fname -> oldFileNames.add(fname));
        }

        log.info("After............");
        log.info(productDTO);

        service.modify(productDTO);

        return Map.of("result", productDTO.getPno());
    }
}
