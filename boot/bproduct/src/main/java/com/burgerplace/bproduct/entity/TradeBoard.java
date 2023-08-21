package com.burgerplace.bproduct.entity;

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
public class TradeBoard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tradeBno;

    private String tradeTitle;

    private String tradeContent;

    private String nickname;

    
    @BatchSize(size = 20)
    @OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name ="tradeBoard")
    @Builder.Default
    private List<TradeBoardImage> tradeImages = new ArrayList<>();

        // oneToMany 메서드
        public void addImage(String name) {

            TradeBoardImage pImage = TradeBoardImage.builder().imageName(name)
                    .ord(tradeImages.size()).build();
    
            tradeImages.add(pImage);
        } 
    
        // oneToMany 메서드
        public void cleanImages(){
            tradeImages.clear();
        }
    
    
        // 해당 보드 메서드
        public void changeTitle(String title){
            this.tradeTitle=title;
        }
    
        // 해당 보드 메서드
        public void changeContent(String content){
            this.tradeContent=content;
        }


    
}
