package org.zerock.j1.dto;

import lombok.Data;

@Data
public class TradeReplyDTO {
    
    private Long tRno;

    private String replyText;

    private String replyFile;

    private String nickname;

    private Long tBno;

    public void changeText(String text){
        this.replyText=text;
    }

    public void changeFile(String fileName){

        this.replyFile = fileName;
    }

}
