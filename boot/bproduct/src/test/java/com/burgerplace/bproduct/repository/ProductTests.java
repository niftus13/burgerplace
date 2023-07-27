package com.burgerplace.bproduct.repository;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.burgerplace.bproduct.entity.Product;
import com.burgerplace.bproduct.repositroy.ProductRepository;

@SpringBootTest
public class ProductTests {

    @Autowired
    ProductRepository repo;


    @Test
    public void testInsert(){

        Product product = Product.builder()
        .pname("Test")
        .pdesc("Test")
        .writer("user00")
        .price(4000)
        .build();

        product.addImage(UUID.randomUUID().toString()+ "_aaa.jpg");
        product.addImage(UUID.randomUUID().toString()+"_bbb.jpg0");
        product.addImage(UUID.randomUUID().toString()+"_ccc.jpg");

        repo.save(product);

    }
}
