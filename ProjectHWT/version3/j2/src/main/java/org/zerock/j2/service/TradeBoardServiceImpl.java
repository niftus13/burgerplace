package org.zerock.j2.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import org.zerock.j2.dto.PageRequestDTO;
import org.zerock.j2.dto.PageResponseDTO;
import org.zerock.j2.dto.TradeBoardListRcntDTO;
import org.zerock.j2.dto.TradeGetBoardDTO;

import org.zerock.j2.entity.TradeBoard;
import org.zerock.j2.repository.TradeBoardRepository;
import org.zerock.j2.util.FileUploader;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class TradeBoardServiceImpl implements TradeBoardService {

    private final TradeBoardRepository tbRepo;

    private final FileUploader fileUploader;

    @Override
    public PageResponseDTO<TradeBoardListRcntDTO> listRcnt(PageRequestDTO pageRequestDTO) {

        log.info("--------------------------");
        log.info(pageRequestDTO);

        return tbRepo.searchDTORcnt(pageRequestDTO);
    }

    @Override
    public TradeGetBoardDTO getOne(Long tradeBno) {

        log.info(tradeBno + " fbsi");

        Optional<TradeBoard> result = tbRepo.findById(tradeBno);

        log.info(result + " fbsi ");

        TradeBoard reply = result.orElseThrow();

        log.info(reply + " fbsi");

        TradeGetBoardDTO dto = TradeGetBoardDTO.builder()
                .tradeBno(reply.getTradeBno())
                .tradeTitle(reply.getTradeTitle())
                .tradeContent(reply.getTradeContent())
                .tradeImages(reply.getTradeImages().stream().map(pi -> pi.getImageName()).collect(Collectors.toList()))
                .build();

        log.info(dto + " fbsi dto");

        return dto;
    }

    @Override
    public Long Register(TradeGetBoardDTO tradeGetBoardDTO) {

        log.info(tradeGetBoardDTO + " boardservice imple freeboardDTO");

        TradeBoard board = TradeBoard.builder()
                .tradeContent(tradeGetBoardDTO.getTradeContent())
                .tradeTitle(tradeGetBoardDTO.getTradeTitle())
                .nickname(tradeGetBoardDTO.getNickname())
                .build();

        tradeGetBoardDTO.getTradeImages().forEach(imageName -> {
            board.addImage(imageName);
        });

        return tbRepo.save(board).getTradeBno();
    }

    @Override
    public void remove(Long tradeBno) {
        Optional<TradeBoard> result = tbRepo.findById(tradeBno);

        TradeBoard board = result.orElseThrow();

        board.changeContent("해당 글은 삭제되었습니다.");
        board.changeTitle("해당 글은 삭제되었습니다.");

        tbRepo.save(board);

        List<String> fileNames = board.getTradeImages().stream().map(pi -> pi.getImageName())
                .collect(Collectors.toList());

        fileUploader.removeFiles(fileNames);
    }

    @Override
    public void modify(TradeGetBoardDTO tradeGetBoardDTO) {
        Optional<TradeBoard> result = tbRepo.findById(tradeGetBoardDTO.getTradeBno());

        TradeBoard board = result.orElseThrow();

        board.changeContent(tradeGetBoardDTO.getTradeContent());
        board.changeTitle(tradeGetBoardDTO.getTradeTitle());

        // 기존 이미지 목록 추출 --- 추후 비교해서 삭제
        List<String> oldFileNames = board.getTradeImages().stream().map(pi -> pi.getImageName())
                .collect(Collectors.toList());

        board.cleanImages();

        tradeGetBoardDTO.getTradeImages().forEach(imageName -> {
            board.addImage(imageName);
        });

        log.info("======================================");
        log.info("======================================");
        log.info("======================================");
        log.info(board);
        log.info("======================================");
        log.info("======================================");

        tbRepo.save(board);

        // 기존 파일들중에 productDTO.getImages() 포함안된 파일들 찾기
        List<String> newFiles = tradeGetBoardDTO.getTradeImages();
        
        List<String> wantDeleteFiles = oldFileNames.stream()
                .filter(f -> newFiles.indexOf(f) == -1)
                // 들어온 문자열에서 f가 발견되지 않아서 -1이 결과값으로 나온 것만 wantDeleteFiles란 이름으로
                // 리스트 형태로 저장된다.
                .collect(Collectors.toList());
            fileUploader.removeFiles(wantDeleteFiles);
    }

}
