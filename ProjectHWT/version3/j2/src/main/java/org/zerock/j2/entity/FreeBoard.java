package org.zerock.j2.entity;

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
public class FreeBoard extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long freeBno;

    private String freeTitle;

    private String freeContent;

    private String nickname;

    @BatchSize(size = 20)
    @OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name ="freeBoard")
    @Builder.Default
    private List<FreeBoardImage> freeImages = new ArrayList<>();

    // oneToMany 메서드
    public void addImage(String name) {

        FreeBoardImage pImage = FreeBoardImage.builder().imageName(name)
                .ord(freeImages.size()).build();

        freeImages.add(pImage);
    } 

    // oneToMany 메서드
    public void cleanImages(){
        freeImages.clear();
    }


    // 해당 보드 메서드
    public void changeTitle(String title){
        this.freeTitle=title;
    }

    // 해당 보드 메서드
    public void changeContent(String content){
        this.freeContent=content;
    }
}
