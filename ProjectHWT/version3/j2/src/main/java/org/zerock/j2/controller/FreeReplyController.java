package org.zerock.j2.controller;

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
import org.zerock.j2.dto.FreeReplyDTO;
import org.zerock.j2.dto.FreeReplyPageRequestDTO;
import org.zerock.j2.dto.PageResponseDTO;
import org.zerock.j2.service.FreeReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/replies")
@RequiredArgsConstructor
@CrossOrigin
@Log4j2
public class FreeReplyController {

    private final FreeReplyService replyService;

    @GetMapping("/{freeBno}/list")
    public PageResponseDTO<FreeReplyDTO> list( @PathVariable("freeBno") Long freeBno,
            FreeReplyPageRequestDTO requestDTO) {

        log.info("freeBno 매개변수 초기값 ---" + freeBno); // 제대로 들어와서 반환함

        log.info(requestDTO+" requestDTO list 매개변수 초기값"); // 제대로 들어와서 반환함

        return replyService.list(requestDTO);
    }

    @PostMapping("/")
    public Map<String, Long> register(@RequestBody FreeReplyDTO replyDTO) {

        log.info(replyDTO+" ReplyRegister replyDTO");

        Long newRno = replyService.register(replyDTO);

        return Map.of("result", newRno);

    }

    @GetMapping(value = "/{freeRno}")
    public FreeReplyDTO get(@PathVariable("freeRno") Long freeRno) {

        log.info("freeRno가");
        log.info(freeRno+" ReplyController freeRno");

        return replyService.read(freeRno);
    }

    @DeleteMapping("/{freeRno}")
    public Map<String, Long> remove(@PathVariable("freeRno") Long freeRno) {

        log.info(freeRno+ " ReplyRemove");

        replyService.remove(freeRno);

        return Map.of("result", freeRno);
    }

    @PutMapping("/{freeRno}")
    public Map<String, Long> modify(@RequestBody FreeReplyDTO replyDTO) {

        log.info(replyDTO+ " ReplyModify");

        replyService.modify(replyDTO);

        return Map.of("result", replyDTO.getFreeRno());
    }

}