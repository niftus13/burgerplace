package org.zerock.j1.dto;

import lombok.Data;

@Data
public class ReplyDTO {
    
    private Long rno;

    private String replyText;

    private String replyFile;

    private String replyer;
    // bno값 추가
    private Long bno;
    
    // 수정시 쓰기위한 함수 2개 설정  바꾸는 함수들만 
    public void changeText(String text){
        this.replyText = text;
    }

    public void changeFile(String fileName){

        this.replyFile = fileName;
    }

}
