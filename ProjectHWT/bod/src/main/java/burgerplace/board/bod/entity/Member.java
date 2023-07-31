package burgerplace.board.bod.entity;

import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Member")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Member {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String pw;

    private String nickname;

    private LocalDateTime Date;

    private boolean admin;
}
