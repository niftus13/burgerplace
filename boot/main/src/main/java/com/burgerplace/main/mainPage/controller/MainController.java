package com.burgerplace.main.mainPage.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.burgerplace.main.common.pageDto.PageRequestDTO;
import com.burgerplace.main.common.pageDto.PageResponseDTO;
import com.burgerplace.main.mainPage.dto.MainDTO;
import com.burgerplace.main.mainPage.dto.MainListRcntDTO;
import com.burgerplace.main.mainPage.service.MainService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@RestController
@RequestMapping("/api/main")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin
public class MainController {
    
    private final MainService mainService;

    @GetMapping(value = "/list")
    public PageResponseDTO<MainListRcntDTO> list(@ParameterObject PageRequestDTO requestDTO) {
        
        log.info(requestDTO);

        return mainService.listRcnt(requestDTO);
    }

    @GetMapping("/{bno}")
    public MainDTO get(@PathVariable("bno") Long bno) {

        return mainService.getOne(bno);
    }
    
}
