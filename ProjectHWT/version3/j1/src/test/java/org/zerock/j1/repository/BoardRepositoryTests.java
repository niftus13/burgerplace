package org.zerock.j1.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.j1.domain.Board;
import org.zerock.j1.dto.BoardListRcntDTO;
import org.zerock.j1.dto.BoardReadDTO;
import org.zerock.j1.dto.PageRequestDTO;
import org.zerock.j1.dto.PageResponseDTO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    // 테스트를 위한 DI
    @Autowired
    private BoardRepository boardRepository;

    // Insert 테스트
    @Test
    public void testInsert() {
        for (int i = 0; i < 100; i++) {
            Board board = Board.builder()
                    .ftitle("Sample Title" + i)
                    .fcontent("Sample Content" + i)
                    .nickname("user" + (i % 10))
                    .build();

            boardRepository.save(board);
        }
    }

    // Read 테스트
    @Test
    public void testRead() {

        Long fbno = 1L;

        Optional<Board> result = boardRepository.findById(fbno);

        log.info("--------------------------------");

        Board board = result.orElseThrow();

        log.info(board);
    }

    // Update 테스트
    @Test
    public void testUpdate() {
        // JPA 업데이트는 Mybatis 방식과 다르다.
        // 조회후 save를 다시하는 방법
        // 이방식이 복잡하고 너무길다 싶을때 쓰는게
        // Query method 방식
        Long fbno = 1L;

        Optional<Board> result = boardRepository.findById(fbno);
        // bno=1에 해당하는 로우를 찾고 result에 담는다.

        Board board = result.orElseThrow();
        // 예외 처리한다.

        board.changeTitle("Update Title");
        // 엔티티의 메서드를 통해 title을 변경한다.

        boardRepository.save(board);
        // 엔티티 처리를 데이터베이스에서 저장한다.
    }

    // Query method 관련 테스트
    @Test
    public void testQuery1() {

        java.util.List<Board> list = boardRepository.findByftitleContaining("1");

        log.info("----------------------");
        log.info(list.size()); //19
        log.info(list); // 찾은 행들이 나온다.
        // '%1%' => findByTitleContaining
    }

    // JPQL 관련 테스트
    @Test
    public void testQuery1_1() {

        java.util.List<Board> list = boardRepository.listTitle("1");

        log.info("----------------------");
        log.info(list.size());
        log.info(list);
        // 위의 테스트와 똑같은 결과가 나온다. => 위처럼 쓰는 쿼리는 잘 사용하지 않아서 jpql를 활용한 테스트
    }

    // JPQL 실제 사용이유의 방식의 테스트
    @Test
    public void testQuery1_2() {

        java.util.List<Object[]> list = boardRepository.listTitle2("1");

        log.info("----------------------");
        log.info(list.size()); //19
        list.forEach(arr -> log.info(Arrays.toString(arr))); // bno와 title만 나오는 쿼리이다.
    }

    // Page 처리 JPQL
    @Test
    public void testQuery1_3() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("fbno").descending());
        // 페이지 처리하는 쿼리이다. limit page, size order by bno desc => 이런 형식이다.
        log.info(pageable);

        Page<Object[]> result = boardRepository.listTitle3("1", pageable);

        log.info(result);
        // 페이지 처리 쿼리와 카운트 쿼리가 나간다.
    }


    // JPQL Modify Test
    @Commit
    @Transactional
    @Test
    public void testModify(){
        Long fbno = 50L;
        String ftitle = "Modified Title 100";
        int count = boardRepository.modifyTitle(ftitle, fbno);

        log.info("-----------------" + count);

    }

    // 검색하면서 Paging 처리가 되는방법
    @Test
    public void testQuery2() {

        Pageable pageable = PageRequest.of(
                0, 10, Sort.by("fbno").descending());

        Page<Board> result = boardRepository.findByfcontentContaining("1", pageable);

        log.info("------------------------");
        log.info(result);
        // testQeury1_3과 같은 결과를 부른다.
    }

    @Test
    public void testNative(){

        List<Object[]> result = boardRepository.listNative();
        
        result.forEach(arr -> log.info(Arrays.toString(arr)));
        
    }

    // QBoard를 써서 만든테스트
    @Test
    public void testSearch1(){

       Pageable pageable = PageRequest.of(0, 10, Sort.by("fbno").descending());
       // 일반적인 페이지 처리 메서드
       Page<Board> result = boardRepository.search1("tcw","1", pageable);
       log.info(result.getTotalElements()+"kkkk_+kkkkkk");

       result.get().forEach(b -> log.info(b));
    }

    @Test
    public void testListWithRcnt(){

        List<Object[]> result = boardRepository.getListWithRcnt();

        for (Object[] result2 : result){
            log.info(Arrays.toString(result2));
        }
    }

    @Test
    public void testListWithRcntSearch(){

         Pageable pageable = PageRequest.of(0, 10, Sort.by("fbno").descending());

         boardRepository.searchWithRcnt("tcw", "1", pageable);
    }

    @Test
    public void test0706_1(){

        PageRequestDTO pageRequest = new PageRequestDTO();

        PageResponseDTO<BoardListRcntDTO> responseDTO= boardRepository.searchDTORcnt(pageRequest);

        log.info(responseDTO);
    }


    @Test
    public void testReadOne(){
        Long fbno = 77L;
        
        BoardReadDTO dto= boardRepository.readOne(fbno);

        log.info(dto);
        log.info(dto.getRegDate());
        log.info(dto.getModDate());
        log.info(dto.getClass().getName());
    }

}
