package burgerplace.board.bod.entity;

import java.time.LocalDateTime;
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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="TradeBoard")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "tImages")
public class TradeBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tfBno;

    private String tTitle;

    private String tContent;

    private String nickname;

    private LocalDateTime tDate;

    private Boolean tFinish;

    @BatchSize(size = 20)
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = {CascadeType.ALL})
    @JoinColumn(name = "Tboard")
    @Builder.Default
    private List<TBoardImage> tImages = new ArrayList<>();

    
    public void addImage(TBoardImage tboardImage){

        tboardImage.changeOrd(tImages.size());

        tImages.add(tboardImage);
    }
    
}
