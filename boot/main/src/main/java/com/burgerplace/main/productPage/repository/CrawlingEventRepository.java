package com.burgerplace.main.productPage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.burgerplace.main.productPage.domain.CrawlingEvent;


public interface CrawlingEventRepository extends JpaRepository<CrawlingEvent, Long>{
    
}
