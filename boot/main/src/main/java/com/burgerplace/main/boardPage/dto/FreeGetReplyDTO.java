package com.burgerplace.main.boardPage.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FreeGetReplyDTO {

    private long freeRno;

    private String replyText;

    private String nickname;

    // bno값 추가
    private long freeBno;

    // DB 처리용
    @Builder.Default
    private List<String> freeImages = new ArrayList<>();

    // 등록/수정 업로드된 파일 데이터를 수집하는 용도
    @Builder.Default
    private List<MultipartFile> freeFiles = new ArrayList<>();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modDate;

    // 수정시 쓰기위한 함수 2개 설정 바꾸는 함수들만
    public void changeText(String text) {
        this.replyText = text;
    }

}
