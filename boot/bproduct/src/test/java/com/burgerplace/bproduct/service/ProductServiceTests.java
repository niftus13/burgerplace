package com.burgerplace.bproduct.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ProductServiceTests {
    @Autowired
    ProductService productService;



    @Test
    public void parsingTest1(){
        productService.parsing();
    }

    @Test
    public void parseImgStringTest(){
        String name = "074f2c31-b3ae-4514-bc5d-1255a231d8a9_(HOT)꿀꽈배기싸이순살+콜라2.png";
        log.info("name = " + name);
        String uuid = name.substring(0,name.indexOf("_"));
        log.info("uuid = " + uuid);
        String oName = name.substring(37);
        log.info(oName);
    }


}
