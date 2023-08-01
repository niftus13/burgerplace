package burgerplace.board.bod.entity;

import java.time.LocalDateTime;
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
@Table(name="FreeReply")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FreeReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fRno;

    private String nickname;

    private String replyText;

    private LocalDateTime replyDate;

    private boolean fHidden;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="FBoard_fBno")
    private FreeBoard freeBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mem_id")
    private Member member;

    @BatchSize(size = 5)
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = {CascadeType.ALL})
    @JoinColumn(name = "FR_ifRno")
    @Builder.Default
    private List<FReplyImage> frImages = new ArrayList<>();

    public void fAddImages(FReplyImage fReplyImage){
        fReplyImage.changeOrd(frImages.size());

        frImages.add(fReplyImage);
    }



    
    
}
