package com.burgerplace.main.memberPage.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.burgerplace.main.memberPage.dto.MemberDTO;
import com.burgerplace.main.memberPage.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;



@RestController
@RequestMapping("/api/member/")
@Log4j2
@CrossOrigin
@RequiredArgsConstructor
public class MemberController {



    // 회원 가입 부분

    private final MemberService memberService;

    // 회원 가입 폼을 보여주는 페이지
    @GetMapping("/register")
    public String showRegisterForm() {
        return null; 
    }

    // 회원 가입 처리
    @PostMapping("/register")
    public String registerMember(MemberDTO memberDTO) {
        memberService.register(memberDTO); // 회원 가입 서비스 호출
        return null; 

    }
}
