package com.burgerplace.member.controller;

import java.util.List;
import java.util.Map;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("modify")
    public Map<String, String> modify(MemberDTO memberDTO) {
        
        log.info("----------------------modify---------------");
        log.info("----------------------modify---------------");
        log.info("----------------------modify---------------");
        log.info(memberDTO);

        memberService.modify(memberDTO);

        return Map.of("result", memberDTO.getId());
    }

    @DeleteMapping("{id}")
    public Map<String, String> delete(@PathVariable("id") String id) {

        log.info("id.........." + id);
        memberService.remove(id);

        return Map.of("result", id);
    }
    
}
