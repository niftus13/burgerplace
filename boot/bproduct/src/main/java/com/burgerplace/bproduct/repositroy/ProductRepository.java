package com.burgerplace.bproduct.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.burgerplace.bproduct.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
