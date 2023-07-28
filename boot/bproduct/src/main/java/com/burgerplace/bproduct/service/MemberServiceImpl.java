package com.burgerplace.bproduct.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.burgerplace.bproduct.dto.MemberDTO;
import com.burgerplace.bproduct.entity.Member;
import com.burgerplace.bproduct.repositroy.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    // 로그인 실패시 실행되는 예외처리
    public static final class MemberLoginException extends RuntimeException {

        public MemberLoginException(String msg) {
            super(msg);
        }

    }

    @Override
    public MemberDTO login(String email, String pw) {

        MemberDTO memberDTO = null;
        try {
            Optional<Member> result = memberRepository.findById(email);

            Member member = result.orElseThrow();

            if (!member.getPw().equals(pw)) {
                throw new MemberLoginException("Password Incorrect");
            }

            // 패스워드는 안나가게 해야되므로 Service에서 처리해준다.
            memberDTO = MemberDTO.builder()
                    .email(member.getEmail())
                    .pw("")
                    .nickname(member.getNickname())
                    .admin(member.isAdmin())
                    .build();

        } catch (Exception e) {
            throw new MemberLoginException(e.getMessage());
        }

        return memberDTO;
    }

}
