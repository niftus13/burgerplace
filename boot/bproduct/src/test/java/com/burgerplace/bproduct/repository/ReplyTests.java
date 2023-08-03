package com.burgerplace.bproduct.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.burgerplace.bproduct.dto.ReplyPageRequestDTO;
import com.burgerplace.bproduct.entity.Product;
import com.burgerplace.bproduct.entity.Reply;
import com.burgerplace.bproduct.repositroy.ReplyRepository;
import com.burgerplace.bproduct.service.ReplyService;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReplyTests {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private ReplyService replyService;

    @Test
    public void insertOne() {

        Long pno = 100L;

        Product product = Product.builder().pno(pno).build();

        Reply reply = Reply.builder()
                .replyText("Reply......1")
                .replyer("replyer00")
                .product(product)
                .build();

        replyRepository.save(reply);
    }

    @Test
    public void testInsertDummies() {

        Long[] pnoArr = { 99L, 96L, 92L, 84L, 81L };

        for (Long pno : pnoArr) {

            Product product = Product.builder().pno(pno).build();

            for (int i = 0; i < 50; i++) {

                Reply reply = Reply.builder()
                        .replyText("Reply..." + pno + "--" + i)
                        .replyer("replyer" + i)
                        .product(product)
                        .build();

                replyRepository.save(reply);
            }
        }
    }

    @Test
    public void testListBoard(){
        Long pno = 99L;
        Pageable pageable = PageRequest.of(0, 10, Sort.by("pRno").ascending());

        Page<Reply> result = replyRepository.listProduct(pno, pageable);

        
        result.get().forEach(r -> log.info(r));
    
    }

    @Test
    public void testCount() {
        
        Long pno = 99L;

        long count = replyRepository.getCountProduct(pno);

        log.info("count: " + count);
    }

    @Test
    public void testListLast() {

        ReplyPageRequestDTO requestDTO = ReplyPageRequestDTO
        .builder()
        .pno(99L)
        .last(true)
        .build();

        log.info(replyService.list(requestDTO));

        // replyService.list(requestDTO);
    }
}