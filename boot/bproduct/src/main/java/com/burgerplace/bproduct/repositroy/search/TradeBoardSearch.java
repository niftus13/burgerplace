package com.burgerplace.bproduct.repositroy.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.zerock.j2.dto.PageRequestDTO;
import org.zerock.j2.dto.PageResponseDTO;
import org.zerock.j2.dto.TradeBoardListRcntDTO;
import org.zerock.j2.entity.TradeBoard;

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
