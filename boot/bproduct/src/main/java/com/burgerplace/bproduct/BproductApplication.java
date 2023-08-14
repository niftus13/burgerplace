package com.burgerplace.bproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BproductApplication {

	public static void main(String[] args) {
		SpringApplication.run(BproductApplication.class, args);
	}

}
   