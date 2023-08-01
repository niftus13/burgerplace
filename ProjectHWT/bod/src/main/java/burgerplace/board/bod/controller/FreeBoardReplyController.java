package burgerplace.board.bod.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import burgerplace.board.bod.dto.FreeBoardListDTO;
import burgerplace.board.bod.dto.PageRequestDTO;
import burgerplace.board.bod.dto.PageResponseDTO;
import burgerplace.board.bod.service.FreeBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin
@RequestMapping("/api/FreeBoar/Reply")
@RequiredArgsConstructor
@Log4j2
public class FreeBoardReplyController {

    FreeBoardService fbService;

    @GetMapping("list")
    public PageResponseDTO<FreeBoardListDTO> list(PageRequestDTO pageRequestDTO){

        log.info("------------------- page처리 컨트롤러에서 전달합니다.");
        log.info((pageRequestDTO));

        return fbService.list(pageRequestDTO);

    }
    
}
