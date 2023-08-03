package burgerplace.board.bod.service;

import burgerplace.board.bod.dto.FreeReplyDTO;
import burgerplace.board.bod.dto.PageResponseDTO;
import burgerplace.board.bod.dto.ReplyPageRequestDTO;
import jakarta.transaction.Transactional;

@Transactional
public interface FreeReplyService {
  PageResponseDTO<FreeReplyDTO> list(ReplyPageRequestDTO requestDTO);

  Long register(FreeReplyDTO replyDTO);

  FreeReplyDTO read(Long fRno);

  void remove(Long fRno);

  void modify(FreeReplyDTO replyDTO);
}
