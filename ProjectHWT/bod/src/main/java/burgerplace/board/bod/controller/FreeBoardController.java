package burgerplace.board.bod.controller;



import org.hibernate.annotations.Parameter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import burgerplace.board.bod.dto.FreeBoardDTO;
import burgerplace.board.bod.dto.FreeBoardListDTO;
import burgerplace.board.bod.dto.PageRequestDTO;
import burgerplace.board.bod.dto.PageResponseDTO;
import burgerplace.board.bod.service.FreeBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin
@RequestMapping("/api/FBoard")
@RequiredArgsConstructor
@Log4j2
public class FreeBoardController {

    private final FreeBoardService freeboardService;

    @GetMapping
    public PageResponseDTO<FreeBoardListDTO>list(PageRequestDTO RequestDTO){

        log.info((RequestDTO));

        return freeboardService.listRcnt(RequestDTO);
    }

    @GetMapping("{bno}")
    public FreeBoardDTO get(@PathVariable("bno") Long bno){
        return freeboardService.getOne(bno);
    }
    
}
