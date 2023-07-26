package board.free.project1.service;

import board.free.project1.dto.PageResponseDTO;
import board.free.project1.dto.ReplyDTO;
import board.free.project1.dto.ReplyPageRequestDTO;
import jakarta.transaction.Transactional;

@Transactional
public interface ReplyService {

    // reply list
    PageResponseDTO<ReplyDTO> list(ReplyPageRequestDTO requestDTO);

    // reply insert
    Long register(ReplyDTO replyDTO);

    // reply read
    ReplyDTO read(Long rno);

    // reply delete
    void remove(Long rno);

    // reply modify
    void modify(ReplyDTO replyDTO);

}
