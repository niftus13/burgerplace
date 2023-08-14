    package com.burgerplace.member.service;

    import com.burgerplace.member.dto.MemberDTO;
    import com.burgerplace.member.dto.PageRequestDTO;
    import com.burgerplace.member.dto.PageResponseDTO;

    import jakarta.transaction.Transactional;

    @Transactional
    public interface MemberService {

        PageResponseDTO<MemberDTO> listRcnt(PageRequestDTO pageRequestDTO);

        MemberDTO getOne(String id);

        void remove(String id);

        void modify(MemberDTO memberDTO);

        void register(MemberDTO memberDTO);
    }
