package org.zerock.j2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.zerock.j2.entity.TradeReply;

public interface TradeReplyRepository extends JpaRepository<TradeReply, Long> {

    @Query("select tr from TradeReply tr where tr.tradeBoard.tradeBno = :tradeBno ")
    Page<TradeReply> listBoard(@Param("tradeBno") Long tradeBno, Pageable pageable);

    
    // 게시글의 댓글수 가져오는것
    @Query("select count(tr) from TradeReply tr where tr.tradeBoard.tradeBno = :tradeBno ")
    long getCountBoard(@Param("tradeBno") Long tradeBno);
    // 이 로직은 외래키의 bno의 count를 세어 전달해준다.
    
}
