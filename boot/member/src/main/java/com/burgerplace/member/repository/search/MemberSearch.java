package com.burgerplace.member.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.burgerplace.member.dto.MemberDTO;
import com.burgerplace.member.dto.PageRequestDTO;
import com.burgerplace.member.dto.PageResponseDTO;
import com.burgerplace.member.entity.MemberEntity;

public interface MemberSearch {

    // List를 뽑는 메소드
    
    // List<Board> search1();

    //Page<Board> search1 (Pageable pageable);
    // 기본 게시판의 검색조건
    Page<MemberEntity> search1 (String searchType, String searchKeyword, Pageable pageable);

    // DTO로 받는 검색방식 method
    PageResponseDTO<MemberDTO> searchDTORcnt(PageRequestDTO requestDTO);

    // Pageable을 반환하는 것을 만들어주는 method
    default Pageable makePageable(PageRequestDTO requestDTO){

        Pageable pageable = PageRequest.of(
            requestDTO.getPage() -1,
            requestDTO.getSize(),
            Sort.by("id").descending()
            );
            
            return pageable;
    }
    
}
