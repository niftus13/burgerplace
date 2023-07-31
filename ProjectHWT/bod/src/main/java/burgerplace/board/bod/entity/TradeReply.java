package burgerplace.board.bod.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private TradeBoard tradeBoard;


}
