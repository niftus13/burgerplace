package burgerplace.board.bod.repository.search;

import burgerplace.board.bod.dto.FreeBoardListDTO;
import burgerplace.board.bod.dto.PageRequestDTO;
import burgerplace.board.bod.dto.PageResponseDTO;

public interface FreeBoardSearch {
    
    PageResponseDTO<FreeBoardListDTO> list(PageRequestDTO pageRequestDTO);

}
