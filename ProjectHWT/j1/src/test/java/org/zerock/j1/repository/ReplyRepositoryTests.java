package org.zerock.j1.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.j1.domain.FreeBoard;
import org.zerock.j1.domain.FreeReply;
import org.zerock.j1.dto.ReplyPageRequestDTO;
import org.zerock.j1.service.FreeReplyService;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {

    @Autowired
    private FreeReplyRepository frRepository;

    @Autowired
    private FreeReplyService frService;


    @Test
    public void insertOne() {

        Long fBno = 100L;

        FreeBoard fBoard = FreeBoard.builder().fBno(fBno).build();

        FreeReply fReply = FreeReply.builder()
                .replyText("Reply......1")
                .nickname("replyer00")
                .freeBoard(fBoard)
                .build();

        frRepository.save(fReply);
    }

    @Test
    public void testInsertDummies() {

        Long[] fBnoArr = { 99L, 96L, 92L, 84L, 81L };

        for (Long fBno : fBnoArr) {

            FreeBoard fBoard = FreeBoard.builder().fBno(fBno).build();

            for (int i = 0; i < 50; i++) {

                FreeReply fReply = FreeReply.builder()
                        .replyText("Reply..." + fBno + "--" + i)
                        .nickname("replyer" + i)
                        .freeBoard(fBoard)
                        .build();

                frRepository.save(fReply);
            }
        }
    }



    @Test
    public void testListBoard(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("fRno").ascending());
        Long fBno = 99L;
        Page<FreeReply> result = frRepository.listBoard(fBno, pageable);

        result.get().forEach(r-> log.info(r));
        
    }

    @Test
    public void testCount(){
        
        long fBno = 99L;

        long count = frRepository.getCountBoard(fBno);

        log.info("count: " + count);
    }

    @Test
    public void testListLast(){

        ReplyPageRequestDTO requestDTO = ReplyPageRequestDTO.builder().fBno(99L).last(true).build();

        log.info(frService.list(requestDTO));


    }

}
