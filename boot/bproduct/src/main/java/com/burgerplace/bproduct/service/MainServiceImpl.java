package com.burgerplace.bproduct.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.burgerplace.main.dto.MainDTO;
import com.burgerplace.main.dto.MainListRcntDTO;
import com.burgerplace.main.dto.PageRequestDTO;
import com.burgerplace.main.dto.PageResponseDTO;
import com.burgerplace.main.entity.Main;
import com.burgerplace.main.repository.MainRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {
    
    private final MainRepository mainRepository;
    private final ModelMapper modelMapper;

    @Override
    public PageResponseDTO<MainListRcntDTO> listRcnt(PageRequestDTO pageRequestDTO) {
       
        log.info("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        log.info(pageRequestDTO);

        return mainRepository.searchDTORcnt(pageRequestDTO);
    }

    @Override
    public MainDTO getOne(Long bno) {
       
        Optional<Main> result = mainRepository.findById(bno);
        
        Main main = result.orElseThrow();

        MainDTO dto = modelMapper.map(main, MainDTO.class);
        
        return dto;
    }
}
