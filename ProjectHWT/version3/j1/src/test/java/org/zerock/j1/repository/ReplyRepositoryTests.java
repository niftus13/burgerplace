package org.zerock.j1.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.j1.domain.Board;
import org.zerock.j1.domain.Reply;
import org.zerock.j1.dto.ReplyPageRequestDTO;
import org.zerock.j1.service.ReplyService;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private ReplyService replyService;


    @Test
    public void insertOne() {

        Long fbno = 100L;

        Board board = Board.builder().fbno(fbno).build();

        Reply reply = Reply.builder()
                .replyText("Reply......1")
                .nickname("replyer00")
                .board(board)
                .build();

        replyRepository.save(reply);
    }

    @Test
    public void testInsertDummies() {

        Long[] bnoArr = { 99L, 96L, 92L, 84L, 81L };

        for (Long fbno : bnoArr) {

            Board board = Board.builder().fbno(fbno).build();

            for (int i = 0; i < 50; i++) {

                Reply reply = Reply.builder()
                        .replyText("Reply..." + fbno + "--" + i)
                        .nickname("replyer" + i)
                        .board(board)
                        .build();

                replyRepository.save(reply);
            }
        }
    }
    @Test
    public void testListBoard(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("frno").ascending());
        Long fbno = 99L;
        Page<Reply> result = replyRepository.listBoard(fbno, pageable);

        result.get().forEach(r-> log.info(r));
        
    }

    @Test
    public void testCount(){
        
        long fbno = 99L;

        long count = replyRepository.getCountBoard(fbno);

        log.info("count: " + count);
    }

    @Test
    public void testListLast(){

        ReplyPageRequestDTO requestDTO = ReplyPageRequestDTO.builder().fbno(99L).last(true).build();

        log.info(replyService.list(requestDTO));


    }

}
