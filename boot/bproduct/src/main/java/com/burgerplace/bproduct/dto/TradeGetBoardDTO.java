package com.burgerplace.bproduct.dto;

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
public class TradeGetBoardDTO {

    private long tradeBno;

    private String tradeTitle;

    private String tradeContent;

    private String nickname;

        // DB 처리용
    @Builder.Default
    private List<String> tradeImages = new ArrayList<>();

    // 등록/수정 업로드된  파일 데이터를 수집하는 용도
    @Builder.Default
    private List<MultipartFile> tradeFiles = new ArrayList<>();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modDate;
    
}
