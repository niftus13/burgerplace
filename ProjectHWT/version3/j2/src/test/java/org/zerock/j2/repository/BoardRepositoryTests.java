package org.zerock.j2.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.j2.dto.FreeBoardListRcntDTO;

import org.zerock.j2.dto.PageRequestDTO;
import org.zerock.j2.dto.PageResponseDTO;
import org.zerock.j2.entity.FreeBoard;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    // 테스트를 위한 DI
    @Autowired
    private FreeBoardRepository boardRepository;

    // Insert 테스트
    @Test
    public void testInsert() {
        for (int i = 0; i < 100; i++) {
            FreeBoard board = FreeBoard.builder()
                    .freeTitle("Sample Title" + i)
                    .freeContent("Sample Content" + i)
                    .nickname("user" + (i % 10))
                    .build();

            board.addImage(UUID.randomUUID().toString() + "aaa_.jpg");
            board.addImage(UUID.randomUUID().toString() + "bbb_.jpg");
            board.addImage(UUID.randomUUID().toString() + "ccc_.jpg");

            boardRepository.save(board);
        }
    }

    // Read 테스트
    // Transactional이 없으면 오류
    @Test
    @Transactional
    public void testRead() {

        Long freeBno = 1L;

        Optional<FreeBoard> result = boardRepository.findById(freeBno);

        log.info("--------------------------------");

        FreeBoard board = result.orElseThrow();

        log.info(board);
    }

    // Update 테스트
    @Test
    public void testUpdate() {

        Long freeBno = 2L;

        Optional<FreeBoard> result = boardRepository.findById(freeBno);
        // bno=1에 해당하는 로우를 찾고 result에 담는다.

        FreeBoard board = result.orElseThrow();
        // 예외 처리한다.

        board.changeTitle("Update Title");
        // 엔티티의 메서드를 통해 title을 변경한다.

        boardRepository.save(board);
        // 엔티티 처리를 데이터베이스에서 저장한다.
    }

    // Query method 관련 테스트
    @Test
    public void testQuery1() {

        java.util.List<FreeBoard> list = boardRepository.findByFreeTitleContaining("1");

        log.info("----------------------");
        log.info(list.size()); // 19
        log.info(list); // 찾은 행들이 나온다.
        // '%1%' => findByTitleContaining
    }

    // JPQL 관련 테스트
    @Test
    public void testQuery1_1() {

        java.util.List<FreeBoard> list = boardRepository.listTitle("1");

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
        log.info(list.size()); // 19
        list.forEach(arr -> log.info(Arrays.toString(arr))); // bno와 title만 나오는 쿼리이다.
    }

    // Page 처리 JPQL
    @Test
    public void testQuery1_3() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("freeBno").descending());
        // 페이지 처리하는 쿼리이다. limit page, size order by bno desc => 이런 형식이다.
        log.info(pageable);

        Page<Object[]> result = boardRepository.listTitle3("1", pageable);

        log.info(result + " hwt testQeury1_3");
        // 페이지 처리 쿼리와 카운트 쿼리가 나간다.
    }

    // JPQL Modify Test
    @Commit
    @Transactional
    @Test
    public void testModify() {

        Long fbno = 50L;

        String ftitle = "Modified Title 100";

        int count = boardRepository.modifyTitle(ftitle, fbno);

        log.info("-----------------" + count);

    }

    // 검색하면서 Paging 처리가 되는방법
    @Test
    public void testQuery2() {

        Pageable pageable = PageRequest.of(
                0, 10, Sort.by("freeBno").descending());

        Page<FreeBoard> result = boardRepository.findByFreeContentContaining("1", pageable);

        log.info("------------------------");
        log.info(result);
        // testQeury1_3과 같은 결과를 부른다.
    }

    @Test
    public void testNative() {

        List<Object[]> result = boardRepository.listNative();

        result.forEach(arr -> log.info(Arrays.toString(arr)));

    }

    // QBoard를 써서 만든테스트
    @Test
    public void testSearch1() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("freeBno").descending());
        // 일반적인 페이지 처리 메서드
        Page<FreeBoard> result = boardRepository.search1("tcw", "1", pageable);
        log.info(result.getTotalElements() + "kkkk_+kkkkkk");

        result.get().forEach(b -> log.info(b));
    }

    // @Test
    // public void testListWithRcnt(){

    // List<Object[]> result = boardRepository.getListWithRcnt();

    // for (Object[] result2 : result){
    // log.info(Arrays.toString(result2));
    // }
    // }

    @Test
    public void testListWithRcntSearch() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("freeBno").descending());

        boardRepository.searchWithRcnt("tcw", "1", pageable);
    }

    @Test
    public void test0706_1() {

        PageRequestDTO pageRequest = new PageRequestDTO();

        PageResponseDTO<FreeBoardListRcntDTO> responseDTO = boardRepository.searchDTORcnt(pageRequest);

        log.info(responseDTO);
    }

    // @Test
    // public void testReadOne(){
    // Long freeBno = 77L;

    // FreeBoardReadDTO dto= boardRepository.readOne(freeBno);

    // log.info(dto);
    // log.info(dto.getRegDate());
    // log.info(dto.getModDate());
    // log.info(dto.getClass().getName());
    // }

}
