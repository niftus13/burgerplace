package org.zerock.j1.controller;

import org.springdoc.core.annotations.ParameterObject;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.j1.dto.FreeBoardDTO;
import org.zerock.j1.dto.FreeBoardListRcntDTO;
import org.zerock.j1.dto.PageRequestDTO;
import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.service.FreeBoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/board/")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin
public class BoardController {
    
    private final FreeBoardService fbService;

    @GetMapping(value ="/list")
    public PageResponseDTO<FreeBoardListRcntDTO> list (@ParameterObject  PageRequestDTO requestDTO){

        log.info(requestDTO);

        return fbService.listRcnt(requestDTO);
        
    }
    
    @GetMapping("{fBno}")
    public FreeBoardDTO get(@PathVariable("fBno") Long fBno){

        return  fbService.getOne(fBno);
    }
}
