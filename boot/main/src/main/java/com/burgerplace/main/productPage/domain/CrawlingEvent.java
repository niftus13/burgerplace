package com.burgerplace.main.productPage.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="eventCrawling")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrawlingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pEno;
    private String eventInfo;
    private LocalDate startDate;
    private LocalDate endDate;
    private String brand;
    
}
