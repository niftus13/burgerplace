package org.zerock.j1.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class FreeReplyDTO {
    
    private Long fRno;

    private String replyText;

    private String nickname;
    // bno값 추가
    private Long bno;

    private boolean fHidden;
    
    // 수정시 쓰기위한 함수 2개 설정  바꾸는 함수들만 
    public void changeText(String text){
        this.replyText = text;
    }


}
