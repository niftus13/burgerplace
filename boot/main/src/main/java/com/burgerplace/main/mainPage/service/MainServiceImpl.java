package com.burgerplace.main.mainPage.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.burgerplace.main.common.pageDto.PageRequestDTO;
import com.burgerplace.main.common.pageDto.PageResponseDTO;
import com.burgerplace.main.mainPage.dto.MainDTO;
import com.burgerplace.main.mainPage.dto.MainListRcntDTO;
import com.burgerplace.main.mainPage.entity.Main;
import com.burgerplace.main.mainPage.repository.MainRepository;

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
