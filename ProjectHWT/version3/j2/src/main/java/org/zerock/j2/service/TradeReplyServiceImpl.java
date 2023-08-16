package org.zerock.j2.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.zerock.j2.dto.PageResponseDTO;
import org.zerock.j2.dto.TradeGetReplyDTO;
import org.zerock.j2.dto.TradeReplyDTO;
import org.zerock.j2.dto.TradeReplyPageRequestDTO;


import org.zerock.j2.entity.TradeBoard;
import org.zerock.j2.entity.TradeReply;
import org.zerock.j2.repository.TradeReplyRepository;
import org.zerock.j2.util.FileUploader;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2

public class TradeReplyServiceImpl implements TradeReplyService {

    private final TradeReplyRepository tbRepo;
    private final ModelMapper modelMapper;
    private final FileUploader fileUploader;
    
    
    @Override
    public PageResponseDTO<TradeReplyDTO> list(TradeReplyPageRequestDTO requestDTO) {

        boolean last = requestDTO.isLast();

        int pageNum = requestDTO.getPage();

        if (last) {
            long totalCount = tbRepo.getCountBoard(requestDTO.getTradeBno());
            // 만약 댓글이 50개인 99번 게시물은 totlacount가 50이 떠야한다.

            pageNum = (int) (Math.ceil(totalCount / (double) requestDTO.getSize()));

            pageNum = pageNum <= 0 ? 1 : pageNum;
        }

        Pageable pageable = PageRequest.of(pageNum - 1, requestDTO.getSize(), Sort.by("tradeRno").ascending());

        Page<TradeReply> result = tbRepo.listBoard(requestDTO.getTradeBno(), pageable);

        log.info("------------------------------------");

        long totalReplyCount = result.getTotalElements();

                List<TradeReplyDTO> dtoList = result.get()
                .map(en -> modelMapper.map(en, TradeReplyDTO.class))
                .collect(Collectors.toList());
        // entity의 값을 dto로 바꾸고 리스트로 만들고 dtoList에 저장한다.
        log.info(dtoList + "Reply dto List ");

        PageResponseDTO<TradeReplyDTO> responseDTO = new PageResponseDTO<>(dtoList, totalReplyCount,requestDTO);
        responseDTO.setPage(pageNum);



        log.info(responseDTO + " responseDTO");
        return responseDTO;
    }

    @Override
    public TradeGetReplyDTO read(Long tradeRno) {
       
        log.info(tradeRno + " fbsi");

        Optional<TradeReply> result = tbRepo.findById(tradeRno);

        log.info(result + " fbsi ");

        TradeReply reply = result.orElseThrow();

        log.info(reply + " fbsi");

        TradeGetReplyDTO dto = TradeGetReplyDTO.builder()
                .tradeRno(reply.getTradeRno())
                .replyText(reply.getReplyText())
                .nickname(reply.getNickname())
                .tradeImages(reply.getTradeImages().stream().map(pi -> pi.getImageName()).collect(Collectors.toList()))
                .build();

        log.info(dto + " fbsi dto");

        return dto;
    }

    @Override
    public Long register(TradeGetReplyDTO replyDTO) {

        log.info(replyDTO + " replyservice imple freereplyDTO");

        TradeBoard board = TradeBoard.builder().tradeBno(replyDTO.getTradeBno()).build();

        TradeReply reply = TradeReply.builder()
                .tradeBoard(board)
                .replyText(replyDTO.getReplyText())
                .nickname(replyDTO.getNickname())
                .build();

        replyDTO.getTradeImages().forEach(imageName -> {
            reply.addImage(imageName);
        });

        return tbRepo.save(reply).getTradeRno();
    }

    @Override
    public void remove(Long tradeRno) {

        Optional<TradeReply> result = tbRepo.findById(tradeRno);

        TradeReply reply = result.orElseThrow();

        reply.changeText("해당 글은 삭제되었습니다.");

        tbRepo.save(reply);

        List<String> fileNames = reply.getTradeImages().stream().map(pi -> pi.getImageName())
                .collect(Collectors.toList());

        fileUploader.removeFiles(fileNames);
    }

    @Override
    public void modify(TradeGetReplyDTO replyDTO) {

        Optional<TradeReply> result = tbRepo.findById(replyDTO.getTradeRno());

        log.info(result+" fbsi modify result");

        TradeReply reply = result.orElseThrow();

        log.info(reply+ " fbsi modify reply");

        reply.changeText(replyDTO.getReplyText());

        // 기존 이미지 목록 추출 --- 추후 비교해서 삭제
        List<String> oldFileNames = reply.getTradeImages().stream().map(pi -> pi.getImageName())
                .collect(Collectors.toList());

        log.info(oldFileNames+" fbsi modify oldFileNames");

        reply.cleanImages();

        log.info(reply+" 현재 reply");

        replyDTO.getTradeImages().forEach(imageName -> {
            reply.addImage(imageName);
        });

        log.info("======================================");
        log.info("======================================");
        log.info("======================================");
        log.info(reply);
        log.info("======================================");
        log.info("======================================");

        tbRepo.save(reply);

        // 기존 파일들중에 productDTO.getImages() 포함안된 파일들 찾기
        List<String> newFiles = replyDTO.getTradeImages();

        List<String> wantDeleteFiles = oldFileNames.stream()
                .filter(f -> newFiles.indexOf(f) == -1)
                // 들어온 문자열에서 f가 발견되지 않아서 -1이 결과값으로 나온 것만 wantDeleteFiles란 이름으로
                // 리스트 형태로 저장된다.
                .collect(Collectors.toList());
        fileUploader.removeFiles(wantDeleteFiles);
        // 제거 메서드로 보낸다.
    }
    
}
