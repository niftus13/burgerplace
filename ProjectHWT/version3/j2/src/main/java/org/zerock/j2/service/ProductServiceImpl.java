package org.zerock.j2.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.j2.dto.PageRequestDTO;
import org.zerock.j2.dto.PageResponseDTO;
import org.zerock.j2.dto.ProductDTO;
import org.zerock.j2.dto.ProductListDTO;
import org.zerock.j2.entity.Product;
import org.zerock.j2.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.zerock.j2.util.FileUploader;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final FileUploader fileUploader;


    @Override
    public PageResponseDTO<ProductListDTO> list(PageRequestDTO requestDTO) {

        log.info(requestDTO);

        return productRepository.listWithReview(requestDTO);
        // 가져온 requestDTO 매개변수를 listwithreview에 전달한다.
    }

    @Override
    public Long register(ProductDTO productDTO) {

        // react에서 productDTO에 해당하는 정보를 boot로 보낸다.
        // 이 정보는 dto값이라서 entity값으로 변환하고 db에 저장해야 한다.
        
        Product product = Product.builder()
        .pname(productDTO.getPname())
        .pdesc(productDTO.getPdesc())
        .price(productDTO.getPrice())
        .build();
        //dto에 있는 것을 entity로 넣어주는 작업

        productDTO.getImages().forEach(fname->{
            product.addImage(fname);
        });

        return productRepository.save(product).getPno();
        // 데이터 베이스에 저장하고 이에 해당하는 번호를 return한다.
        
    }

    @Override
    public ProductDTO readOne(Long pno) {

        log.info(pno);

       Product product = productRepository.selectOne(pno);
       // deleg가 false인 것만 pno에 해당하는 로우를 가져온다.

       ProductDTO dto = ProductDTO.builder()
               .pno(product.getPno())
               .pname(product.getPname())
               .price(product.getPrice())
               .pdesc(product.getPdesc())
               .images(product.getImages().stream().map(pi -> pi.getFname()).collect(Collectors.toList()))
               .build();
               // builder 형식으로 product Entity에 있던 것을 dto로 옮겨준다.
               // images는 list 형식이다.
               log.info(dto+" hwt dto");

       return dto;
    }

    @Override
    public void remove(Long pno) {
        
        Product product =productRepository.selectOne(pno);
        // 삭제하기 전에 selectOne 쿼리로 해당 pno의 deleg가 0인 로우를 가져온다.

        product.changeDel(true);
        // 해당 로우를 true로 바꾼다. 1로...

        productRepository.save(product);
        // 이후에 저장한다.

        List<String> fileNames =
                product.getImages().stream().map(pi -> pi.getFname()).collect(Collectors.toList());
                // 파일은 여러개 있을 수 있어서 list형식으로 하나의 변수명으로 다 박는다.

        fileUploader.removeFiles(fileNames);
        // 해당 fileNames를 removesfiles 매개변수로 보낸다.

    }

    @Override
    public void modify(ProductDTO productDTO) {

        Optional<Product> result = productRepository.findById(productDTO.getPno());
        // 해당 productDTO 로우를 가져와 result에 넣는다.

        Product product = result.orElseThrow();
        // 예외 처리한 product

        // 기본 정보들 수정
        product.changePname(productDTO.getPname());
        // dto를 통해서 온 pname변경
        product.changePdesc(productDTO.getPdesc());
        // dto를 통해서 온 pdesc 변경
        product.changePrice(productDTO.getPrice());
        // dto를 통해서 온 가격 변경

        // 기존 이미지 목록 추출 --- 추후 비교해서 삭제
        List<String> oldFileNames =
                product.getImages().
                        stream().map(pi ->pi.getFname())
                        .collect(Collectors.toList());

        // 이미지들은 clearImage() 실행
        product.clearImages();

        // 이미지 문자열들 추가 addImage()
        productDTO.getImages().forEach(fname-> product.addImage(fname));

        log.info("======================================");
        log.info("======================================");
        log.info("======================================");
        log.info(product);
        log.info("======================================");
        log.info("======================================");


        // save()
        productRepository.save(product);


        // 기존 파일들중에  productDTO.getImages() 포함안된 파일들 찾기
        List<String> newFiles = productDTO.getImages();
        List<String> wantDeleteFiles = oldFileNames.stream()
                .filter(f -> newFiles.indexOf(f) == -1)
                // 들어온 문자열에서 f가 발견되지 않아서 -1이 결과값으로 나온 것만 wantDeleteFiles란 이름으로
                // 리스트 형태로 저장된다.
                .collect(Collectors.toList());
        fileUploader.removeFiles(wantDeleteFiles);
        // 제거 메서드로 보낸다.
    }


}
