package com.burgerplace.main.productPage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.burgerplace.main.productPage.domain.CrawlingEvent;
import com.burgerplace.main.productPage.domain.Event;
import com.burgerplace.main.productPage.dto.EventDTO;
import com.burgerplace.main.productPage.repository.CrawlingEventRepository;
import com.burgerplace.main.productPage.repository.EventRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class EventServiceimpl implements EventService{

    private final CrawlingEventRepository crawlingEventRepository;
    private final EventRepository eventRepository;


    @Override
    public void parsing() {

    List<CrawlingEvent> events = crawlingEventRepository.findAll();
    List<Event> eventList = new ArrayList<>(); // 이벤트 리스트 생성

        for (CrawlingEvent crawlingEvent : events) {
            Event event = Event.builder()
                .eventInfo(crawlingEvent.getEventInfo())
                .brand(crawlingEvent.getBrand())
                .startDate(crawlingEvent.getStartDate())
                .endDate(crawlingEvent.getEndDate())
                .build();
            eventList.add(event); // 이벤트 리스트에 추가
        }

        eventRepository.saveAll(eventList); // 이벤트 리스트 한꺼번에 저장
    }


    @Override
    public void remove(Long eno) {
        // Event event = eventRepository.selectOne(eno);
        // eventRepository.delete(event);

        
    }


    @Override
    public void modify(EventDTO eventDTO) {
        
    }
            
  
}
    
    

