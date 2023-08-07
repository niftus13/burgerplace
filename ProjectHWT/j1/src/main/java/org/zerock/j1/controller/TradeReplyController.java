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
import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.dto.TradeReplyDTO;
import org.zerock.j1.dto.TradeReplyPageRequestDTO;
import org.zerock.j1.service.TradeReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/replies")
@RequiredArgsConstructor
@CrossOrigin
@Log4j2
public class TradeReplyController {

    private final TradeReplyService replyService;

    @GetMapping("/{tBno}/list")
    public PageResponseDTO<TradeReplyDTO> list(
            @PathVariable("tBno") Long tBno,
            TradeReplyPageRequestDTO requestDTO) {

        log.info("bno ---" + tBno);
        log.info(requestDTO);

        return replyService.list(requestDTO);
    }

    @PostMapping("/")
    public Map<String, Long> register(@RequestBody TradeReplyDTO replyDTO) {

        log.info("ReplyController................");
        log.info(replyDTO);

        Long newRno = replyService.register(replyDTO);

        return Map.of("result", newRno);

    }

    @GetMapping(value = "/{tRno}")
    public TradeReplyDTO get(@PathVariable("tRno") Long tRno) {
        return replyService.read(tRno);
    }

    @DeleteMapping("/{rno}")
    public Map<String, Long> remove(@PathVariable("tRno") Long tRno) {

        replyService.remove(tRno);

        return Map.of("result", tRno);
    }

    @PutMapping("/{rno}")
    public Map<String, Long> modify(@RequestBody TradeReplyDTO replyDTO) {

        replyService.modify(replyDTO);

        return Map.of("result", replyDTO.getTRno());
    }

}