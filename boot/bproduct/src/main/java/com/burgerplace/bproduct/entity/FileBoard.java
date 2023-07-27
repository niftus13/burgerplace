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
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "images") // images를 제외한다
public class FileBoard {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;
    
    private String content;
    
    private String writer;

    // 종속성 설정, 부모가 상태가 변하면 자식도 변하게 한다
    // @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "board")
    @Builder.Default
    // size 값 만큼 한번에 처리한다
    @BatchSize(size = 20)
    private List<FileBoardImage> images = new ArrayList<>();

    public void addImage(FileBoardImage boardImage) {

        // size값으로 순번을 준다
        boardImage.changeOrd(images.size());

        images.add(boardImage);
    }

    public void cleanImages() {
        images.clear();
    }
}
