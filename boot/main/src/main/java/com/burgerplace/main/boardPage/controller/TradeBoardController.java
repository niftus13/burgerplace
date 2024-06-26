package com.burgerplace.main.boardPage.controller;

import java.util.List;
import java.util.Map;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.burgerplace.main.boardPage.dto.PageRequestDTO;
import com.burgerplace.main.boardPage.dto.PageResponseDTO;
import com.burgerplace.main.boardPage.dto.TradeBoardListRcntDTO;
import com.burgerplace.main.boardPage.dto.TradeGetBoardDTO;
import com.burgerplace.main.boardPage.service.TradeBoardService;
import com.burgerplace.main.common.util.FileUploader;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/tboard/")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin
public class TradeBoardController {

    
    private final TradeBoardService boardService;

    private final FileUploader uploader;

    @GetMapping(value = "/list")
    public PageResponseDTO<TradeBoardListRcntDTO> list(@ParameterObject PageRequestDTO requestDTO) {

        log.info(requestDTO + "FreeBoard Request DTO");

        return boardService.listRcnt(requestDTO);

    }

    @GetMapping("{tradeBno}")
    public TradeGetBoardDTO get(@PathVariable("tradeBno") Long tradeBno) {

        log.info(tradeBno + "Controller BNO");

        return boardService.getOne(tradeBno);
    } // 로직 이해 완료

    
    @PostMapping("/")
    public Map<String, Long> postBoard(TradeGetBoardDTO boardDTO) {

        log.info(boardDTO+" tradeBoard dto");

        List<String> fileNames = uploader.uploadFiles(boardDTO.getTradeFiles(), true);
        boardDTO.setTradeImages(fileNames);

        log.info(fileNames+" tradeBoardDTO fileNames");

        Long newBno = boardService.Register(boardDTO);

        log.info(newBno+" tBoard newBno");

        return Map.of("result", newBno);
    }

    @DeleteMapping("{tradeBno}")
    public Map<String, Long> remove(@PathVariable("tradeBno") Long tradeBno) {

        log.info(tradeBno + " ReplyRemove");

        boardService.remove(tradeBno);

        return Map.of("result", tradeBno);
    }

    @PostMapping("modify")
    public Map<String, Long> modify(TradeGetBoardDTO boardDTO) {

        log.info("----------------modify-----------------");
        log.info("----------------modify-----------------");
        log.info("----------------modify-----------------");
        log.info(boardDTO);
        // 기존 파일 및 업로드된 파일까지 추가하는 배열

        // 기존 파일 및 업로드된 파일까지 추가하는 배열
        if (boardDTO.getTradeFiles() != null && boardDTO.getTradeFiles().size() > 0) {

            List<String> uploadFileNames = uploader.uploadFiles(boardDTO.getTradeFiles(), true);

            List<String> oldFileNames = boardDTO.getTradeImages();

            uploadFileNames.forEach(fname -> oldFileNames.add(fname));
        }
        log.info("After............");
        log.info(boardDTO);

        boardService.modify(boardDTO);

        return Map.of("result", boardDTO.getTradeBno());
    }
    
}
