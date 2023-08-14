package org.zerock.j2.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.j2.dto.FreeBoardListRcntDTO;
import org.zerock.j2.dto.PageRequestDTO;
import org.zerock.j2.dto.PageResponseDTO;
import org.zerock.j2.entity.FreeBoard;


// 검색기능을위한 interface 
// 이름 일치시켜야된다. BoardSearch --> BoardSearchImpl
public interface FreeBoardSearch {
    
    
    // List를 뽑는 메소드
    
    // List<Board> search1();

    //Page<Board> search1 (Pageable pageable);
    // 기본 게시판의 검색조건
    Page<FreeBoard> search1 (String searchType, String searchKeyword, Pageable pageable);

    // 기본 게시판 검색에 + 댓글
    Page<Object[]> searchWithRcnt(String searchType, String searchKeyword, Pageable pageable);


    // DTO로 받는 검색방식 method
    PageResponseDTO<FreeBoardListRcntDTO> searchDTORcnt(PageRequestDTO requestDTO);


    // Pageable을 반환하는 것을 만들어주는 method
    default Pageable makePageable(PageRequestDTO requestDTO){

        Pageable pageable = PageRequest.of(
            requestDTO.getPage() -1,
            requestDTO.getSize(),
            Sort.by("freeBno").descending()
            );
            // 들어온 페이지 0, size, bno 역순 pageable의 변수에 존재
            
            return pageable;
    }
}
