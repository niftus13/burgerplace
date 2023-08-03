package org.zerock.j1.service;

import org.springframework.transaction.annotation.Transactional;
import org.zerock.j1.dto.FreeReplyDTO;
import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.dto.ReplyPageRequestDTO;

@Transactional
public interface FreeReplyService {

    PageResponseDTO<FreeReplyDTO> list(ReplyPageRequestDTO requestDTO);

    Long register(FreeReplyDTO replyDTO);

    FreeReplyDTO read(Long fRno);

    void remove(Long fRno);

    void modify(FreeReplyDTO replyDTO);

}