package org.zerock.j1.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// 날짜추가를 위해서 BaseEntity를 상속받는다 (중복코드 절감)
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class FreeBoard extends BaseEntity{
    
    // PK 설정 및 Auto increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fBno;
    
    @Column(length = 200, nullable = false)
    private String fTitle;

    @Column(length = 1000, nullable = false)
    private String fContent;

    @Column(length = 50, nullable = false)
    private String nickname;
    
    

    // setter 대신에 change를 쓴다

    public void changeTitle(String fTitle){
        this.fTitle=fTitle;
    }

}
