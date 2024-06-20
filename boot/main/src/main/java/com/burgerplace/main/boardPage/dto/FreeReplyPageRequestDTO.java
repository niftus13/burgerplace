package com.burgerplace.main.boardPage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false) 
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FreeReplyPageRequestDTO extends PageRequestDTO {
    
    private Long freeBno;
    @Builder.Default
    private int page = 1;
    @Builder.Default
    private int size =20;
    
    private boolean last;
}
