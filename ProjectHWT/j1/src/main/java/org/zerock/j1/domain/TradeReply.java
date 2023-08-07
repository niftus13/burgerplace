package org.zerock.j1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "tradeBoard")
public class TradeReply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tRno;

    private String nickname;

    private String replyText;

    private boolean tHidden;

    @ManyToOne(fetch = FetchType.LAZY)
    private TradeBoard tradeBoard;

    public void changerText(String text) {
        this.replyText = text;
    }
    
}
