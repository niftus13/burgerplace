package org.zerock.j2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.j2.entity.FreeReply;

public interface FreeReplyRepository extends JpaRepository<FreeReply, Long> {

    @Query("select fr from FreeReply fr where fr.freeBoard.freeBno = :freeBno ")
    Page<FreeReply> listBoard(@Param("freeBno") Long freeBno, Pageable pageable);

    
    // 게시글의 댓글수 가져오는것
    @Query("select count(fr) from FreeReply fr where fr.freeBoard.freeBno = :freeBno ")
    long getCountBoard(@Param("freeBno") Long freeBno);
    // 이 로직은 외래키의 bno의 count를 세어 전달해준다.
    
}
