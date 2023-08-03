package burgerplace.board.bod.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import burgerplace.board.bod.dto.FreeBoardListDTO;
import burgerplace.board.bod.dto.PageRequestDTO;
import burgerplace.board.bod.dto.PageResponseDTO;
import burgerplace.board.bod.entity.FreeBoard;

public interface FreeBoardSearch {

    Page<FreeBoard> search1 (String searchType, String searchKeyword, Pageable pageable);

            // 기본 게시판 검색에 + 댓글
    Page<Object[]> searchWithRcnt(String searchType, String searchKeyword, Pageable pageable);

        // DTO로 받는 검색방식 method
    PageResponseDTO<FreeBoardListDTO> searchDTORcnt(PageRequestDTO requestDTO);

        // Pageable을 반환하는 것을 만들어주는 method
        default Pageable makePageable(PageRequestDTO requestDTO){

            Pageable pageable = PageRequest.of(
                requestDTO.getPage() -1,
                requestDTO.getSize(),
                Sort.by("bno").descending()
                );
                
                return pageable;
        }

    
}
