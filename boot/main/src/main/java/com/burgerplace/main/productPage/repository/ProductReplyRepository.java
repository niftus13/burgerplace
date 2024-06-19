package com.burgerplace.main.productPage.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.burgerplace.main.productPage.domain.ProductReply;




public interface ProductReplyRepository extends JpaRepository<ProductReply,Long>{
    
    // @Query("select p from Reply p where p.product.pno = :pno")
    // Page<ProductReply> listProduct(@Param("pno") Long pno, Pageable pageable);

    // @Query("select count(p) from Reply p where p.product.pno = :pno")
    // long getCountProduct(@Param("pno") Long pno);

    @Query("select p from ProductReply p where p.product.pno =:pno")
    Page<ProductReply> listProduct(@Param("pno") Long pno, Pageable pageable);

    @Query("select count(p) from ProductReply p where p.product.pno =:pno")
    long getCountProduct(@Param("pno") Long pno);


}
