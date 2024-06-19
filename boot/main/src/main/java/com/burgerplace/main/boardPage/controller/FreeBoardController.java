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

import com.burgerplace.main.boardPage.dto.FreeBoardListRcntDTO;
import com.burgerplace.main.boardPage.dto.FreeGetBoardDTO;
import com.burgerplace.main.boardPage.dto.PageRequestDTO;
import com.burgerplace.main.boardPage.dto.PageResponseDTO;
import com.burgerplace.main.boardPage.service.FreeBoardService;
import com.burgerplace.main.common.util.FileUploader;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/board/")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin
public class FreeBoardController {

    private final FreeBoardService boardService;

    private final FileUploader uploader;

    @GetMapping(value = "/list")
    public PageResponseDTO<FreeBoardListRcntDTO> list(@ParameterObject PageRequestDTO requestDTO) {

        log.info(requestDTO + "FreeBoard Request DTO");

        return boardService.listRcnt(requestDTO);

    }

    @GetMapping("{freeBno}")
    public FreeGetBoardDTO get(@PathVariable("freeBno") Long freeBno) {

        log.info(freeBno + "Controller BNO");

        return boardService.getOne(freeBno);
    } // 로직 이해 완료


    @PostMapping("/")
    public Map<String, Long> postBoard(FreeGetBoardDTO boardDTO) {

        log.info(boardDTO);

        List<String> fileNames = uploader.uploadFiles(boardDTO.getFreeFiles(), true);
        boardDTO.setFreeImages(fileNames);

        Long newBno = boardService.Register(boardDTO);

        return Map.of("result", newBno);
    }


    @DeleteMapping("{freeBno}")
    public Map<String, Long> remove(@PathVariable("freeBno") Long freeBno) {

        log.info(freeBno + " ReplyRemove");

        boardService.remove(freeBno);

        return Map.of("result", freeBno);
    }

    @PostMapping("modify")
    public Map<String, Long> modify(FreeGetBoardDTO boardDTO) {

        log.info("----------------modify-----------------");
        log.info("----------------modify-----------------");
        log.info("----------------modify-----------------");
        log.info(boardDTO);
        // 기존 파일 및 업로드된 파일까지 추가하는 배열

        // 기존 파일 및 업로드된 파일까지 추가하는 배열
        if (boardDTO.getFreeFiles() != null && boardDTO.getFreeFiles().size() > 0) {

            List<String> uploadFileNames = uploader.uploadFiles(boardDTO.getFreeFiles(), true);

            List<String> oldFileNames = boardDTO.getFreeImages();

            uploadFileNames.forEach(fname -> oldFileNames.add(fname));
        }
        log.info("After............");
        log.info(boardDTO);

        boardService.modify(boardDTO);

        return Map.of("result", boardDTO.getFreeBno());
    }
}
