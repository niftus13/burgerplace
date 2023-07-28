package com.burgerplace.bproduct.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.burgerplace.bproduct.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
    
}
