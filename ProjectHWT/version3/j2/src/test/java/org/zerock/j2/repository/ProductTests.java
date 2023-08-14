package org.zerock.j2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import org.zerock.j2.dto.PageRequestDTO;
import org.zerock.j2.dto.PageResponseDTO;
import org.zerock.j2.dto.ProductListDTO;
import org.zerock.j2.entity.Product;

import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class ProductTests {
 
    @Autowired
    ProductRepository repo;

    @Test
    public void testInsert(){
        for(int i =0; i < 200; i++){
        Product product = Product.builder()
        .pname("Test" +i)
        .pdesc("Test" + i)
        .writer("user" + i)
        .price(4000)
        .build();

        product.addImage(UUID.randomUUID().toString()+ "_aaa.jpg");
        product.addImage(UUID.randomUUID().toString()+"_bbb.jpg");
        product.addImage(UUID.randomUUID().toString()+"_ccc.jpg");

        repo.save(product);
        }//end for

    }

    @Test
    @Transactional // 안걸리면 lazy exception이 걸린다.
    public void testRead1(){

        Optional<Product> result = repo.findById(10L);

        Product product = result.orElseThrow();
        System.out.println(product+ " hwt product");
        System.out.println("---------------------");
        System.out.println(product.getImages()+" hwt product image");
    }
    // Entity Graph를 적용한 상세보기
    @Test
    public void testRead2(){

        Product product = repo.selectOne(15L);

  
        System.out.println(product);
        System.out.println("---------------------");
        System.out.println(product.getImages());
    }

    // Delete Test
    @Test
    public void testDelete(){

        repo.deleteById(1L);
    }
    
    // Modify Test
    @Commit
    @Transactional
    @Test
    public void testUpdate(){
        // 1이 삭제되었기때문
        Optional<Product> result = repo.findById(2L);

        Product product = result.orElseThrow();
         product.changePrice(6000);
         product.clearImages();

         product.addImage(UUID.randomUUID().toString()+"_newImage.jpg");
        
         repo.save(product);
         
    }
    // 동적 쿼리 테스트
    @Test
    public void testList1(){

        PageRequestDTO requestDTO = new PageRequestDTO();

        PageResponseDTO<ProductListDTO> result = repo.list(requestDTO);

       for (ProductListDTO dto : result.getDtoList()) {
        
        System.out.println(dto);
       } 
        
    }
    @Test
    public void testList2(){

        PageRequestDTO requestDTO = new PageRequestDTO();

        PageResponseDTO<ProductListDTO> result = repo.listWithReview(requestDTO);

       for (ProductListDTO dto : result.getDtoList()) {
        
        System.out.println(dto);
       } 
        
    }

}
