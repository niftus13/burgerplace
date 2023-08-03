package org.zerock.j1.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.j1.domain.FreeBoard;
import org.zerock.j1.dto.FreeBoardDTO;
import org.zerock.j1.dto.FreeBoardListRcntDTO;
import org.zerock.j1.dto.PageRequestDTO;
import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.repository.FreeBoardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class FreeBoardServiceImpl  implements FreeBoardService{
    
    private final FreeBoardRepository fbRepository;

    private final ModelMapper modelMapper;
    // 등록작업을 위해서 사용한다.
    // private final ModelMapper modelMapper;

    @Override
    public PageResponseDTO<FreeBoardListRcntDTO> listRcnt(PageRequestDTO pageRequestDTO) {
        
        log.info("--------------------------");
        log.info(pageRequestDTO);

        return fbRepository.searchDTORcnt(pageRequestDTO);

    }

    @Override
    public FreeBoardDTO getOne(Long bno) {
        
        Optional<FreeBoard> result = fbRepository.findById(bno);

        FreeBoard board = result.orElseThrow();

        FreeBoardDTO dto = modelMapper.map(board, FreeBoardDTO.class);
        
            
        

        return dto;
    }
}
