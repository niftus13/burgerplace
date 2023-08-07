package com.burgerplace.member.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.burgerplace.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
    
    Member findByNickname(String nickname);

    List<Member> findByAdmin(boolean admin);
}
