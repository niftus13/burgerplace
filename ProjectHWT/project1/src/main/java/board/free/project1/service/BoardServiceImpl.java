package board.free.project1.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import board.free.project1.dto.BoardDTO;
import board.free.project1.dto.BoardListRcntDTO;
import board.free.project1.dto.PageRequestDTO;
import board.free.project1.dto.PageResponseDTO;
import board.free.project1.entity.Board;
import board.free.project1.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    private final ModelMapper modelMapper;
    // 등록작업을 위해서 사용한다.
    // private final ModelMapper modelMapper;

    @Override
    public PageResponseDTO <BoardListRcntDTO> listRcnt(PageRequestDTO pageRequestDTO) {

        log.info("--------------------------");
        log.info(pageRequestDTO);
        // 제대로 왔니? => 확인해보니까 pageRequestDTO page=1, size=10, k,t=null 출력된다.

        return boardRepository.searchDTORcnt(pageRequestDTO);
        // 자 이제 repository에 searchDTORcnt로 가서 pageRequestDTO page=1, size=10, k,t=null를 매개변수로 주자.
        // 그리고 그 결과를 반환하장!!

    }

    @Override
    public BoardDTO getOne(Long bno) {

        Optional<Board> result = boardRepository.findById(bno);

        Board board = result.orElseThrow(); 

        BoardDTO dto = modelMapper.map(board, BoardDTO.class);
        // board => dto로 db에 있는 것을 가져와서 보여준다.

        return dto;
    }

}
