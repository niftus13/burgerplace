package com.burgerplace.main.mainPage.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.burgerplace.main.common.pageDto.PageRequestDTO;
import com.burgerplace.main.common.pageDto.PageResponseDTO;
import com.burgerplace.main.mainPage.dto.MainListRcntDTO;
import com.burgerplace.main.mainPage.entity.Main;

public interface MainSearch {

    // v1
    Page<Main> search1(String searchType, String keyword, Pageable pageable);

    // v2
    Page<Object[]> searchWithRcnt(String searchType, String keyword, Pageable pageable);

    // v3
    PageResponseDTO<MainListRcntDTO> searchDTORcnt(PageRequestDTO requestDTO);

    default Pageable makePageable(PageRequestDTO requestDTO) {

        Pageable pageable = PageRequest.of(
            requestDTO.getPage() -1,
            requestDTO.getSize(),
            Sort.by("bno").descending());

            return pageable;
    }
}
