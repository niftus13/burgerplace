package com.burgerplace.bproduct.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ProductReplyDTO {

    private Long pRno;

    private String nickname;

    private String replyText;

    private Integer grade;

    private Date replyDate;

    private String replyFile;

    private String replyer;
    
}
