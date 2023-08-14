package org.zerock.j2.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public interface FreeBoardReadDTO {
    
    Long getFreeBno();

    String getFreeTitle();

    String getFreeContent();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime getRegDate();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime getModDate();

}
