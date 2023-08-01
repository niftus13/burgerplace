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

@Entity
@Table(name="FReply_Image")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class FReplyImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ifRno;

    private String pName;

    private Integer ord;

    private String uuid;

    
    public void changeOrd(int ord){
        this.ord=ord;
    }

    
}
