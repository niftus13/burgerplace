package board.free.project1.controller;

import java.util.Map;

import org.springdoc.core.annotations.ParameterObject;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import board.free.project1.dto.BoardDTO;
import board.free.project1.dto.BoardListRcntDTO;
import board.free.project1.dto.PageRequestDTO;
import board.free.project1.dto.PageResponseDTO;
import board.free.project1.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/board/")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin
public class BoardController {
    
    private final BoardService boardService;

    @GetMapping(value ="/list")
    public PageResponseDTO<BoardListRcntDTO> list (@ParameterObject  PageRequestDTO requestDTO){

        log.info(requestDTO);

        return boardService.listRcnt(requestDTO);
        
    }
    @GetMapping("{bno}")
    public BoardDTO get(@PathVariable("bno") Long bno){

        return  boardService.getOne(bno);
    }

    @PostMapping("")
    public Map<String, Long> register( @RequestBody BoardDTO boardDTO){

        log.info("boardController.........");
        log.info(boardDTO);

        Long newBno = boardService.register(boardDTO);

        return Map.of("result", newBno);

    }
}
