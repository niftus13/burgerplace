package com.burgerplace.main.boardPage.service;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.burgerplace.main.boardPage.dto.FreeBoardListRcntDTO;
import com.burgerplace.main.boardPage.dto.FreeGetBoardDTO;
import com.burgerplace.main.boardPage.dto.PageRequestDTO;
import com.burgerplace.main.boardPage.dto.PageResponseDTO;
import com.burgerplace.main.boardPage.entity.FreeBoard;
import com.burgerplace.main.boardPage.repository.FreeBoardRepository;
import com.burgerplace.main.common.util.FileUploader;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class FreeBoardServiceImpl implements FreeBoardService {

    private final FreeBoardRepository boardRepository;

    private final ModelMapper modelMapper;

    private final FileUploader fileUploader;
    // 등록작업을 위해서 사용한다.
    // private final ModelMapper modelMapper;

    @Override
    public PageResponseDTO<FreeBoardListRcntDTO> listRcnt(PageRequestDTO pageRequestDTO) {

        log.info("--------------------------");
        log.info(pageRequestDTO);

        return boardRepository.searchDTORcnt(pageRequestDTO);

    }

    @Override
    public FreeGetBoardDTO getOne(Long freeBno) {

        log.info(freeBno + " fbsi");

        Optional<FreeBoard> result = boardRepository.findById(freeBno);

        log.info(result+" fbsi ");

        FreeBoard board = result.orElseThrow();

        log.info(board+" fbsi");


        FreeGetBoardDTO dto = FreeGetBoardDTO.builder()
                .freeBno(board.getFreeBno())
                .freeTitle(board.getFreeTitle())
                .freeContent(board.getFreeContent())
                .nickname(board.getNickname())
                .freeImages(board.getFreeImages().stream().map(pi -> pi.getImageName()).collect(Collectors.toList()))
                .build();

                log.info(dto+ " fbsi dto");

        return dto;
    }



    
    @Override
    public Long Register(FreeGetBoardDTO freeBoardDTO) {

        log.info(freeBoardDTO + " boardservice imple freeboardDTO");

        FreeBoard board = FreeBoard.builder()
                .freeContent(freeBoardDTO.getFreeContent())
                .freeTitle(freeBoardDTO.getFreeTitle())
                .nickname(freeBoardDTO.getNickname())
                .build();

        freeBoardDTO.getFreeImages().forEach(imageName -> {
            board.addImage(imageName);
        });

        return boardRepository.save(board).getFreeBno();
    }

    @Override
    public void remove(Long freeBno) {

        Optional<FreeBoard> result = boardRepository.findById(freeBno);

        FreeBoard board = result.orElseThrow();

        board.changeContent("해당 글은 삭제되었습니다.");
        board.changeTitle("해당 글은 삭제되었습니다.");

        boardRepository.save(board);

        List<String> fileNames = board.getFreeImages().stream().map(pi -> pi.getImageName())
                .collect(Collectors.toList());

        fileUploader.removeFiles(fileNames);

    }

    @Override
    public void modify(FreeGetBoardDTO freeBoardDTO) {

        Optional<FreeBoard> result = boardRepository.findById(freeBoardDTO.getFreeBno());

        FreeBoard board = result.orElseThrow();

        board.changeContent(freeBoardDTO.getFreeContent());
        board.changeTitle(freeBoardDTO.getFreeTitle());

        // 기존 이미지 목록 추출 --- 추후 비교해서 삭제
        List<String> oldFileNames = board.getFreeImages().stream().map(pi -> pi.getImageName())
                .collect(Collectors.toList());

        board.cleanImages();

        freeBoardDTO.getFreeImages().forEach(imageName -> {
            board.addImage(imageName);
        });

        log.info("======================================");
        log.info("======================================");
        log.info("======================================");
        log.info(board);
        log.info("======================================");
        log.info("======================================");

        boardRepository.save(board);

        // 기존 파일들중에 productDTO.getImages() 포함안된 파일들 찾기
        List<String> newFiles = freeBoardDTO.getFreeImages();
        
        List<String> wantDeleteFiles = oldFileNames.stream()
                .filter(f -> newFiles.indexOf(f) == -1)
                // 들어온 문자열에서 f가 발견되지 않아서 -1이 결과값으로 나온 것만 wantDeleteFiles란 이름으로
                // 리스트 형태로 저장된다.
                .collect(Collectors.toList());
        fileUploader.removeFiles(wantDeleteFiles);
        // 제거 메서드로 보낸다.

    }
}
