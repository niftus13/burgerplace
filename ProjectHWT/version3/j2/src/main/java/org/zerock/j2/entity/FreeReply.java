package org.zerock.j2.entity;

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
@ToString
public class FreeReply extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long freeRno;

    private String replyText;

    private String replyFile;

    private String nickname;


    @ManyToOne(fetch = FetchType.LAZY)
    private FreeBoard freeBoard;

    public void changeText(String text) {
        this.replyText = text;
    }

    public void changeFile(String fileName) {
        this.replyFile = fileName;
    }

    
}
