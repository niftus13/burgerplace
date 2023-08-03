package org.zerock.j1.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FreeBoardDTO {
    
    // Board Entity => Board DTO 용도는생각 하지 말고 생성
    // JSONFormat || JsonIgnore 날짜시간 처리시 사용한다.
    // JsonIgnore json형식으로 만들지 말라.

    private Long fBno;

    private String fTitle;

    private String fContent;

    private String nickname;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modDate;

}
