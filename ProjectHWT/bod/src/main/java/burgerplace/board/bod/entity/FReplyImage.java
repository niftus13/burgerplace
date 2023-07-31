package burgerplace.board.bod.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="FReply_Image")
public class FReplyImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ifRno;

    private String pName;

    private Integer ord;

    private String uuid;

    
}
