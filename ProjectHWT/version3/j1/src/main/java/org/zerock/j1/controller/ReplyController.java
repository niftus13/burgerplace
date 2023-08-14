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
import org.zerock.j1.dto.ReplyDTO;
import org.zerock.j1.dto.ReplyPageRequestDTO;
import org.zerock.j1.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/replies")
@RequiredArgsConstructor
@CrossOrigin
@Log4j2
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping("/{fbno}/list")
    public PageResponseDTO<ReplyDTO> list(
            @PathVariable("fbno") Long fbno,
            ReplyPageRequestDTO requestDTO) {

        log.info("bno ---" + fbno);
        log.info(requestDTO+" requestDTO list "); 
        // list === requestDTO? 만약 requestDTO의 log가 bno,page,last가 들어오면 true
        // axios 통신으로 가져오는 것은 bno=100, page=1, last=false size=20(builder.default값으로 20)

        return replyService.list(requestDTO);
    }

    @PostMapping("/")
    public Map<String, Long> register(@RequestBody ReplyDTO replyDTO) {

        log.info("ReplyController................");
        log.info(replyDTO);

        Long newRno = replyService.register(replyDTO);

        return Map.of("result", newRno);

    }

    @GetMapping(value = "/{frno}")
    public ReplyDTO get(@PathVariable("frno") Long frno) {
        return replyService.read(frno);
    }

    @DeleteMapping("/{frno}")
    public Map<String, Long> remove(@PathVariable("frno") Long frno) {

        replyService.remove(frno);

        return Map.of("result", frno);
    }

    @PutMapping("/{frno}")
    public Map<String, Long> modify(@RequestBody ReplyDTO replyDTO) {

        replyService.modify(replyDTO);

        return Map.of("result", replyDTO.getFrno());
    }

}