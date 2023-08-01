package burgerplace.board.bod.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TBoardImage {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itBno;

    private String pName;

    private Integer ord;

    private String uuid;

    public void changeOrd(int ord){
        this.ord=ord;
    }

}
