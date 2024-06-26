package com.burgerplace.main.salePage.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.burgerplace.main.salePage.domain.SalesEntity;



// repository생성
// 검색기능구현할거다를 위해 BoardSearch도 붙여준다.
public interface SalesBoardRepository extends JpaRepository<SalesEntity, Long> {
 
    // repository에 메소드명을 넣으면
    // 자동으로 쿼리 메소드를 만들어낸다.
    // 생각보다 사용되진 않는다 why? 복잡한 쿼리 문을 생성을 하기 힘들기떄문.
    // 실제로 사용되는것은 JPQL을 사용한다.
    List<SalesEntity> findByTitleContaining(String title);

    // JPQL 방식 @Query annotation을 넣어준다
    // * 사용 불가 alias사용하거나 entity property사용
    // 대소문자 및 띄어쓰기 주의
    // 변수앞에는 항상 @Param(명칭)
    // DDL은 @Modifying라는 annotation을 붙여줘야된다. => 웬만해선 사용하는 것을지양해야된다.
    // 장점 join 처리시 결과뽑아내는게 좋다.
    
    @Query("select b from Board b where b.title like %:title% ")
    List<SalesEntity> listTitle(@Param("title") String title);

    // JPQL을 사용하는 진짜 이유 : 특정한 column 만 가져올때, 리턴은 Object의 배열이됨.
    @Query("select b.bno, b.title from Board b where b.title like %:title% ")
    List<Object[]> listTitle2(@Param("title") String title);

    // Page처리 JPQL Page로 Object배열로 받아야됨.
    @Query("select b.bno, b.title from Board b where b.title like %:title% ")
    Page<Object[]> listTitle3(@Param("title") String title, Pageable pageable);

    // Update 하는 JPQL
    @Modifying
    @Query("update Board b set b.title = :title where b.bno = :bno")
    int modifyTitle(@Param("title") String title, @Param("bno") Long pEno);

    // Paging 까지 된 쿼리메소드
    // 마지막 매개변수로 Pageable이 들어가면 Page타입으로 리턴해야되고
    // Paging 을 처리해 준다.
    // order by 까지 지원해준다.
    Page<SalesEntity> findByContentContaining(String eventInfo, Pageable pageable);

    // nativeQuery
    // 급할때 쓴다.
    // native Query를 쓰면 DB에 종속되게 되버린다.
    @Query(value = "select * from Product_Event", nativeQuery=true)
    List<Object[]> listNative();

}
