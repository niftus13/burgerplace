package com.burgerplace.main.productPage.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.burgerplace.main.mainPage.entity.Reply;


public interface ReplyRepository extends JpaRepository<com.burgerplace.main.mainPage.entity.Reply, Long>{
    @Query("select r from Reply r where r.board.bno = :bno")
    Page<Reply> listBoard(@Param("bno") Long bno, Pageable pageable);

    @Query("select count(r) from Reply r where r.board.bno = :bno")
    long getCountBoard(@Param("bno") Long bno);
}
