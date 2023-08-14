package org.zerock.j1.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.j1.domain.Reply;
import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.dto.ReplyDTO;
import org.zerock.j1.dto.ReplyPageRequestDTO;
import org.zerock.j1.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper;

    @Override
    public PageResponseDTO<ReplyDTO> list(ReplyPageRequestDTO requestDTO) {


        boolean last = requestDTO.isLast();
        // requestDTO의 값에서 last 값만 가져와 last변수에 담는다.
        log.info(last);

        int pageNum = requestDTO.getPage();
        log.info(pageNum);
        // 이하동문

        if (last) {
            long totalCount = replyRepository.getCountBoard(requestDTO.getFbno());
            // 만약 댓글이 50개인 99번 게시물은 totlacount가 50이 떠야한다.

            pageNum = (int) (Math.ceil(totalCount / (double) requestDTO.getSize()));

            pageNum = pageNum <= 0 ? 1 : pageNum;
        }

        Pageable pageable = PageRequest.of(pageNum - 1, requestDTO.getSize(), Sort.by("frno").ascending());

        Page<Reply> result = replyRepository.listBoard(requestDTO.getFbno(), pageable);

        log.info("------------------------------------");

        long totalReplyCount = result.getTotalElements();

        List<ReplyDTO> dtoList = result.get()
                .map(en -> modelMapper.map(en, ReplyDTO.class))
                .collect(Collectors.toList());
                // entity의 값을 dto로 바꾸고 리스트로 만들고 dtoList에 저장한다.
                log.info(dtoList+ "Reply dto List ");

        PageResponseDTO<ReplyDTO> responseDTO = new PageResponseDTO<>(dtoList, totalReplyCount, requestDTO);
        responseDTO.setPage(pageNum);

        log.info(responseDTO+" responseDTO");
        return responseDTO;
        
    }

    @Override
    public Long register(ReplyDTO replyDTO) {

        Reply reply = modelMapper.map(replyDTO, Reply.class);

        log.info("reply....");
        log.info(reply);

        Long newRno = replyRepository.save(reply).getFrno();

        return newRno;

    }

    @Override
    public ReplyDTO read(Long frno) {

        Optional<Reply> result = replyRepository.findById(frno);

        Reply reply = result.orElseThrow();

        return modelMapper.map(reply, ReplyDTO.class);

    }

    @Override
    public void remove(Long frno) {

        Optional<Reply> result = replyRepository.findById(frno);

        Reply reply = result.orElseThrow();

        reply.changeText("해당 글은 삭제되었습니다.");
        reply.changeFile(null);

        replyRepository.save(reply);

    }

    @Override
    public void modify(ReplyDTO replyDTO) {

        Optional<Reply> result = replyRepository.findById(replyDTO.getFrno());

        Reply reply = result.orElseThrow();

        reply.changeText(replyDTO.getReplyText());
        reply.changeFile(replyDTO.getReplyFile());

        replyRepository.save(reply);

    }

}