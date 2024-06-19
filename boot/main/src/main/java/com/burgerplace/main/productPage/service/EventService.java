package com.burgerplace.main.productPage.service;

import org.springframework.transaction.annotation.Transactional;

import com.burgerplace.main.productPage.dto.EventDTO;


@Transactional
public interface EventService {

    void parsing();

    void remove(Long eno);

    void modify(EventDTO eventDTO);

    
}
