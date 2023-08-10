package com.burgerplace.bproduct.controller;

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

import com.burgerplace.bproduct.dto.PageResponseDTO;
import com.burgerplace.bproduct.dto.ProductReplyDTO;
import com.burgerplace.bproduct.dto.ReplyPageRequestDTO;
import com.burgerplace.bproduct.service.ProductReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
//api 서버란뜻
@RequestMapping("/api/productreplies/")
@RequiredArgsConstructor
//CORS 해결 간단한방법1 annotation 좋은방법은 아니다.
@CrossOrigin
@Log4j2
public class ReplyController {

    private final ProductReplyService replyService;

    @GetMapping("{pno}/list")
    public PageResponseDTO<ProductReplyDTO> list(@PathVariable("pno") Long pno, ReplyPageRequestDTO requestDTO){


        log.info("pno ----" + pno);
        log.info(requestDTO);

        return replyService.list(requestDTO);

    }
    @PostMapping("")
    public Map<String, Long> register( @RequestBody ProductReplyDTO replyDTO){

        log.info("ReplyController.........");
        log.info(replyDTO);

        Long newRno = replyService.register(replyDTO);

        return Map.of("result", newRno);

    }

    @GetMapping("{pRno}")
    public ProductReplyDTO getOne(@PathVariable("pRno") Long pRno) {

        return replyService.read(pRno);        
    }
    @DeleteMapping("{pRno}")
    public Map<String, Long> remove(@PathVariable("pRno") Long pRno){

        replyService.remove(pRno);


        return Map.of("result",pRno);
    }
    @PutMapping("{pRno}")
    public Map<String, Long> modify(@RequestBody ProductReplyDTO replyDTO){

        replyService.modify(replyDTO);

        return Map.of("result", replyDTO.getPRno());
    }

}
