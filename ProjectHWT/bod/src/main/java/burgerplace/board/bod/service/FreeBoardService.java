package burgerplace.board.bod.service;

import burgerplace.board.bod.dto.FreeBoardListDTO;
import burgerplace.board.bod.dto.PageRequestDTO;
import burgerplace.board.bod.dto.PageResponseDTO;
import jakarta.transaction.Transactional;

@Transactional
public interface FreeBoardService {

    PageResponseDTO<FreeBoardListDTO> list (PageRequestDTO pageRequestDTO);
    // 페이징 처리

    
}
