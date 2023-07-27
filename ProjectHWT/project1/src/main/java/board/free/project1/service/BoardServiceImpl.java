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

        return boardRepository.searchDTORcnt(pageRequestDTO);

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
