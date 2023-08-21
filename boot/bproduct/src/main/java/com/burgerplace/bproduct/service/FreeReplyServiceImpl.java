package com.burgerplace.bproduct.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.zerock.j2.dto.FreeGetReplyDTO;
import org.zerock.j2.dto.FreeReplyDTO;
import org.zerock.j2.dto.FreeReplyPageRequestDTO;
import org.zerock.j2.dto.PageResponseDTO;
import org.zerock.j2.entity.FreeBoard;
import org.zerock.j2.entity.FreeReply;
import org.zerock.j2.repository.FreeReplyRepository;
import org.zerock.j2.util.FileUploader;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class FreeReplyServiceImpl implements FreeReplyService {

    private final FreeReplyRepository replyRepository;
    private final ModelMapper modelMapper;
    private final FileUploader fileUploader;

    @Override
    public PageResponseDTO<FreeReplyDTO> list(FreeReplyPageRequestDTO requestDTO) {

        boolean last = requestDTO.isLast();
        // requestDTO의 값에서 last 값만 가져와 last변수에 담는다.
        log.info(last);

        int pageNum = requestDTO.getPage();
        log.info(pageNum);
        // 이하동문

        if (last) {
            long totalCount = replyRepository.getCountBoard(requestDTO.getFreeBno());
            // 만약 댓글이 50개인 99번 게시물은 totlacount가 50이 떠야한다.

            pageNum = (int) (Math.ceil(totalCount / (double) requestDTO.getSize()));

            pageNum = pageNum <= 0 ? 1 : pageNum;
        }

        Pageable pageable = PageRequest.of(pageNum - 1, requestDTO.getSize(), Sort.by("freeRno").ascending());

        Page<FreeReply> result = replyRepository.listBoard(requestDTO.getFreeBno(), pageable);

        log.info("------------------------------------");

        long totalReplyCount = result.getTotalElements();

        List<FreeReplyDTO> dtoList = result.get()
                .map(en -> modelMapper.map(en, FreeReplyDTO.class))
                .collect(Collectors.toList());
        // entity의 값을 dto로 바꾸고 리스트로 만들고 dtoList에 저장한다.
        log.info(dtoList + "Reply dto List ");

        PageResponseDTO<FreeReplyDTO> responseDTO = new PageResponseDTO<>(dtoList, totalReplyCount, requestDTO);
        responseDTO.setPage(pageNum);

        log.info(responseDTO + " responseDTO");
        return responseDTO;

    }

    @Override
    public FreeGetReplyDTO read(Long freeRno) {

        log.info(freeRno + " fbsi");

        Optional<FreeReply> result = replyRepository.findById(freeRno);

        log.info(result + " fbsi ");

        FreeReply reply = result.orElseThrow();

        log.info(reply + " fbsi");

        FreeGetReplyDTO dto = FreeGetReplyDTO.builder()
                .freeRno(reply.getFreeRno())
                .replyText(reply.getReplyText())
                .nickname(reply.getNickname())
                .freeImages(reply.getFreeImages().stream().map(pi -> pi.getImageName()).collect(Collectors.toList()))
                .build();

        log.info(dto + " fbsi dto");

        return dto;
    }

    @Override
    public Long register(FreeGetReplyDTO replyDTO) {

        log.info(replyDTO + " replyservice imple freereplyDTO");

        FreeBoard board = FreeBoard.builder().freeBno(replyDTO.getFreeBno()).build();

        FreeReply reply = FreeReply.builder()
                .freeBoard(board)
                .replyText(replyDTO.getReplyText())
                .nickname(replyDTO.getNickname())
                .build();

        replyDTO.getFreeImages().forEach(imageName -> {
            reply.addImage(imageName);
        });

        return replyRepository.save(reply).getFreeRno();
    }


    @Override
    public void remove(Long freeRno) {

        Optional<FreeReply> result = replyRepository.findById(freeRno);

        FreeReply reply = result.orElseThrow();

        reply.changeText("해당 글은 삭제되었습니다.");

        replyRepository.save(reply);

        List<String> fileNames = reply.getFreeImages().stream().map(pi -> pi.getImageName())
                .collect(Collectors.toList());

        fileUploader.removeFiles(fileNames);
    }


    @Override
    public void modify(FreeGetReplyDTO replyDTO) {

        Optional<FreeReply> result = replyRepository.findById(replyDTO.getFreeRno());

        log.info(result+" fbsi modify result");

        FreeReply reply = result.orElseThrow();

        log.info(reply+ " fbsi modify reply");

        reply.changeText(replyDTO.getReplyText());

        // 기존 이미지 목록 추출 --- 추후 비교해서 삭제
        List<String> oldFileNames = reply.getFreeImages().stream().map(pi -> pi.getImageName())
                .collect(Collectors.toList());

        log.info(oldFileNames+" fbsi modify oldFileNames");

        reply.cleanImages();

        log.info(reply+" 현재 reply");

        replyDTO.getFreeImages().forEach(imageName -> {
            reply.addImage(imageName);
        });

        log.info("======================================");
        log.info("======================================");
        log.info("======================================");
        log.info(reply);
        log.info("======================================");
        log.info("======================================");

        replyRepository.save(reply);

        // 기존 파일들중에 productDTO.getImages() 포함안된 파일들 찾기
        List<String> newFiles = replyDTO.getFreeImages();

        List<String> wantDeleteFiles = oldFileNames.stream()
                .filter(f -> newFiles.indexOf(f) == -1)
                // 들어온 문자열에서 f가 발견되지 않아서 -1이 결과값으로 나온 것만 wantDeleteFiles란 이름으로
                // 리스트 형태로 저장된다.
                .collect(Collectors.toList());
        fileUploader.removeFiles(wantDeleteFiles);
        // 제거 메서드로 보낸다.
    }

}