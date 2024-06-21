package com.burgerplace.main.productPage.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.burgerplace.main.productPage.domain.CrawlingProduct;
import com.burgerplace.main.productPage.domain.Product;
import com.burgerplace.main.productPage.dto.ProductDTO;

public interface CrawlingProductRepository extends JpaRepository<CrawlingProduct,Integer>,ProductRepository{



    @Query("select c from CrawlingProduct c where c.delFlag = false and c.cno = :cno ")
    CrawlingProduct getOne(@Param("cno")Long cno);


   @Query("select distinct p from CrawlingProduct c join Product p on c.cno = p.pno where c.delFlag = false")
    List<Product> findDistinctProducts();


}
