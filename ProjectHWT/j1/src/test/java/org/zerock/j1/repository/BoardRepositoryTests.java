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
import org.zerock.j1.domain.FreeBoard;
import org.zerock.j1.dto.FreeBoardListRcntDTO;
import org.zerock.j1.dto.FreeBoardReadDTO;
import org.zerock.j1.dto.PageRequestDTO;
import org.zerock.j1.dto.PageResponseDTO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    // 테스트를 위한 DI
    @Autowired
    private FreeBoardRepository fbRepository;

    // Insert 테스트
    @Test
    public void testInsert() {
        for (int i = 0; i < 100; i++) {
            FreeBoard fBoard = FreeBoard.builder()
                    .fTitle("Sample Title" + i)
                    .fContent("Sample Content" + i)
                    .nickname("user" + (i % 10))
                    .build();

            fbRepository.save(fBoard);
        }
    }

    // Read 테스트
    @Test
    public void testRead() {

        Long fBno = 1L;

        Optional<FreeBoard> result = fbRepository.findById(fBno);

        log.info("--------------------------------");

        FreeBoard fBoard = result.orElseThrow();

        log.info(fBoard);
    }

    // Update 테스트
    @Test
    public void testUpdate() {
        // JPA 업데이트는 Mybatis 방식과 다르다.
        // 조회후 save를 다시하는 방법
        // 이방식이 복잡하고 너무길다 싶을때 쓰는게
        // Query method 방식
        Long fBno = 1L;

        Optional<FreeBoard> result = fbRepository.findById(fBno);

        FreeBoard fBoard = result.orElseThrow();
        fBoard.changeTitle("Update Title");

        fbRepository.save(fBoard);
    }

    // Query method 관련 테스트
    @Test
    public void testQuery1() {

        java.util.List<FreeBoard> list = fbRepository.findByfTitleContaining("1");

        log.info("----------------------");
        log.info(list.size());
        log.info(list);
    }

    // JPQL 관련 테스트
    @Test
    public void testQuery1_1() {

        java.util.List<FreeBoard> list = fbRepository.listTitle("1");

        log.info("----------------------");
        log.info(list.size());
        log.info(list);
    }

    // JPQL 실제 사용이유의 방식의 테스트
    @Test
    public void testQuery1_2() {

        java.util.List<Object[]> list = fbRepository.listTitle2("1");

        log.info("----------------------");
        log.info(list.size());
        list.forEach(arr -> log.info(Arrays.toString(arr)));
    }

    // Page 처리 JPQL
    @Test
    public void testQuery1_3() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("fBno").descending());

        Page<Object[]> result = fbRepository.listTitle3("1", pageable);

        log.info(result);

    }
    // JPQL Modify Test
    @Commit
    @Transactional
    @Test
    public void testModify(){
        Long bno = 100L;
        String title = "Modified Title 100";
        int count = fbRepository.modifyTitle(title, bno);

        log.info("-----------------" + count);

    }

    // 검색하면서 Paging 처리가 되는방법
    @Test
    public void testQuery2() {

        Pageable pageable = PageRequest.of(
                0, 10, Sort.by("fBno").descending());

        Page<FreeBoard> result = fbRepository.findByfContentContaining("1", pageable);

        log.info("------------------------");
        log.info(result);
    }

    // @Test
    // public void testNative(){

    //     List<Object[]> result = fbRepository.listNative();
        
    //     result.forEach(arr -> log.info(Arrays.toString(arr)));
        
    // }

    // QBoard를 써서 만든테스트
    @Test
    public void testSearch1(){

       Pageable pageable = PageRequest.of(0, 10, Sort.by("fBno").descending());
       Page<FreeBoard> result = fbRepository.search1("tcw","1", pageable);
       log.info(result.getTotalElements());

       result.get().forEach(b -> log.info(b));
    }

    @Test
    public void testListWithRcnt(){

        List<Object[]> result = fbRepository.getListWithRcnt();

        for (Object[] result2 : result){
            log.info(Arrays.toString(result2));
        }
    }

    @Test
    public void testListWithRcntSearch(){

         Pageable pageable = PageRequest.of(0, 10, Sort.by("fBno").descending());

         fbRepository.searchWithRcnt("tcw", "1", pageable);
    }

    @Test
    public void test0706_1(){

        PageRequestDTO pageRequest = new PageRequestDTO();

        PageResponseDTO<FreeBoardListRcntDTO> responseDTO= fbRepository.searchDTORcnt(pageRequest);

        log.info(responseDTO);
    }


    @Test
    public void testReadOne(){
        Long bno = 77L;
        
        FreeBoardReadDTO dto= fbRepository.readOne(bno);

        log.info(dto);
        log.info(dto.getRegDate());
        log.info(dto.getModDate());
        log.info(dto.getClass().getName());
    }

}
