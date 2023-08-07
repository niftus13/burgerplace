package org.zerock.j1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReplyPageRequestDTO extends PageRequestDTO {
    
    private Long fBno;
    @Builder.Default
    private int page = 1;
    @Builder.Default
    private int size =20;

    private boolean last;
}
