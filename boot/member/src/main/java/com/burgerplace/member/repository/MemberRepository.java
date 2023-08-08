package com.burgerplace.member.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.burgerplace.member.entity.Member;
import com.burgerplace.member.repository.search.MemberSearch;

public interface MemberRepository extends JpaRepository<Member, String>, MemberSearch {
    
    Member findByNickname(String nickname);

    List<Member> findByAdmin(boolean admin);
}
