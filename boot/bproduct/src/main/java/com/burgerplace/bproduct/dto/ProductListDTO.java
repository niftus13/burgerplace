package com.burgerplace.bproduct.dto;

import lombok.Data;

@Data
public class ProductListDTO {
    
    private Long pno;
    private String pname;
    private int price;
    private String pfname;

    private long reviewCnt;

    private double reviewAvg;
}
