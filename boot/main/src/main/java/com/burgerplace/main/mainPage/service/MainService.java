package com.burgerplace.main.mainPage.service;

import com.burgerplace.main.common.pageDto.PageRequestDTO;
import com.burgerplace.main.common.pageDto.PageResponseDTO;
import com.burgerplace.main.mainPage.dto.MainDTO;
import com.burgerplace.main.mainPage.dto.MainListRcntDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface MainService {
    
    PageResponseDTO<MainListRcntDTO> listRcnt(PageRequestDTO pageRequestDTO);

    MainDTO getOne(Long bno);

}
