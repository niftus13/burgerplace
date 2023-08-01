package com.burgerplace.bproduct.repositroy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.burgerplace.bproduct.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Query("select p from Reply p where p.product.pno = :pno")
    Page<Reply> listProduct(@Param("pno") Long pno, Pageable pageable);

    @Query("select count(p) from Reply p where p.product.pno = :pno")
    long getCountProduct(@Param("pno") Long pno);
}
