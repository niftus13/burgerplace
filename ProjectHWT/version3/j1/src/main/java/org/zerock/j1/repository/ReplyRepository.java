package org.zerock.j1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.j1.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long>{
    @Query("select r from Reply r where r.board.fbno = :fbno ")
    Page<Reply> listBoard(@Param("fbno") Long fbno, Pageable pageable);

    // 게시글의 댓글수 가져오는것
    @Query("select count(r) from Reply r where r.board.fbno = :fbno ")
    long getCountBoard(@Param("fbno") Long fbno);
    // 이 로직은 외래키의 bno의 count를 세어 전달해준다.
}
