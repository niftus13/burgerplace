package com.burgerplace.main.productPage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.burgerplace.main.productPage.domain.Event;
import com.burgerplace.main.productPage.domain.Product;



public interface EventRepository extends JpaRepository<Event, Long>{

    @Query("select e from Event e where e.eno = :eno")
    Product selectOne(@Param("eno")Long eno);
    
}
