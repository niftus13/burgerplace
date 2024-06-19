package com.burgerplace.main.repository;

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

import com.burgerplace.main.common.pageDto.PageRequestDTO;
import com.burgerplace.main.common.pageDto.PageResponseDTO;
import com.burgerplace.main.mainPage.dto.MainListRcntDTO;
import com.burgerplace.main.mainPage.dto.MainReadDTO;
import com.burgerplace.main.mainPage.entity.Main;
import com.burgerplace.main.mainPage.repository.MainRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MainRepositoryTests {

    @Autowired
    private MainRepository mainRepository;

    @Test
    public void testInsert() {

        for (int i = 0; i < 100; i++) {

            Main main = Main
                    .builder()
                    .title("Sample Title")
                    .content("Sample Content" + i)
                    .writer("Sample Writer" + (i % 10))
                    .build();

            mainRepository.save(main);
        }
    }

    @Test
    public void testRead() {

        Long bno = 1L;

        Optional<Main> result = mainRepository.findById(1L);

        log.info("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");

        Main main = result.orElseThrow();

        log.info(main);
    }

    @Test
    public void testUpdate() {

        Long bno = 1L;

        Optional<Main> result = mainRepository.findById(1L);

        Main main = result.orElseThrow();

        main.changeTitle("Update Title");

        mainRepository.save(main);
    }

    @Test
    public void testQuery1() {

        java.util.List<Main> list = mainRepository.findByTitleContaining("1");

        log.info("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        log.info(list.size());
        log.info(list);
    }

    @Test
    public void testQuery1_1() {

        java.util.List<Main> list = mainRepository.listTitle("1");

        log.info("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        log.info(list.size());
        log.info(list);
    }

    @Test
    public void testQuery1_2() {

        java.util.List<Object[]> list = mainRepository.listTitle2("1");

        log.info("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        log.info(list.size());
        list.forEach(arr -> log.info(Arrays.toString(arr)));
    }

    @Test
    public void testQuery1_3() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Object[]> result = mainRepository.listTitle3("1", pageable);

        log.info(result);
    }

    @Commit
    @Transactional
    @Test
    public void testModify() {

        Long bno = 100L;
        String title = "Modified Title 100";

        int count = mainRepository.modifyTitle(title, bno);

        log.info("-----------" + count);
    }

    @Test
    public void testQuery2() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Main> result = mainRepository.findByContentContaining("1", pageable);

        log.info("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        log.info(result);
    }

    @Test
    public void testNative() {
       List<Object[]> result = mainRepository.listNative();

       result.forEach(arr -> log.info(Arrays.toString(arr)));
    }

    @Test
    public void testSearch1() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        
        Page<Main> result = mainRepository.search1("tcw", "1", pageable);

        log.info(result.getTotalElements());

        result.get().forEach(b->log.info(b));
    }

    @Test
    public void testListWithRcnt() {

        List<Object[]> result = mainRepository.getListWithRcnt();

        for(Object[] result2 : result) {
            log.info(Arrays.toString(result2));
        }
    }

    @Test
    public void searchWithRcntSearch() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        mainRepository.searchWithRcnt("tcw", "1", pageable);
    }

    @Test
    public void test0706_1() {

        PageRequestDTO pageRequest = new PageRequestDTO();

        PageResponseDTO<MainListRcntDTO> responseDTO =
        mainRepository.searchDTORcnt(pageRequest);

        log.info(responseDTO);
    }

    @Test
    public void testReadOne() {
        Long bno = 77L;

        MainReadDTO dto = mainRepository.readOne(bno);

        log.info(dto);
        log.info(dto.getRegDate());
        log.info(dto.getModDate());
        log.info(dto.getClass().getName());
    }
}
