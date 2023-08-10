package com.burgerplace.member.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.burgerplace.member.dto.MemberReadDTO;
import com.burgerplace.member.entity.Member;
import com.burgerplace.member.repository.search.MemberSearch;

public interface MemberRepository extends JpaRepository<Member, String>, MemberSearch {
    
    Member findByNickname(String nickname);

    List<Member> findByAdmin(boolean admin);

    // id 값 문자열 조회
    @Query("select m from Member m where m.nickname like %:nickname% ")
    List<Member> listNickname(@Param("nickname") String nickname);

    // 특정 조건만 조회
    @Query("select m.nickname, m.id from Member m where m.nickname like %:nickname% ")
    List<Object[]> listNickname2(@Param("nickname") String nickname);

    // Page처리 JPQL Page로 Object배열로 받아야됨.
    @Query("select m.nickname, m.id from Member m where m.id like %:nickname% ")
    Page<Object[]> listNickname3(@Param("nickname") String nickname, Pageable pageable);

    // Update 하는 JPQL
    @Modifying
    @Query("update Member m set m.nickname = :nickname where m.id = :id")
    int modifyNickname(@Param("nickname") String nickname, @Param("id") String id);

    // Paging 까지 된 쿼리메소드
    // 마지막 매개변수로 Pageable이 들어가면 Page타입으로 리턴해야되고
    // Paging 을 처리해 준다.
    // order by 까지 지원해준다.
    Page<Member> findByNicknameContaining(String nickname, Pageable pageable);

    // nativeQuery
    // 급할때 쓴다.
    // native Query를 쓰면 DB에 종속되게 되버린다.
    @Query(value = "select * from member ", nativeQuery=true)
    List<Object[]> listNative();

    @Query("select m from Member m where m.id = :id")
    MemberReadDTO readOne(@Param("id")String id);

}
