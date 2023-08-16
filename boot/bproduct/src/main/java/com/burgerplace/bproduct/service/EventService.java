package com.burgerplace.bproduct.service;

import org.springframework.transaction.annotation.Transactional;

import com.burgerplace.bproduct.dto.EventDTO;

@Transactional
public interface EventService {

    void parsing();

    void remove(Long eno);

    void modify(EventDTO eventDTO);

    
}
