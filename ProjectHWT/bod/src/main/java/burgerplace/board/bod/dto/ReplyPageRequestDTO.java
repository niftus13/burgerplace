package burgerplace.board.bod.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReplyPageRequestDTO extends PageRequestDTO {
    
    private int fBno;

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size =20;

    private boolean last;

}
