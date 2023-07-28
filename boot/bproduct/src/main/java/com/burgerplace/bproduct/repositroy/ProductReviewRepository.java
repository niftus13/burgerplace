package com.burgerplace.bproduct.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.burgerplace.bproduct.entity.ProductReview;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long>{
    
}
