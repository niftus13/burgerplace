package org.zerock.j2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.j2.dto.FreeReplyPageRequestDTO;
import org.zerock.j2.entity.FreeBoard;
import org.zerock.j2.entity.FreeReply;
import org.zerock.j2.service.FreeReplyService;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {

    @Autowired
    private FreeReplyRepository replyRepository;

    @Autowired
    private FreeReplyService replyService;


    @Test
    public void insertOne() {

        Long freeBno = 100L;

        FreeBoard board = FreeBoard.builder().freeBno(freeBno).build();

        FreeReply reply = FreeReply.builder()
                .replyText("Reply......1")
                .nickname("replyer00")
                .freeBoard(board)
                .build();

        replyRepository.save(reply);
    }

    @Test
    public void testInsertDummies() {

        Long[] bnoArr = { 98L, 96L, 92L, 84L, 81L, 79L, 75L };

        for (Long freeBno : bnoArr) {

            FreeBoard board = FreeBoard.builder().freeBno(freeBno).build();

            for (int i = 0; i < 50; i++) {

                FreeReply reply = FreeReply.builder()
                        .replyText("Reply..." + freeBno + "--" + i)
                        .nickname("replyer" + i)
                        .freeBoard(board)
                        .build();

                replyRepository.save(reply);
            }
        }
    }
    
    @Test
    public void testListBoard(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("freeRno").ascending());
        Long freeBno = 99L;
        Page<FreeReply> result = replyRepository.listBoard(freeBno, pageable);

        result.get().forEach(r-> log.info(r));
        
    }

    @Test
    public void testCount(){
        
        long freeBno = 99L;

        long count = replyRepository.getCountBoard(freeBno);

        log.info("count: " + count);
    }

    @Test
    public void testListLast(){

        FreeReplyPageRequestDTO requestDTO = 
            FreeReplyPageRequestDTO.builder().freeBno(99L).last(true).build();

        log.info(replyService.list(requestDTO));


    }

}
