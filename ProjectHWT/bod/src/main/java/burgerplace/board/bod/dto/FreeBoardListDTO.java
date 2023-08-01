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
public class FreeBoardListDTO {

    private Integer fBno;

    private String fTitle;

    private String uuid;

    private String pName;
    
}
