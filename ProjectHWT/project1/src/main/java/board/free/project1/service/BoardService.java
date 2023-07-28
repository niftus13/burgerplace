package board.free.project1.service;

import board.free.project1.dto.BoardDTO;
import board.free.project1.dto.BoardListRcntDTO;
import board.free.project1.dto.PageRequestDTO;
import board.free.project1.dto.PageResponseDTO;
import jakarta.transaction.Transactional;

@Transactional
public interface BoardService {

    PageResponseDTO<BoardListRcntDTO> listRcnt(PageRequestDTO pageRequestDTO);
    // controller에서 여기에 왔다. 내가 가지고 온 것은 page=1, size=10, keyword,type=null
    // 구현체로 가자.

    BoardDTO getOne(Long bno);

}
