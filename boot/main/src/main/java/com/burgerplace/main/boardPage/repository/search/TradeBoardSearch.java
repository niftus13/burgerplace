package com.burgerplace.main.boardPage.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.burgerplace.main.boardPage.dto.PageRequestDTO;
import com.burgerplace.main.boardPage.dto.PageResponseDTO;
import com.burgerplace.main.boardPage.dto.TradeBoardListRcntDTO;
import com.burgerplace.main.boardPage.entity.TradeBoard;

public interface TradeBoardSearch {

    Page<TradeBoard> search1(String searchType, String searchKeyword, Pageable pageable);



    Page<Object[]> searchWithRcnt(String searchType, String searchKeyword, Pageable pageable);



    PageResponseDTO<TradeBoardListRcntDTO> searchDTORcnt(PageRequestDTO requestDTO);



    default Pageable makePageable(PageRequestDTO requestDTO) {

        Pageable pageable = PageRequest.of(
                requestDTO.getPage() - 1,
                requestDTO.getSize(),
                Sort.by("tradeBno").descending());
        // 들어온 페이지 0, size, bno 역순 pageable의 변수에 존재

        return pageable;
    }

}
