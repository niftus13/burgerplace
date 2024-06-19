package com.burgerplace.main.memberPage.controller;

import java.util.Map;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.burgerplace.main.common.pageDto.PageRequestDTO;
import com.burgerplace.main.common.pageDto.PageResponseDTO;
import com.burgerplace.main.memberPage.dto.MemberDTO;

import com.burgerplace.main.memberPage.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


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
