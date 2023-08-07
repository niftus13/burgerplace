package org.zerock.j1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.j1.domain.TradeReply;

public interface TradeReplyRepository extends JpaRepository<TradeReply, Long> {

    @Query("select tr from TradeReply tr where tr.tradeboard.tBno = :tBno ")
    Page<TradeReply> listBoard(@Param("tBno") Long tBno, Pageable pageable);

    @Query("select count(tr) from TradeReply tr where tr.tradeboard.tno = :tBno ")
    long getCountBoard(@Param("bno") Long bno);

}
