package com.burgerplace.bproduct.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.burgerplace.bproduct.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long>{
    
}
