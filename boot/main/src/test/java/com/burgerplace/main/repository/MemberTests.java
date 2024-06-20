package com.burgerplace.main.repository;

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
    public  void testInsert(){

        MemberEntity member = MemberEntity.builder()
                .id("user01@gmail.com")
                .pw("1111")
                .nickname("USER00")
                .admin(true)
                .build();

        memberRepository.save(member);
    }
    @Test
    public  void testRead(){
        String email = "user01@gmail.com";

        Optional<MemberEntity> result = memberRepository.findById(email);

        MemberEntity member = result.orElseThrow();

        System.out.println(member);
    }
}
