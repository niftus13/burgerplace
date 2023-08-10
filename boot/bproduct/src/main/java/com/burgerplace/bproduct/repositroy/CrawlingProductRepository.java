package com.burgerplace.bproduct.repositroy;

import com.burgerplace.bproduct.entity.CrawlingProduct;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CrawlingProductRepository extends JpaRepository<CrawlingProduct,Integer> {

}
