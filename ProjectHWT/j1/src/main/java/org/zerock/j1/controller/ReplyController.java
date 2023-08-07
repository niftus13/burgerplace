package org.zerock.j1.controller;

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
import org.zerock.j1.dto.FreeReplyDTO;
import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.dto.ReplyPageRequestDTO;
import org.zerock.j1.service.FreeReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/replies")
@RequiredArgsConstructor
@CrossOrigin
@Log4j2
public class ReplyController {

    private final FreeReplyService frService;

    @GetMapping("/{fBno}/list")
    public PageResponseDTO<FreeReplyDTO> list(
            @PathVariable("fBno") Long fBno,
            ReplyPageRequestDTO requestDTO) {

        log.info("bno ---" + fBno);
        log.info(requestDTO);

        return frService.list(requestDTO);
    }

    @PostMapping("/")
    public Map<String, Long> register(@RequestBody FreeReplyDTO replyDTO) {

        log.info("ReplyController................");
        log.info(replyDTO);

        Long newRno = frService.register(replyDTO);

        return Map.of("result", newRno);

    }

    @GetMapping(value = "/{fRno}")
    public FreeReplyDTO get(@PathVariable("fRno") Long fRno) {
        return frService.read(fRno);
    }

    @DeleteMapping("/{fRno}")
    public Map<String, Long> remove(@PathVariable("fRno") Long fRno) {

        frService.remove(fRno);

        return Map.of("result", fRno);
    }

    @PutMapping("/{fRno}")
    public Map<String, Long> modify(@RequestBody FreeReplyDTO replyDTO) {

        frService.modify(replyDTO);

        return Map.of("result", replyDTO.getFRno());
    }

}