package org.zerock.j2.service;

import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.j2.dto.FreeBoardDTO;
import org.zerock.j2.dto.FreeBoardListRcntDTO;
import org.zerock.j2.dto.FreeGetBoardDTO;
import org.zerock.j2.dto.PageRequestDTO;
import org.zerock.j2.dto.PageResponseDTO;
import org.zerock.j2.entity.FreeBoard;
import org.zerock.j2.repository.FreeBoardRepository;
import org.zerock.j2.util.FileUploader;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class FreeBoardServiceImpl  implements FreeBoardService{
    
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
        
        Optional<FreeBoard> result = boardRepository.findById(freeBno);


        FreeBoard board = result.orElseThrow();

        log.info(board);

        FreeGetBoardDTO dto = FreeGetBoardDTO.builder()
        .freeBno(board.getFreeBno())
        .freeTitle(board.getFreeTitle())
        .freeContent(board.getFreeContent())
        .freeImages(board.getFreeImages().stream().map(pi -> pi.getImageName()).collect(Collectors.toList()))
        .build();


        return dto;
    }

    @Override
    public Long Register(FreeGetBoardDTO freeBoardDTO) {

        log.info(freeBoardDTO+" boardservice imple freeboardDTO");
     
        FreeBoard board = FreeBoard.builder()
        .freeContent(freeBoardDTO.getFreeContent())
        .freeTitle(freeBoardDTO.getFreeTitle())
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
        
    }

    @Override
    public void modify(FreeBoardDTO freeBoardDTO) {
       
        Optional<FreeBoard> result = boardRepository.findById(freeBoardDTO.getFreeBno());

        FreeBoard board = result.orElseThrow();

        board.changeContent(freeBoardDTO.getFreeContent());
        board.changeTitle(freeBoardDTO.getFreeTitle());

    }
}
