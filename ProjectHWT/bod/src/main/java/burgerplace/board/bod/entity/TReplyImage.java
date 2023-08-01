package burgerplace.board.bod.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "TReply_Image")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class TReplyImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itRno;

    private String pName;

    private Integer ord;

    private String uuid;


    
}
