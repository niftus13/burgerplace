package org.zerock.j2.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.zerock.j2.entity.FreeBoard;
import org.zerock.j2.repository.search.FreeBoardSearch;

public interface FreeBoardRepository extends JpaRepository <FreeBoard, Long>, FreeBoardSearch {


    List<FreeBoard> findByFreeTitleContaining(String freeTitle);


    @Query("select fb from FreeBoard fb where fb.freeTitle like %:freeTitle% ")
    List<FreeBoard> listTitle(@Param("freeTitle") String freeTitle);


    @Query("select fb.freeBno, fb.freeTitle from FreeBoard fb where fb.freeTitle like %:freeTitle% ")
    List<Object[]> listTitle2(@Param("freeTitle") String freeTitle);


    @Query("select fb.freeBno, fb.freeTitle from FreeBoard fb where fb.freeTitle like %:freeTitle% ")
    Page<Object[]> listTitle3(@Param("freeTitle") String freeTitle, Pageable pageable);


    @Modifying
    @Query("update FreeBoard fb set fb.freeTitle = :freeTitle where fb.freeBno = :freeBno")
    int modifyTitle(@Param("freeTitle") String freeTitle, @Param("freeBno") Long freeBno);


    Page<FreeBoard> findByFreeContentContaining(String freeContent, org.springframework.data.domain.Pageable pageable);

    

    @Query(value = "select * from free_board ", nativeQuery=true)
    List<Object[]> listNative();



    // 게시물에 따른 댓글의 갯수 추출
    // 쿼리 만들때 단계적으로 잘라서 해낸다.
    // JPQL를 짤때는 Class를 보고 짜줘야된다.
    
    @Query("select b.freeBno, b.freeTitle, b.nickname, count(r) from FreeBoard b left outer join FreeReply r on r.freeBoard = b group by b order by b.freeBno desc")
    List<Object[]> getListWithRcnt();

}
