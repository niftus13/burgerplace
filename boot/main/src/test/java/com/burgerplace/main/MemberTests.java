package com.burgerplace.main;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.burgerplace.main.memberPage.entity.MemberEntity;
import com.burgerplace.main.memberPage.repository.MemberEntityRepository;



@SpringBootTest
public class MemberTests {

    @Autowired
    MemberEntityRepository memberRepository;

    @Test
    public void testInsert() {

        MemberEntity member = MemberEntity.builder()
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

        Optional<MemberEntity> result = memberRepository.findById(id);

        MemberEntity member = result.orElseThrow();

        System.out.println(member);
    }
}
