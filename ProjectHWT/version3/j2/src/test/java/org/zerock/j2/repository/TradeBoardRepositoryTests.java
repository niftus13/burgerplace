package org.zerock.j2.repository;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.j2.entity.FreeBoard;
import org.zerock.j2.entity.TradeBoard;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class TradeBoardRepositoryTests {

    @Autowired
    TradeBoardRepository tbRepo;

    @Test
    public void testInsert() {
        for (int i = 0; i < 100; i++) {
            TradeBoard  board = TradeBoard.builder()
                    .tradeTitle("Sample Title" + i)
                    .tradeContent("Sample Content" + i)
                    .nickname("user" + (i % 10))
                    .build();

            board.addImage(UUID.randomUUID().toString() + "aaa_.jpg");
            board.addImage(UUID.randomUUID().toString() + "bbb_.jpg");
            board.addImage(UUID.randomUUID().toString() + "ccc_.jpg");

            tbRepo.save(board);
        }
    }
    
}
