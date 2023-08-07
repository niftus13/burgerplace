package org.zerock.j1.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.j1.domain.TradeBoard;
import org.zerock.j1.dto.PageRequestDTO;
import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.dto.TradeBoardDTO;
import org.zerock.j1.dto.TradeBoardListRcntDTO;
import org.zerock.j1.repository.TradeBoardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class TradeBoardServiceImpl implements TradeBoardService {

    private final TradeBoardRepository tbRepo;

        private final ModelMapper modelMapper;



    @Override
    public PageResponseDTO<TradeBoardListRcntDTO> listRcnt(PageRequestDTO pageRequestDTO) {
        
        return tbRepo.searchDTORcnt(pageRequestDTO);

    }

    @Override
    public TradeBoardDTO getOne(Long tBno) {
        
        Optional<TradeBoard> result = tbRepo.findById(tBno);

        TradeBoard tBoard = result.orElseThrow();

        TradeBoardDTO dto = modelMapper.map(tBoard, TradeBoardDTO.class);

        return dto;

    }

}
