package burgerplace.board.bod.dto;

import lombok.Data;

@Data
public class FreeReplyDTO {
    
    private Long fRno;

    private String replyText;


    private String replyer;
    // bno값 추가
    private Long fBno;
    
    // 수정시 쓰기위한 함수 2개 설정  바꾸는 함수들만
    public void changeText(String text){
        this.replyText = text;
    }



}
