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
@Table(name = "TradeReply")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString(exclude = "tradeBoard")
public class TradeReply {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tRno;

    private String nickname;

    private String replyText;

    private LocalDateTime replyDate;

    private Boolean tHidden;

    @ManyToOne
    @JoinColumn(name = "mem_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "TBoard_tBno")
    private TradeBoard tradeBoard;

    @BatchSize(size = 5)
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = {CascadeType.ALL})
    @JoinColumn(name="TReply_itRno")
    @Builder.Default
    private List<TReplyImage> tRImages = new ArrayList<>();

    public void trAddImages(TReplyImage tReplyImage){

        tReplyImage.changeOrd(tRImages.size());

        tRImages.add(tReplyImage);
    }


}
