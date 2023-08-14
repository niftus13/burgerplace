package org.zerock.j2.dto;

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
public class FreeBoardListRcntDTO {

    private Long freeBno;
    private String freeTitle;
    private String nickname;
    private Long replyCount;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;


}
