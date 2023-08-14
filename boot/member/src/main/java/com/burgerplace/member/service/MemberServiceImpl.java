package com.burgerplace.member.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.burgerplace.member.dto.MemberDTO;
import com.burgerplace.member.dto.PageRequestDTO;
import com.burgerplace.member.dto.PageResponseDTO;
import com.burgerplace.member.entity.MemberEntity;
import com.burgerplace.member.repository.MemberEntityRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {

    private final MemberEntityRepository memberRepository;

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
        Optional<MemberEntity> result = memberRepository.findById(id);

        MemberEntity member = result.orElseThrow(() -> new NoSuchElementException("Member not found with id: " + id));

        MemberDTO dto = modelMapper.map(member, MemberDTO.class);

        return dto;
    }

    @Override
    public void remove(String id) {

        MemberEntity member = memberRepository.selectOne(id);

        member.changeDel(true);

        memberRepository.save(member);
    }

    @Override
    public void modify(MemberDTO memberDTO) {

        Optional<MemberEntity> result = memberRepository.findById(memberDTO.getId());

        MemberEntity member = result.orElseThrow();

        member.changeNickname(memberDTO.getNickname());
        member.changePassword(memberDTO.getPw());
        member.changeAdmin(memberDTO.getAdmin());

        memberRepository.save(member);
    }

    @Override
    public void register(MemberDTO memberDTO) {
        MemberEntity newMember = modelMapper.map(memberDTO, MemberEntity.class);

        // 회원 가입 시 필요한 초기화 작업 등 수행 가능

        memberRepository.save(newMember);
    }
}
