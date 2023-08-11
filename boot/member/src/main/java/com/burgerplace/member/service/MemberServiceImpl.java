package com.burgerplace.member.service;

import java.util.List;
import java.util.NoSuchElementException;
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

        Member member = result.orElseThrow(() -> new NoSuchElementException("Member not found with id: " + id));

        MemberDTO dto = modelMapper.map(member, MemberDTO.class);

        return dto;
    }

    @Override
    public void remove(String id) {
        
        Member member = memberRepository.selectOne(id);

        member.changeDel(true);

        memberRepository.save(member);
    }

    @Override
    public void modify(MemberDTO memberDTO) {
        
        Optional<Member> result = memberRepository.findById(memberDTO.getId());
   
        Member member = result.orElseThrow();

        member.changeNickname(memberDTO.getNickname());
        member.changePassword(memberDTO.getPw());
        member.changeAdmin(memberDTO.getAdmin());

        memberRepository.save(member);
    }


}
