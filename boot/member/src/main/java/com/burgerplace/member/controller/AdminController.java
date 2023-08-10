package com.burgerplace.member.controller;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.burgerplace.member.dto.MemberDTO;
import com.burgerplace.member.dto.PageRequestDTO;
import com.burgerplace.member.dto.PageResponseDTO;
import com.burgerplace.member.entity.Member;
import com.burgerplace.member.repository.MemberRepository;
import com.burgerplace.member.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/admin")
@Log4j2
@CrossOrigin
@RequiredArgsConstructor
public class AdminController {

    // private final MemberRepository memberRepository;

    // @Autowired
    // public AdminController(MemberRepository memberRepository) {
    //     this.memberRepository = memberRepository;
    // }

    // @GetMapping("members")
    // public List<Member> listMembers(Model model) {
    //     List<Member> members = memberRepository.findAll();
    //     model.addAttribute("members", members);
    //     log.info("members:" + members);
    //     return members;
    // }

    private final MemberService memberService;

    @GetMapping(value = "/list")
    public PageResponseDTO<MemberDTO> list (@ParameterObject PageRequestDTO requestDTO) {

        log.info(requestDTO);

        return memberService.listRcnt(requestDTO);
    }

    @GetMapping("{id}")
    public MemberDTO get(@PathVariable("id") String id) {

        return memberService.getOne(id);
    }
    
}
