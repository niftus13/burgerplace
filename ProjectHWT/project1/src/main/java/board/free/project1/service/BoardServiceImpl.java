package board.free.project1.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import board.free.project1.dto.BoardDTO;
import board.free.project1.dto.BoardListRcntDTO;
import board.free.project1.dto.PageRequestDTO;
import board.free.project1.dto.PageResponseDTO;
import board.free.project1.entity.Board;
import board.free.project1.entity.Reply;
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
    public PageResponseDTO<BoardListRcntDTO> listRcnt(PageRequestDTO pageRequestDTO) {

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

    @Override
    public Long register(BoardDTO boardDTO) {

        Board board = modelMapper.map(boardDTO, Board.class);
        // 등록을 눌렀을 때 boardDTO가 파라미터로 들어오고 mapper를 사용해서 Board db에 매칭시킨다.

        log.info("Board....");
        log.info(board); // db 값

        Long newBno = boardRepository.save(board).getBno(); //db값을 저장한다.

        return newBno;


    }
}
