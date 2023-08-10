package com.burgerplace.bproduct.dto;

import lombok.Data;

@Data
public class ProductListDTO {
    
    private Long pno;

    private String pname;

    private String brand;

    private int price;

    private String UUID;
    
    private String pfname;

    private long replyCnt;

    private double gradeAvg;
}
