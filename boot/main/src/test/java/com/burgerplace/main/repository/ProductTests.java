package com.burgerplace.main.repository;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.burgerplace.main.common.pageDto.PageRequestDTO;
import com.burgerplace.main.common.pageDto.PageResponseDTO;
import com.burgerplace.main.productPage.domain.Product;
import com.burgerplace.main.productPage.dto.ProductListDTO;
import com.burgerplace.main.productPage.repository.ProductRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class ProductTests {

    @Autowired
    ProductRepository repo;

    // @Test
    // public void testInsert(){

    // Product product = Product.builder()
    // .pname("Test")
    // .pdesc("Test")
    // .writer("user00")
    // .price(4000)
    // .build();

    // product.addImage(UUID.randomUUID().toString()+ "_aaa.jpg");
    // product.addImage(UUID.randomUUID().toString()+"_bbb.jpg0");
    // product.addImage(UUID.randomUUID().toString()+"_ccc.jpg");

    // repo.save(product);

    // }



    @Test
    @Transactional // 안걸리면 lazy exception이 걸린다.
    public void testRead1() {

        Optional<Product> result = repo.findById(1L);

        if (result.isPresent()) {
            Product product = result.get();
            System.out.println(product);
            System.out.println("---------------------");
            System.out.println(product.getImages());
        } else {
            System.out.println("Product not found.");
        }
    }

    // Entity Graph를 적용한 상세보기
    @Test
    public void testRead2() {

        Product product = repo.selectOne(1L);

        if (product != null) {
            System.out.println(product);
            System.out.println("---------------------");
            System.out.println(product.getImages());
        } else {
            System.out.println("Product not found.");
        }
    }

    // Delete Test
    @Test
    public void testDelete() {

        repo.deleteById(1L);
    }

    // Modify Test
    @Commit
    @Transactional
    @Test
    public void testUpdate() {
        // 1이 삭제되었기때문
        Optional<Product> result = repo.findById(2L);

        Product product = result.orElseThrow();
        product.changePrice(6000);
        product.clearImages();

        product.addImage(UUID.randomUUID().toString() + "_newImage.jpg");

        repo.save(product);

    }

    @Test
    public void testList1() {

        PageRequestDTO requestDTO = new PageRequestDTO();

        PageResponseDTO<ProductListDTO> result = repo.list(requestDTO);

        for (ProductListDTO dto : result.getDtoList()) {

            System.out.println(dto);
        }

    }

    @Test
    public void testList2() {

        PageRequestDTO requestDTO = new PageRequestDTO();

        PageResponseDTO<ProductListDTO> result = repo.listWithReply(requestDTO);

        for (ProductListDTO dto : result.getDtoList()) {

            System.out.println(dto);
        }

    }

    @Test
    public void testList3(){

        PageRequestDTO requestDTO = new PageRequestDTO();
        PageResponseDTO<ProductListDTO> result = repo.listSearchWithReply(requestDTO);

        for (ProductListDTO dto : result.getDtoList()) {

            System.out.println(dto);
        }

    
    }

    @Test
    public void testList4(){
                PageRequestDTO requestDTO = new PageRequestDTO();
        PageResponseDTO<ProductListDTO> result = repo.listSearchWithReplyAndHashTag(requestDTO);

        for (ProductListDTO dto : result.getDtoList()) {

            System.out.println(dto);
        }

    }


}
