package com.burgerplace.main.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReplyTests {

    @Autowired
    private ProductReplyRepository replyRepository;

    @Autowired
    private ProductReplyService replyService;

    @Test
    public void insertOne() {

        Long pno = 1L;

        Product product = Product.builder().pno(pno).build();

        // Reply reply = Reply.builder()
        //         .replyText("Reply......1")
        //         .replyer("replyer00")
        //         .product(product)
        //         .build();

        ProductReply reply = ProductReply.builder()
        .replyText("Reply......1")
        .nickName("replyer00")
        .product(product)
        .grade(6)
        .build();

        replyRepository.save(reply);
    }

    @Test
    public void testInsertDummies() {

        Long[] pnoArr = {1L ,2L ,3L ,4L ,5L };

        for (Long pno : pnoArr) {

            Product product = Product.builder().pno(pno).build();

            for (int i = 0; i < 20; i++) {
                

                ProductReply reply = ProductReply.builder()
                        .replyText("Reply..." + pno + "--" + i)
                        .nickName("replyer" + i)
                        .grade((int)(Math.random()*(10)+1))
                        .product(product)
                        .build();

                replyRepository.save(reply);
            }
        }
    }

    @Test
    public void testListBoard(){
        Long pno = 1L;
        Pageable pageable = PageRequest.of(0, 10, Sort.by("pRno").ascending());

        Page<ProductReply> result = replyRepository.listProduct(pno, pageable);

        
        result.get().forEach(r -> log.info(r));
    
    }

    @Test
    public void testCount() {
        
        Long pno = 1L;

        long count = replyRepository.getCountProduct(pno);

        log.info("count: " + count);
    }

    @Test
    public void testListLast() {

        ReplyPageRequestDTO requestDTO = ReplyPageRequestDTO
        .builder()
        .pno(1L)
        .last(true)
        .build();

        log.info(replyService.list(requestDTO));

        // replyService.list(requestDTO);
    }
}