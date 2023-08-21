package org.zerock.j2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.j2.entity.FreeBoard;
import org.zerock.j2.entity.FreeReply;
import org.zerock.j2.entity.TradeBoard;
import org.zerock.j2.entity.TradeReply;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class TradeReplyRepositoryTests {

    @Autowired
    TradeReplyRepository trRepo;

    @Test
    public void testInsertDummies() {

        Long[] bnoArr = { 103L, 102L, 101L, 100L , 99L, 92L, 84L, 81L, 79L, 75L };

        for (Long tradeBno : bnoArr) {

            TradeBoard board = TradeBoard.builder().tradeBno(tradeBno).build();

            for (int i = 0; i < 50; i++) {

                TradeReply reply = TradeReply.builder()
                        .replyText("Reply..." + tradeBno + "--" + i)
                        .nickname("replyer" + i)
                        .tradeBoard(board)
                        .build();

                trRepo.save(reply);
            }
        }
    }


    
}
