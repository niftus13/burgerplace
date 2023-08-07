package com.burgerplace.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.burgerplace.member.entity.Member;
import com.burgerplace.member.repository.MemberRepository;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/admin")
@Log4j2
public class AdminController {

    private final MemberRepository memberRepository;

    @Autowired
    public AdminController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("members")
    public List<Member> listMembers(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        log.info("members:" + members);
        return members;
    }
}
