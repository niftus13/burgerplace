package org.zerock.j1.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// 댓글 갯수 까지 나오는 DTO
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardListRcntDTO {

    private Long fbno;
    private String ftitle;
    private String nickname;
    private Long replyCount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;


}
