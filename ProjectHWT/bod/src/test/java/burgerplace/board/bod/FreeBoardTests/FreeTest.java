package burgerplace.board.bod.FreeBoardTests;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import burgerplace.board.bod.entity.FBoardImage;
import burgerplace.board.bod.entity.FReplyImage;
import burgerplace.board.bod.entity.FreeBoard;
import burgerplace.board.bod.entity.FreeReply;
import burgerplace.board.bod.repository.FreeBoardRepository;
import burgerplace.board.bod.repository.FreeReplyRepository;

@SpringBootTest
public class FreeTest {

    @Autowired
    FreeBoardRepository repo;

    @Autowired
    FreeReplyRepository FrRepo;

    @Test
    public void insert() {

        for (int i = 0; i < 100; i++) {

            FreeBoard fileBoard = new FreeBoard();
            
            FBoardImage img2 = FBoardImage.builder()
                    .uuid(UUID.randomUUID().toString())
                    .pName("aaa.jpg")
                    .build();

            FBoardImage img3 = FBoardImage.builder()
                    .uuid(UUID.randomUUID().toString())
                    .pName("bbb.jpg")
                    .build();

            fileBoard.addImages(img2);

            fileBoard.addImages(img3);
            repo.save(fileBoard);

        } // end for

    }

    @Test
    public void replyImageInsert() {
        for (int i = 0; i < 100; i++) {

            FreeReply fr = FreeReply.builder()
                    .nickname("리버풀 전사")
                    .replyText("오늘은 안돼요 내 사랑 이대로는")
                    .replyDate(null)
                    .fHidden(false)
                    .build();

            FReplyImage fri1 = FReplyImage.builder()
                    .uuid(UUID.randomUUID().toString())
                    .pName("ADD.JPG")
                    .build();

            FReplyImage fri2 = FReplyImage.builder()
                    .uuid(UUID.randomUUID().toString())
                    .pName("KKKK.JPG")
                    .build();

                    fr.fAddImages(fri1);
                    fr.fAddImages(fri2);

                    FrRepo.save(fr);

        }
        
    }

}
