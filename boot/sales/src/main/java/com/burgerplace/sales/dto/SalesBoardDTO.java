package com.burgerplace.sales.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SalesBoardDTO {

    private Long pEno; // 번호

    private String category; // 카테고리

    private String title; // 제목(DB설계엔 없는데 필요할거같아서 넣음)

    private String eventInfo; // 내용(할인정보)

    private Long eventPrice; // 할인금액

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate; // 시작날짜
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate; // 종료날짜
    
}
