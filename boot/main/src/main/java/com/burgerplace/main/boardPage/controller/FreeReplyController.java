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
import com.burgerplace.main.boardPage.dto.FreeGetReplyDTO;
import com.burgerplace.main.boardPage.dto.FreeReplyDTO;
import com.burgerplace.main.boardPage.dto.FreeReplyPageRequestDTO;
import com.burgerplace.main.boardPage.dto.PageResponseDTO;
import com.burgerplace.main.boardPage.service.FreeReplyService;
import com.burgerplace.main.common.util.FileUploader;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/replies")
@RequiredArgsConstructor
@CrossOrigin
@Log4j2
public class FreeReplyController {

    private final FreeReplyService replyService;

        private final FileUploader uploader;


    @GetMapping("/{freeBno}/list")
    public PageResponseDTO<FreeReplyDTO> list( @PathVariable("freeBno") Long freeBno,
            FreeReplyPageRequestDTO requestDTO) {

        log.info("freeBno 매개변수 초기값 ---" + freeBno); // 제대로 들어와서 반환함

        log.info(requestDTO+" requestDTO list 매개변수 초기값"); // 제대로 들어와서 반환함

        return replyService.list(requestDTO);
    }

    
    @GetMapping(value = "/{freeRno}")
    public FreeGetReplyDTO get(@PathVariable("freeRno") Long freeRno) {

        log.info("freeRno가");
        log.info(freeRno+" ReplyController freeRno");

        return replyService.read(freeRno);
    }


    @PostMapping("/")
    public Map<String, Long> register(FreeGetReplyDTO replyDTO) {

        log.info(replyDTO+" ReplyRegister replyDTO");

        List<String> fileNames = uploader.uploadFiles(replyDTO.getFreeFiles(), true);
        replyDTO.setFreeImages(fileNames);

        Long newRno = replyService.register(replyDTO);

        return Map.of("result", newRno);

    }


    @DeleteMapping("/{freeRno}")
    public Map<String, Long> remove(@PathVariable("freeRno") Long freeRno) {

        log.info(freeRno+ " ReplyRemove");

        replyService.remove(freeRno);

        return Map.of("result", freeRno);
    }

    @PostMapping("modify")
    public Map<String, Long> modify(FreeGetReplyDTO replyDTO) {

        log.info("----------------modify-----------------");
        log.info("----------------modify-----------------");
        log.info("----------------modify-----------------");
        log.info(replyDTO+" modify dto");
        // 기존 파일 및 업로드된 파일까지 추가하는 배열

        // 기존 파일 및 업로드된 파일까지 추가하는 배열
        if (replyDTO.getFreeFiles() != null && replyDTO.getFreeFiles().size() > 0) {

            List<String> uploadFileNames = uploader.uploadFiles(replyDTO.getFreeFiles(), true);

            List<String> oldFileNames = replyDTO.getFreeImages();

            uploadFileNames.forEach(fname -> oldFileNames.add(fname));
        }
        log.info("After............");
        log.info(replyDTO);

        replyService.modify(replyDTO);

        return Map.of("result", replyDTO.getFreeRno());
    }

}