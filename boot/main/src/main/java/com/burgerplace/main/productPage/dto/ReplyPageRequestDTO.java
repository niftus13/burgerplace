package com.burgerplace.main.productPage.dto;

import com.burgerplace.main.common.pageDto.PageRequestDTO;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper=false) 
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReplyPageRequestDTO extends PageRequestDTO {
    
    private Long pno;

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 50;

    private boolean last;
}