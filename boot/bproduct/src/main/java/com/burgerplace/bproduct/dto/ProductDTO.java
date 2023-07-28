package com.burgerplace.bproduct.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductDTO {

    // 기본적으로 Entity와 맞춰두고 variation을 준다

    private Long pno;
    private String pname;
    private String pdesc;
    private int price;

    private List<String> images;

    // 등록/수정 업로드된  파일 데이터를 수집하는 용도
    private List<MultipartFile> files;

}
