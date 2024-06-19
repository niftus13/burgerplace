package com.burgerplace.main.productPage.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;



@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;

    private String pname;

    private int price;

    private Boolean event;

    private String brand;

    // column이 되니 조심하게 만들어야 된다.
    // delFlag
    private boolean delFlag;


    // hashTag elementCollection
    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Tag> hashTags = new HashSet<>();

    // hashtag add method
    public void addTag(String tagName){
        Tag tag = Tag.builder().tagName(tagName).build();
        hashTags.add(tag);
    }

    // hashtag clear method
    public void clearTag(){
        hashTags.clear();
    }



    // image elementCollection
    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private List<ProductImage> images = new ArrayList<>();

    // 상품을 추가하는 method
    public void addImage(String name){

        ProductImage pImage = ProductImage.builder().pfname(name)
        .ord(images.size()).build();

        images.add(pImage);
    }
    // 크롤링 이미지 이름 파싱
    public void parseImg(String name){

        String uuid = name.substring(0,36);
        String imgName = name.substring(37);

        ProductImage pImage = ProductImage.builder()
                .UUID(uuid)
                .pfname(imgName)
                .ord(images.size()).build();
        images.add(pImage);
    }
    // 이미지 파일들을 싹 비워주는 method
    public void clearImages(){
        images.clear();
    }

    public void changePrice(int price) {
        this.price = price;
    }
    
    public void changePname(String pname) {
        this.pname = pname;
    }

    public void changeDel(boolean delFlag) {
        this.delFlag = delFlag;
    }

}
