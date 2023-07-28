package board.free.project1.controller;


import org.springdoc.core.annotations.ParameterObject;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        // 파라미터가 객체형식으로 모두 들어오는 것을 받아준다.
        // 일단 requestDTO가 들어온다.
        log.info(requestDTO);
        // 한번 찍어보렴
        // page=1, size=10, keyword=null, type=null

        log.info(boardService.listRcnt(requestDTO)+"홍원태 서비스 리스트 리퀘스트디트오 전사");

        return boardService.listRcnt(requestDTO);
        // 이제 service의 listRcnt에 requestDTO를 넣자고 그 결과를 리턴하자.
        
        
    }

    @GetMapping("{bno}")
    public BoardDTO get(@PathVariable("bno") Long bno){

        return  boardService.getOne(bno);
    }

}
