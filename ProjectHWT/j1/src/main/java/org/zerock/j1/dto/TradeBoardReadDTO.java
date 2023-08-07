package org.zerock.j1.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public interface TradeBoardReadDTO {
    
    Long gettBno();

    String gettTitle();

    String gettContent();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime getRegDate();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime getModDate();

}
