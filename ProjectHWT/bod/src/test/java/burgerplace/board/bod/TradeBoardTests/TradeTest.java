package burgerplace.board.bod.TradeBoardTests;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import burgerplace.board.bod.entity.TBoardImage;
import burgerplace.board.bod.entity.TReplyImage;
import burgerplace.board.bod.entity.TradeBoard;
import burgerplace.board.bod.entity.TradeReply;
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

        @Test
        public void tradeReplyInsert(){

            for(int i=0; i<100 ; i++){

            TradeReply tr = TradeReply.builder()
            .nickname("hwt"+i+"siuuuu")
            .replyText("kkkkk"+i)
            .replyDate(null)
            .tHidden(true)
            .build();

            TReplyImage tri1 = TReplyImage.builder()
            .pName("III.JPG")
            .uuid(UUID.randomUUID().toString())
            .build();

            TReplyImage tri2 = TReplyImage.builder()
            .pName("III.JPG")
            .uuid(UUID.randomUUID().toString())
            .build();

            tr.trAddImages(tri1);
            tr.trAddImages(tri2);

            trRepo.save(tr);

            }
        }

}
