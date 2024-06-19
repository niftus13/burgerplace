package com.burgerplace.main.boardPage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.burgerplace.main.boardPage.dto.PageResponseDTO;
import com.burgerplace.main.boardPage.dto.TradeGetReplyDTO;
import com.burgerplace.main.boardPage.dto.TradeReplyDTO;
import com.burgerplace.main.boardPage.dto.TradeReplyPageRequestDTO;

import com.burgerplace.main.boardPage.service.TradeReplyService;
import com.burgerplace.main.common.util.FileUploader;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/treplies")
@RequiredArgsConstructor
@CrossOrigin
@Log4j2
public class TradeReplyController {

        private final TradeReplyService replyService;

        private final FileUploader uploader;

    @GetMapping("/{tradeBno}/list")
    public PageResponseDTO<TradeReplyDTO> list( @PathVariable("tradeBno") Long tradeBno,
            TradeReplyPageRequestDTO requestDTO) {

        log.info("freeBno 매개변수 초기값 ---" + tradeBno); // 제대로 들어와서 반환함

        log.info(requestDTO+" requestDTO list 매개변수 초기값"); // 제대로 들어와서 반환함

        return replyService.list(requestDTO);
    }

    @GetMapping(value = "/{tradeRno}")
    public TradeGetReplyDTO get(@PathVariable("tradeRno") Long tradeRno) {

        log.info("freeRno가");
        log.info(tradeRno+" ReplyController freeRno");

        return replyService.read(tradeRno);
    }

    @PostMapping("/")
    public Map<String, Long> register(TradeGetReplyDTO replyDTO) {

        log.info(replyDTO+" ReplyRegister replyDTO");

        List<String> fileNames = uploader.uploadFiles(replyDTO.getTradeFiles(), true);
        replyDTO.setTradeImages(fileNames);

        Long newRno = replyService.register(replyDTO);

        return Map.of("result", newRno);

    }

    @DeleteMapping("/{tradeRno}")
    public Map<String, Long> remove(@PathVariable("tradeRno") Long tradeRno) {

        log.info(tradeRno+ " ReplyRemove");

        replyService.remove(tradeRno);

        return Map.of("result", tradeRno);
    }

    @PostMapping("modify")
    public Map<String, Long> modify(TradeGetReplyDTO replyDTO) {

        log.info("----------------modify-----------------");
        log.info("----------------modify-----------------");
        log.info("----------------modify-----------------");
        log.info(replyDTO+" modify dto");
        // 기존 파일 및 업로드된 파일까지 추가하는 배열

        // 기존 파일 및 업로드된 파일까지 추가하는 배열
        if (replyDTO.getTradeFiles() != null && replyDTO.getTradeFiles().size() > 0) {

            List<String> uploadFileNames = uploader.uploadFiles(replyDTO.getTradeFiles(), true);

            List<String> oldFileNames = replyDTO.getTradeImages();

            uploadFileNames.forEach(fname -> oldFileNames.add(fname));
        }
        log.info("After............");
        log.info(replyDTO);

        replyService.modify(replyDTO);

        return Map.of("result", replyDTO.getTradeRno());
    }

    
}
