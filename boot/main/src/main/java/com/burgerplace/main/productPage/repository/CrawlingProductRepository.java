package com.burgerplace.main.productPage.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.burgerplace.main.productPage.domain.CrawlingProduct;

public interface CrawlingProductRepository extends JpaRepository<CrawlingProduct,Integer> {

}
