package org.zerock.j2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class J2Application {

	public static void main(String[] args) {
		SpringApplication.run(J2Application.class, args);
	}

}
