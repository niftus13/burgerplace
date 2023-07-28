package com.burgerplace.bproduct.service;

import com.burgerplace.bproduct.dto.MemberDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface MemberService {

    MemberDTO login(String email, String pw);

}
