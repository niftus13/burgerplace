package com.burgerplace.main.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public interface MainReadDTO {

    Long getBno();

    String getTitle();

    String getContent();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime getRegDate();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime getModDate();
}
