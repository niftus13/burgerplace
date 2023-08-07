package org.zerock.j1.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.j1.domain.TradeBoard;
import org.zerock.j1.dto.TradeBoardReadDTO;
import org.zerock.j1.repository.search.TradeBoardSearch;

public interface TradeBoardRepository extends JpaRepository<TradeBoard, Long>, TradeBoardSearch  {

    List<TradeBoard> fingBytTitleContaining(String tTtile);

    @Query("select tb from TradeBoard tb where tb.tTitle like %:tTitle% ")
    List<TradeBoard> listTitle(@Param("tTitle") String tTitle);

    @Query("select tb.tBno, tb.tTitle from TradeBoard tb where tb.tTitle like %:tTitle% ")
    List<Object[]> listTitle2(@Param("title") String title);

    @Query("select tb.tBno, tb.Ttitle from TradeBoard tb where tb.tTitle like %:tTitle% ")
    Page<Object[]> listTitle3(@Param("tTitle") String tTitle, Pageable pageable);

    // Update 하는 JPQL
    @Modifying
    @Query("update TradeBoard tb set tb.tTitle = :tTitle where tb.tBno = :tBno")
    int modifyTitle(@Param("tTitle") String tTitle, @Param("tBno") Long tBno);

    Page<TradeBoard> findBytContentContainig(String tContent, org.springframework.data.domain.Pageable pageable);

    // nativeQuery
    // 급할때 쓴다.
    // native Query를 쓰면 DB에 종속되게 되버린다.
    @Query(value = "select * from TradBoard ", nativeQuery=true)
    List<Object[]> listNative();


    // 게시물에 따른 댓글의 갯수 추출
    // 쿼리 만들때 단계적으로 잘라서 해낸다.
    // JPQL를 짤때는 Class를 보고 짜줘야된다.
    
    // @Query("select tb.tBno, tb.tTitle, tb.nickname, count(tr) from TradeBoard tb left outer join TradeReply tr on tr.tboard = b group by b order by b.bno desc")
    // List<Object[]> getListWithRcnt();

    @Query("select tb from TradeBoard tb where tb.tBno = :tBno")
    TradeBoardReadDTO readOne(@Param("tBno")Long tBno);


}
