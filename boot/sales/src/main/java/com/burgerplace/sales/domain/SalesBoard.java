package com.burgerplace.sales.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Product_Event") // table 이름 지정
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class SalesBoard extends BaseEntity{
    
    // PK 설정 및 Auto increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pEno; // 번호
    // 컬럼 추가및 설정
    @Column(length = 10, nullable = false)
    private String category; // 카테고리
    @Column(length = 30, nullable = false)
    private String title; // 제목(DB설계엔 없는데 필요할거같아서 넣음)
    @Column(length = 500, nullable = false)
    private String eventInfo; // 내용(할인정보)
    @Column(nullable = false)
    private Integer eventPrice; // 할인금액

    // setter 대신에 change를 쓴다
    public void changeTitle(String title){
        this.title=title;
    }

}