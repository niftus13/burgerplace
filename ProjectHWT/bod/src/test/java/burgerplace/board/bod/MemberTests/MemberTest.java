package burgerplace.board.bod.MemberTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import burgerplace.board.bod.entity.Member;
import burgerplace.board.bod.repository.MemberRepository;

@SpringBootTest
public class MemberTest {

    @Autowired
    MemberRepository repo;

    @Test
    public void insert(){

        Member mem = Member.builder()
        .id("dnjsxoghd")
        .nickname("홍원태")
        .pw("123456")
        .Date(null)
        .admin(true)
        .build();
        
        repo.save(mem);

    }
    
}
