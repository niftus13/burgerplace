package com.burgerplace.main.memberPage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    
    private String id;

    private String pw;

    private String nickname;

    private Boolean admin;

    private boolean delFlag;

}
