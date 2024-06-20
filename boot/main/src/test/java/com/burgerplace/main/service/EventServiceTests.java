package com.burgerplace.main.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.burgerplace.main.productPage.service.EventService;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class EventServiceTests {

    @Autowired
    EventService eventService;

    @Test
    public void test1(){

        eventService.parsing();
    }



    
}
