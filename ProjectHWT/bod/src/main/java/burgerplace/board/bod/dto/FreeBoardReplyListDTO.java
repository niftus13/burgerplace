package burgerplace.board.bod.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FreeBoardReplyListDTO {

    private Integer fRno;

    private String nickname;

    private String uuid;

    private String pName;
    
}
