package com.burgerplace.bproduct.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "images")
@Builder
public class Product {

    private Long pno;

    private String pname;

    private String pdesc;

    private String writer;

    private int price;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private List<ProductImage> images = new ArrayList<>();

    // 상품을 추가하는 method
    public void addImage(String name){

        ProductImage pImage = ProductImage.builder().fname(name)
        .ord(images.size()).build();

        images.add(pImage);
    }
    // 이미지 파일들을 싹 비워주는 method
    public void clearImages(){
        images.clear();
    }

    public void changePrice() {
        this.price = price;
    }
    
}
