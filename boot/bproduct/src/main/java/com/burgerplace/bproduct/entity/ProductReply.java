package com.burgerplace.bproduct.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "product")
@Table(name = "productReply")
public class ProductReply {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pRno;

    private String replyText;

    private String replyFile;

    private String nickName; // replyer

    private Integer grade;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    // 수정시 쓰기위한 함수 --바꾸는 함수들만
    public void changeText(String text){
        this.replyText = text;
    }

    public void changeFile(String fileName){

        this.replyFile = fileName;
    }

    public void changeGrade(Integer grade){
        this.grade = grade;
    }

}
