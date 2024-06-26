package com.burgerplace.main.boardPage.entity;

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

    private String nickname;


    @ManyToOne(fetch = FetchType.LAZY)
    private FreeBoard freeBoard;


    @BatchSize(size = 20)
    @OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name ="freeReply")
    @Builder.Default
    private List<FreeReplyImage> freeImages = new ArrayList<>();

    
    // oneToMany 메서드
    public void addImage(String name) {

        FreeReplyImage pImage = FreeReplyImage.builder().imageName(name)
                .ord(freeImages.size()).build();

        freeImages.add(pImage);
    } 

    // oneToMany 메서드
    public void cleanImages(){
        freeImages.clear();
    }




    // 댓글처리 메서드
    public void changeText(String text) {
        this.replyText = text;
    }

    

    
}
