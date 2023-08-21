package com.burgerplace.bproduct.repositroy;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.j2.entity.FreeBoard;
import org.zerock.j2.entity.TradeBoard;
import org.zerock.j2.repository.search.TradeBoardSearch;

public interface TradeBoardRepository extends JpaRepository<TradeBoard, Long>, TradeBoardSearch {

    
    List<FreeBoard> findByTradeTitleContaining(String tradeTitle);


    @Query("select tb from TradeBoard tb where tb.tradeTitle like %:tradeTitle% ")
    List<FreeBoard> listTitle(@Param("tradeTitle") String tradeTitle);


    @Query("select tb.tradeBno, tb.tradeTitle from TradeBoard tb where tb.tradeTitle like %:tradeTitle% ")
    List<Object[]> listTitle2(@Param("tradeTitle") String tradeTitle);


    @Query("select tb.tradeBno, tb.tradeTitle from TradeBoard tb where tb.tradeTitle like %:tradeTitle% ")
    Page<Object[]> listTitle3(@Param("tradeTitle") String tradeTitle, Pageable pageable);


    @Modifying
    @Query("update TradeBoard tb set tb.tradeTitle = :tradeTitle where tb.tradeBno = :tradeBno")
    int modifyTitle(@Param("tradeTitle") String tradeTitle, @Param("tradeBno") Long tradeBno);


    Page<FreeBoard> findByTradeContentContaining(String tradeContent, org.springframework.data.domain.Pageable pageable);

    

    @Query(value = "select * from trade_board ", nativeQuery=true)
    List<Object[]> listNative();



    // 게시물에 따른 댓글의 갯수 추출
    // 쿼리 만들때 단계적으로 잘라서 해낸다.
    // JPQL를 짤때는 Class를 보고 짜줘야된다.
    
    @Query("select tb.tradeBno, tb.tradeTitle, tb.nickname, count(tr) from TradeBoard tb left outer join TradeReply tr on tr.tradeBoard = tb group by tb order by tb.tradeBno desc")
    List<Object[]> getListWithRcnt();
    
}
