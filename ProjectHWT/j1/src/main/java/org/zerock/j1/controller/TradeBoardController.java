package org.zerock.j1.controller;

import org.springdoc.core.annotations.ParameterObject;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.j1.dto.PageRequestDTO;
import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.dto.TradeBoardDTO;
import org.zerock.j1.dto.TradeBoardListRcntDTO;
import org.zerock.j1.service.TradeBoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/tboard/")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin
public class TradeBoardController {
    
    private final TradeBoardService boardService;

    @GetMapping(value ="/list")
    public PageResponseDTO<TradeBoardListRcntDTO> list (@ParameterObject  PageRequestDTO requestDTO){

        log.info(requestDTO);

        return boardService.listRcnt(requestDTO);
        
    }
    
    @GetMapping("{tBno}")
    public TradeBoardDTO get(@PathVariable("tBno") Long tBno){

        return  boardService.getOne(tBno);
    }
}
