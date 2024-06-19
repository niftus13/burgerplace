package com.burgerplace.main.memberPage.service;

import com.burgerplace.main.common.pageDto.PageRequestDTO;
import com.burgerplace.main.common.pageDto.PageResponseDTO;
import com.burgerplace.main.memberPage.dto.MemberDTO;

import jakarta.transaction.Transactional;

    @Transactional
    public interface MemberService {

        PageResponseDTO<MemberDTO> listRcnt(PageRequestDTO pageRequestDTO);

        MemberDTO getOne(String id);

        void remove(String id);

        void modify(MemberDTO memberDTO);

        void register(MemberDTO memberDTO);
    }
