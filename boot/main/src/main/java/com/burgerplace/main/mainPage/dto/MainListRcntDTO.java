package com.burgerplace.main.mainPage.dto;

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
public class MainListRcntDTO {

    private Long bno;
    private String title;
    private String writer;
    private long replyCount;
}
