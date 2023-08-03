package burgerplace.board.bod.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import burgerplace.board.bod.dto.FreeReplyDTO;
import burgerplace.board.bod.dto.PageResponseDTO;
import burgerplace.board.bod.dto.ReplyPageRequestDTO;
import burgerplace.board.bod.service.FreeReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/replies")
public class FreeReplyController {

    private final FreeReplyService replyService;

    @GetMapping("/{fBno}/list")
    public PageResponseDTO<FreeReplyDTO> list(
            @PathVariable("fBno") Long fBno,
            ReplyPageRequestDTO requestDTO) {

        log.info("bno ---" + fBno);
        log.info(requestDTO);

        return replyService.list(requestDTO);
    }

    @PostMapping("/")
    public Map<String, Long> register(@RequestBody FreeReplyDTO replyDTO) {

        log.info("ReplyController................");
        log.info(replyDTO);

        Long newRno = replyService.register(replyDTO);

        return Map.of("result", newRno);

    }

    @GetMapping(value = "/{fRno}")
    public FreeReplyDTO get(@PathVariable("rno") Long rno) {
        return replyService.read(rno);
    }

    @DeleteMapping("/{fRno}")
    public Map<String, Long> remove(@PathVariable("rno") Long rno) {

        replyService.remove(rno);

        return Map.of("result", rno);
    }

    @PutMapping("/{fRno}")
    public Map<String, Long> modify(@RequestBody FreeReplyDTO replyDTO) {

        replyService.modify(replyDTO);

        return Map.of("result", replyDTO.getFRno());
    }

}
