package com.burgerplace.main.mainPage.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.burgerplace.main.mainPage.dto.MainReadDTO;
import com.burgerplace.main.mainPage.entity.Main;
import com.burgerplace.main.mainPage.repository.search.MainSearch;

public interface MainRepository extends JpaRepository<Main, Long>, MainSearch{


    @Query("select b from Board b where b.bno = :bno")
    MainReadDTO readOne(@Param("bno") Long bno);

    List<Main> findByTitleContaining(String title);

    @Query("select b from Board b where b.title like %:title% ")
    List<Main> listTitle(@Param("title") String title);

    @Query("select b.bno, b.title from Board b where b.title like %:title% ")
    List<Object[]> listTitle2(@Param("title") String title);

    @Query("select b.bno, b.title from Board b where b.title like %:title%")
    Page<Object[]> listTitle3(@Param("title")String title, Pageable pageable);

    @Modifying
    @Query("update Board b set b.title = :title where b.bno = :bno")
    int modifyTitle(@Param("title") String title, @Param("bno") Long bno);

    Page<Main> findByContentContaining(String content, Pageable pageable);

    @Query(value = "select * from t_board", nativeQuery = true)
    List<Object[]> listNative();

    @Query("select b.bno, b.title, b.writer, count(r) from Board b left outer join Reply r on r.board = b  group by b order by b.bno desc")
    List<Object[]> getListWithRcnt();
    
}
