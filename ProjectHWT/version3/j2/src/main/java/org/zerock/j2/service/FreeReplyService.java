package org.zerock.j2.service;




import org.zerock.j2.dto.FreeReplyDTO;
import org.zerock.j2.dto.FreeReplyPageRequestDTO;
import org.zerock.j2.dto.PageResponseDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface FreeReplyService {

    PageResponseDTO<FreeReplyDTO> list(FreeReplyPageRequestDTO requestDTO);

    FreeReplyDTO read(Long freeRno);

    Long register(FreeReplyDTO replyDTO);

    void remove(Long freeRno);

    void modify(FreeReplyDTO replyDTO);

}