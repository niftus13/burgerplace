package com.burgerplace.main.salePage.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SalesBoardDTO {

    private Long pEno; // 번호

    private String category; // 카테고리

    private String title; // 제목(DB설계 부재)

    private String eventInfo; // 내용(할인정보)

    private Integer eventPrice; // 할인금액

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate; // 시작날짜
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate; // 종료날짜
    
}
