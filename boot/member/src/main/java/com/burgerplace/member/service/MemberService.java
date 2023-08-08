package com.burgerplace.member.service;

import com.burgerplace.member.dto.MemberDTO;
import com.burgerplace.member.dto.PageRequestDTO;
import com.burgerplace.member.dto.PageResponseDTO;

public interface MemberService {

    PageResponseDTO<MemberDTO> listRcnt(PageRequestDTO pageRequestDTO);

    MemberDTO getOne(String id);
}
