package com.burgerplace.bproduct.service;

import com.burgerplace.main.dto.MainDTO;
import com.burgerplace.main.dto.MainListRcntDTO;
import com.burgerplace.main.dto.PageRequestDTO;
import com.burgerplace.main.dto.PageResponseDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface MainService {
    
    PageResponseDTO<MainListRcntDTO> listRcnt(PageRequestDTO pageRequestDTO);

    MainDTO getOne(Long bno);

}
