package com.burgerplace.bproduct.dto;

import java.time.LocalDate;


import lombok.Data;

@Data
public class ProductReplyDTO {

    private Long prno;

    private String nickname;

    private String replyText;

    private Integer grade;

    private LocalDate replyDate;

    private String replyFile;
    
}
