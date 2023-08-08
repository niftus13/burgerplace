package com.burgerplace.member.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.burgerplace.member.dto.MemberDTO;
import com.burgerplace.member.dto.PageRequestDTO;
import com.burgerplace.member.dto.PageResponseDTO;
import com.burgerplace.member.entity.Member;
import com.burgerplace.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {
    

    private final MemberRepository memberRepository;

    private final ModelMapper modelMapper;
    // 등록작업을 위해서 사용한다.
    // private final ModelMapper modelMapper;
    @Override
    public PageResponseDTO<MemberDTO> listRcnt(PageRequestDTO pageRequestDTO) {
       
        log.info("--------------------------");
        log.info(pageRequestDTO);

        return memberRepository.searchDTORcnt(pageRequestDTO);
    }

    @Override
    public MemberDTO getOne(String id) {
        Optional<Member> result = memberRepository.findById(id);

        Member member = result.orElseThrow();

        MemberDTO dto = modelMapper.map(member, MemberDTO.class);

        return dto;
    }


}
