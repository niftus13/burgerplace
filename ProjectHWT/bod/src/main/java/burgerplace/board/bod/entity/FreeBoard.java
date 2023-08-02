package burgerplace.board.bod.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.BatchSize;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "fImages")
public class FreeBoard  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fBno;

    private String fTitle;

    private String fContent;

    private String nickname;
        

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany
    @JoinColumn(name="FBoard_fBno")
    private FreeReply freeReply;

    @BatchSize(size = 20)
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = {CascadeType.ALL})
    @JoinColumn(name = "Fboard_fBno")
    @Builder.Default
    private List<FBoardImage> fImages = new ArrayList<>();

    public void addImages(FBoardImage fBoardImage){
        fBoardImage.changeOrd(fImages.size());

        fImages.add(fBoardImage);
    }

    


    
}
