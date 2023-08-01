package burgerplace.board.bod.TradeBoardTests;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import burgerplace.board.bod.entity.TBoardImage;
import burgerplace.board.bod.entity.TradeBoard;
import burgerplace.board.bod.repository.TradeBoardRepository;
import burgerplace.board.bod.repository.TradeReplyRepository;

@SpringBootTest
public class TradeTest {

    @Autowired
    TradeBoardRepository tbRepo;

    @Autowired
    TradeReplyRepository trRepo;

    @Test
    public void tradeInsert() {

        for (int i = 0; i < 100; i++) {
            TradeBoard tb = TradeBoard.builder()
                    .tTitle("맥 알리스터")
                    .tContent("Her we GO")
                    .nickname("hwt")
                    .tDate(null)
                    .tFinish(false)
                    .build();

            TBoardImage ti1 = TBoardImage.builder()
                    .pName("aaaa.jpg")
                    .uuid(UUID.randomUUID().toString())
                    .build();

            TBoardImage ti2 = TBoardImage.builder()
                    .pName("aaaa.jpg")
                    .uuid(UUID.randomUUID().toString())
                    .build();

            tb.addImage(ti1);
            tb.addImage(ti2);

            tbRepo.save(tb);


        }
    }

}
