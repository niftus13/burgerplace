package burgerplace.board.bod.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import burgerplace.board.bod.entity.FreeBoard;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Integer> {

    // repository에 메소드명을 넣으면
    // 자동으로 쿼리 메소드를 만들어낸다.
    // 생각보다 사용되진 않는다 why? 복잡한 쿼리 문을 생성을 하기 힘들기떄문.
    // 실제로 사용되는것은 JPQL을 사용한다.
    List<FreeBoard> findByTitleContaining(String fTitle);

    @Query("select fb from FreeBoard fb where fb.fTitle like %:fTitle% ")
    List<FreeBoard> listTitle(@Param("title") String title);



    // JPQL을 사용하는 진짜 이유 : 특정한 column 만 가져올때, 리턴은 Object의 배열이됨.
    @Query("select fb.fBno, fb.fTitle from FreeBoard fb where fb.fTitle like %:fTitle% ")
    List<Object[]> listTitle2(@Param("fTitle") String fTitle);


    // Page처리 JPQL Page로 Object배열로 받아야됨.
    @Query("select fb.fBno, fb.fTitle from FreeBoard fb where fb.fTitle like %:fTitle% ")
    Page<Object[]> listTitle3(@Param("fTitle") String fTitle, Pageable pageable);


    // Update 하는 JPQL
    @Modifying
    @Query("update FreeBoard fb set fb.fTitle = :fTitle where fb.fBno = :fBno")
    int modifyTitle(@Param("fTitle") String fTitle, @Param("fBno") Long fBno);

    // Paging 까지 된 쿼리메소드
    // 마지막 매개변수로 Pageable이 들어가면 Page타입으로 리턴해야되고
    // Paging 을 처리해 준다.
    // order by 까지 지원해준다.
    Page<FreeBoard> findByContentContaining(String fContent, org.springframework.data.domain.Pageable pageable);

    // nativeQuery
    // 급할때 쓴다.
    // native Query를 쓰면 DB에 종속되게 되버린다.
    // @Query(value = "select * from t_board ", nativeQuery=true)
    // List<Object[]> listNative();



    // 게시물에 따른 댓글의 갯수 추출
    // 쿼리 만들때 단계적으로 잘라서 해낸다.
    // JPQL를 짤때는 Class를 보고 짜줘야된다.
    
    @Query("select b.bno, b.title, b.writer, count(r) from Board b left outer join Reply r on r.board = b group by b order by b.bno desc")
    List<Object[]> getListWithRcnt();


    // @Query("select b from Board b where b.bno = :bno")
    // BoardReadDTO readOne(@Param("bno")Long bno);
    
}
