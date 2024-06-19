package com.burgerplace.main.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MainDTO {

    private Long bno;

    private String title;

    private String content;

    private String writer;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 효과 없음
    private LocalDateTime regDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modDate;
}
