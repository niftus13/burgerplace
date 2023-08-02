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
@ToString(exclude = "tImages")
public class TradeBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tBno;

    private String tTitle;

    private String tContent;

    private String nickname;

    private Boolean tFinish;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany
    @JoinColumn(name="TBoard_tBno")
    private TradeReply tradeReply;

    @BatchSize(size = 20)
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = {CascadeType.ALL})
    @JoinColumn(name = "TBoard_tBno")
    @Builder.Default
    private List<TBoardImage> tImages = new ArrayList<>();

    
    public void addImage(TBoardImage tboardImage){

        tboardImage.changeOrd(tImages.size());

        tImages.add(tboardImage);
    }
    
}
