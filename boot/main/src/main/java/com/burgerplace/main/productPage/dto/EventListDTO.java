package com.burgerplace.main.productPage.dto;

import java.time.LocalDate;

import lombok.Data;


@Data
public class EventListDTO {

    private Long eno;

    private String eventInfo;

    private LocalDate startDate;

    private LocalDate endDate;
    
    private String brand;

    private long replyCnt;

    private double gradeAvg;

    
    
}
