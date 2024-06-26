package com.burgerplace.main.mainPage.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.burgerplace.main.mainPage.entity.Reply;

public interface MainReplyRepository extends JpaRepository<Reply, Long>{
    @Query("select r from Reply r where r.board.bno = :bno")
    Page<Reply> listBoard(@Param("bno") Long bno, Pageable pageable);

    @Query("select count(r) from Reply r where r.board.bno = :bno")
    long getCountBoard(@Param("bno") Long bno);
}
