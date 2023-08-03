package org.zerock.j1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.j1.domain.FreeReply;

public interface FreeReplyRepository extends JpaRepository<FreeReply, Long>{
    @Query("select fr from FreeReply fr where fr.freeBoard.fBno = :fBno ")
    Page<FreeReply> listBoard(@Param("fBno") Long fBno, Pageable pageable);

    // 게시글의 댓글수 가져오는것
    @Query("select count(fr) from FreeReply fr where fr.freeBoard.fBno = :fBno ")
    long getCountBoard(@Param("fBno") Long fBno);
}
