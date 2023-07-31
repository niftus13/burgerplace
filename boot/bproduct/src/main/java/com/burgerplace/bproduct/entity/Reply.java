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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="Product_reply")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "product")
public class Reply {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pRno;

    private String replyText;

    private String replyFile;

    private String replyer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
