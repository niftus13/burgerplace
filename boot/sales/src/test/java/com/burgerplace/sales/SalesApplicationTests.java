package com.burgerplace.sales;

import com.burgerplace.sales.domain.SalesBoard;
import com.burgerplace.sales.repository.SalesBoardRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class SalesApplicationTests {

	// 테스트를 위한 DI
	@Autowired
	private SalesBoardRepository boardRepository;

	// Insert 테스트
	@Test
	public void testInsert() {
		for (int i = 0; i < 100; i++) {
			SalesBoard board = SalesBoard.builder()
					.title("Sample Title" + i)
					.category("test")
					.eventInfo("Sample Content" + i)
					.eventPrice(3000)
					.build();

			boardRepository.save(board);
		}
	}
}
