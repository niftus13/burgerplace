package com.burgerplace.member;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.burgerplace.member.entity.Member;
import com.burgerplace.member.repository.MemberRepository;

@SpringBootTest
public class MemberTests {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testInsert() {

        Member member = Member.builder()
                .id("Bazzi@nexon.com")
                .pw("1111")
                .admin(true)
                .nickname("Bazzi")
                .build();

        memberRepository.save(member);
    }

    @Test
    public void testRead() {
        String id = "Bazzi@nexon.com";

        Optional<Member> result = memberRepository.findById(id);

        Member member = result.orElseThrow();

        System.out.println(member);
    }
}
