package com.burgerplace.bproduct.dto;

import java.util.List;
import java.util.Set;



import lombok.Data;

@Data
public class ProductListDTO {
    
    private Long pno;

    private String pname;

    private String brand;

    private int price;

    private String UUID;
    
    private String pfname;

    private List<String> hashTags;

    private long replyCnt;

    private double gradeAvg;
}
