package com.burgerplace.main.mainPage.dto;

import lombok.Data;

@Data
public class ReplyDTO {
    
    private Long rno;

    private String replyText;

    private String replyFile;

    private String replyer;
}
