package org.zerock.j1.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.j1.domain.Board;
import org.zerock.j1.dto.BoardReadDTO;
import org.zerock.j1.repository.search.BoardSearch;

// repository생성
// 검색기능구현할거다를 위해 BoardSearch도 붙여준다.
public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
    // repository에 메소드명을 넣으면
    // 자동으로 쿼리 메소드를 만들어낸다.
    // 생각보다 사용되진 않는다 why? 복잡한 쿼리 문을 생성을 하기 힘들기떄문.
    // 실제로 사용되는것은 JPQL을 사용한다.
    List<Board> findByftitleContaining(String ftitle);

    // JPQL 방식 @Query annotation을 넣어준다
    // * 사용 불가 alias사용하거나 entity property사용
    // 대소문자 및 띄어쓰기 주의
    // 변수앞에는 항상 @Param(명칭)
    // DDL은 @Modifying라는 annotation을 붙여줘야된다. => 웬만해선 사용하는 것을지양해야된다.
    // 장점 join 처리시 결과뽑아내는게 좋다.

    @Query("select b from Board b where b.ftitle like %:ftitle% ")
    List<Board> listTitle(@Param("ftitle") String ftitle);


    // JPQL을 사용하는 진짜 이유 : 특정한 column 만 가져올때, 리턴은 Object의 배열이됨.
    @Query("select b.fbno, b.ftitle from Board b where b.ftitle like %:ftitle% ")
    List<Object[]> listTitle2(@Param("ftitle") String ftitle);
    // title=%?% ?에 해당하는 title의 번호와 이름을 가져오는 쿼리이다.
    // 특정 칼럼을 가져온다 => 리턴타입은 Object의 배열이다. -> 공식이다.


    // Page처리 JPQL Page로 Object배열로 받아야됨.
    @Query("select b.fbno, b.ftitle from Board b where b.ftitle like %:ftitle% ")
    Page<Object[]> listTitle3(@Param("ftitle") String ftitle, Pageable pageable);
    // 페이지 처리하는 jpql도 Object 배열로 받아야된다.


    // Update 하는 JPQL
    @Modifying
    @Query("update Board b set b.ftitle = :ftitle where b.fbno = :fbno")
    int modifyTitle(@Param("ftitle") String ftitle, @Param("fbno") Long fbno);
    // 엔티티의 title과 bno를 매개변수로 받고 @Query를 실행한다.
    // :title => 매개변수 title을 말하는 것이다. 내가 변수로 지정할 값을 의미한다.
    // 그래서 test에서 bno와 title의 변수를 만든 것이다.


    // Paging 까지 된 쿼리메소드
    // 마지막 매개변수로 Pageable이 들어가면 Page타입으로 리턴해야되고
    // Paging 을 처리해 준다.
    // order by 까지 지원해준다.
    Page<Board> findByfcontentContaining(String fcontent, org.springframework.data.domain.Pageable pageable);
    // findBy속성명Containing된 메서드에 리턴타입이 page이고 두 번째 인자로 pageable이 들어가면 
    //페이징처리와 orderBy처리까지 해준다. => 페이지 처리하는 간편한 메서드이다.
    // 해석하자면 %content%가 포함된 페이지 처리
    



    // nativeQuery
    // 급할때 쓴다.
    // native Query를 쓰면 DB에 종속되게 되버린다.
    @Query(value = "select * from t_board ", nativeQuery=true)
    List<Object[]> listNative();



    // 게시물에 따른 댓글의 갯수 추출
    // 쿼리 만들때 단계적으로 잘라서 해낸다.
    // JPQL를 짤때는 Class를 보고 짜줘야된다.
    
    @Query("select b.fbno, b.ftitle, b.nickname, count(r) from Board b left outer join Reply r on r.board = b group by b order by b.fbno desc")
    List<Object[]> getListWithRcnt();


    @Query("select b from Board b where b.fbno = :fbno")
    BoardReadDTO readOne(@Param("fbno")Long fbno);
}
