package board.free.project1.service;

import board.free.project1.dto.BoardDTO;
import board.free.project1.dto.BoardListRcntDTO;
import board.free.project1.dto.PageRequestDTO;
import board.free.project1.dto.PageResponseDTO;
import jakarta.transaction.Transactional;

@Transactional
public interface BoardService {

    PageResponseDTO<BoardListRcntDTO> listRcnt(PageRequestDTO pageRequestDTO);

    BoardDTO getOne(Long bno);

}
