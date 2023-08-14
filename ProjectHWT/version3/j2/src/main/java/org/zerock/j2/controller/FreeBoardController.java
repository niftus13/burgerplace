package org.zerock.j2.controller;

import java.util.Map;

import org.springdoc.core.annotations.ParameterObject;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.j2.dto.FreeBoardDTO;
import org.zerock.j2.dto.FreeBoardListRcntDTO;
import org.zerock.j2.dto.FreeGetBoardDTO;
import org.zerock.j2.dto.PageRequestDTO;
import org.zerock.j2.dto.PageResponseDTO;
import org.zerock.j2.service.FreeBoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/board/")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin
public class FreeBoardController {
    
    private final FreeBoardService boardService;

    @GetMapping(value ="/list")
    public PageResponseDTO<FreeBoardListRcntDTO> list (@ParameterObject  PageRequestDTO requestDTO){

        log.info(requestDTO+"FreeBoard Request DTO");

        return boardService.listRcnt(requestDTO);
        
    }

    @GetMapping("{freeBno}")
    public FreeGetBoardDTO get(@PathVariable("freeBno") Long freeBno){

        log.info(freeBno+"Controller BNO");

        return  boardService.getOne(freeBno);
    } // 로직 이해 완료


    @PostMapping("/")
    public Map<String, Long> postBoard(@RequestBody FreeGetBoardDTO boardDTO){

        log.info(boardDTO);

        Long newBno = boardService.Register(boardDTO);

        return Map.of("result", newBno);
    }

    @DeleteMapping("{freeBno}")
    public Map<String, Long> remove(@PathVariable("freeBno") Long freeBno) {

        log.info(freeBno+ " ReplyRemove");

        boardService.remove(freeBno);

        return Map.of("result", freeBno);
    }

    @PutMapping("{freeBno}")
    public Map<String, Long> modify(@RequestBody FreeBoardDTO boardDTO) {

        log.info(boardDTO+ " freeModify");

        boardService.modify(boardDTO);

        return Map.of("result", boardDTO.getFreeBno());
    }
}
