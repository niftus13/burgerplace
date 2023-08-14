package org.zerock.j2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class FreeBoardImage {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long imageFreeBno;

    private String imageName;

    private int ord;

    public void changeOrd (int ord){
        this.ord = ord;
    }
    
    

}
