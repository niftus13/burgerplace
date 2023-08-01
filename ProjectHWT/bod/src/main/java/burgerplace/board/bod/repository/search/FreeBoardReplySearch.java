package burgerplace.board.bod.repository.search;

import burgerplace.board.bod.dto.FreeBoardReplyListDTO;
import burgerplace.board.bod.dto.PageRequestDTO;
import burgerplace.board.bod.dto.PageResponseDTO;

public interface FreeBoardReplySearch {

    PageResponseDTO<FreeBoardReplyListDTO> list(PageRequestDTO pageRequestDTO);

}
