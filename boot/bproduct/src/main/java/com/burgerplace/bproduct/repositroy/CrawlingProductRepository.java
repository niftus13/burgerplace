package com.burgerplace.bproduct.repositroy;

import com.burgerplace.bproduct.entity.CrawlingProduct;
import com.burgerplace.bproduct.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CrawlingProductRepository extends JpaRepository<CrawlingProduct,Integer> {

}
