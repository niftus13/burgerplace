package com.burgerplace.bproduct.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.burgerplace.bproduct.entity.CrawlingEvent;

public interface CrawlingEventRepository extends JpaRepository<CrawlingEvent, Long>{
    
}
