package com.burgerplace.bproduct.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.burgerplace.bproduct.entity.CrawlingProduct;
import com.burgerplace.bproduct.repositroy.CrawlingProductRepository;
import org.springframework.stereotype.Service;

import com.burgerplace.bproduct.dto.PageRequestDTO;
import com.burgerplace.bproduct.dto.PageResponseDTO;
import com.burgerplace.bproduct.dto.ProductDTO;
import com.burgerplace.bproduct.dto.ProductListDTO;
import com.burgerplace.bproduct.entity.Product;
import com.burgerplace.bproduct.entity.Tag;
import com.burgerplace.bproduct.repositroy.ProductRepository;
import com.burgerplace.bproduct.util.FileUploader;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final FileUploader fileUploader;
    private final CrawlingProductRepository crawlingProductRepository;

    @Override
    public PageResponseDTO<ProductListDTO> list(PageRequestDTO requestDTO) {

        return productRepository.listSearchWithReply(requestDTO);
    }

    @Override
    public Long register(ProductDTO productDTO) {


        Product product = Product.builder()
                .pname(productDTO.getPname())
                .price(productDTO.getPrice())
                .build();


        productDTO.getImages().forEach(fname -> {
            product.addImage(fname);
        });

        productDTO.getHashTags().forEach(tag ->{
            product.addTag(tag.getTagName());
        });

        return productRepository.save(product).getPno();
    }

    @Override
    public void parsing() {
        
        List<CrawlingProduct> crawlingProducts = crawlingProductRepository.findAll();

        for(CrawlingProduct crawlingProduct : crawlingProducts){

            log.info(
                    crawlingProduct.getCno() + "_"+ crawlingProduct.getPname()+ "_"+crawlingProduct.getPrice()
            );

            Product product = Product.builder()
                    .pname(crawlingProduct.getPname())
                    .price(crawlingProduct.getPrice())
                    .brand(crawlingProduct.getBrand())
                    
                    .build();

            

            product.addTag(crawlingProduct.getBrand());

            product.addTag(crawlingProduct.getPname());

            product.parseImg(crawlingProduct.getFileName());


            log.info("==================entity==================");
            log.info(product.getPno() + "_"+ product.getPname()+ "_"+product.getPrice());

            productRepository.save(product);
        }

    }

    @Override
    public ProductDTO readOne(Long pno) {

        Product product = productRepository.selectOne(pno);
        log.info(product);

        ProductDTO dto = ProductDTO.builder()
                .pno(product.getPno())
                .pname(product.getPname())
                .price(product.getPrice())
                .brand(product.getBrand())
                .event(product.getEvent())
                .images(product.getImages().stream().map(pi -> pi.getUUID()+"_"+pi.getPfname()).collect(Collectors.toList()))
                .build();

        return dto;
    }

    @Override
    public void remove(Long pno) {
        Product product = productRepository.selectOne(pno);

        product.changeDel(true);

        productRepository.save(product);

        List<String> fileNames = product.getImages().stream().map(pi -> pi.getPfname()).collect(Collectors.toList());

        fileUploader.removeFiles(fileNames);
    }

    @Override
    public void modify(ProductDTO productDTO) {

        // 기존 product load
        Optional<Product> result = productRepository.findById(productDTO.getPno());

        Product product = result.orElseThrow();
        // 기본 정보들 수정
        product.changePname(productDTO.getPname());
//        product.changePdesc(productDTO.getPdesc());
        product.changePrice(productDTO.getPrice());

        // 기존 이미지 목록 추출 --- 추후 비교해서 삭제
        List<String> oldFileNames = product.getImages().stream().map(pi -> pi.getPfname())
                .collect(Collectors.toList());
        // 이미지들은 clearImage() 실행
        product.clearImages();

        // 이미지 문자열들 추가 addImage()
        productDTO.getImages().forEach(fname -> product.addImage(fname));

        log.info("======================================");
        log.info("======================================");
        log.info("======================================");
        log.info(product);
        log.info("======================================");
        log.info("======================================");

        // save()
        productRepository.save(product);

        // 기존 파일들중에 productDTO.getImages() 포함안된 파일들 찾기
        List<String> newFiles = productDTO.getImages();
        List<String> wantDeleteFiles = oldFileNames.stream()
                .filter(f -> newFiles.indexOf(f) == -1)
                .collect(Collectors.toList());
        fileUploader.removeFiles(wantDeleteFiles);
    }

}
