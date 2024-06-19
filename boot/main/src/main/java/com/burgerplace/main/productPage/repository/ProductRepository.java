package com.burgerplace.main.productPage.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.burgerplace.main.productPage.domain.Product;
import com.burgerplace.main.productPage.repository.search.ProductSearch;



public interface ProductRepository extends JpaRepository<Product, Long>, ProductSearch {
    
    @EntityGraph(attributePaths = "images")
    @Query("select p from Product p where p.delFlag = false and p.pno = :pno ")
    Product selectOne(@Param("pno")Long pno);



}
