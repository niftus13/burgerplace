package org.zerock.j2.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.j2.dto.FreeReplyDTO;
import org.zerock.j2.dto.FreeReplyPageRequestDTO;
import org.zerock.j2.dto.PageResponseDTO;
import org.zerock.j2.entity.FreeReply;
import org.zerock.j2.repository.FreeReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class FreeReplyServiceImpl implements FreeReplyService {

    private final FreeReplyRepository replyRepository;
    private final ModelMapper modelMapper;

    @Override
    public PageResponseDTO<FreeReplyDTO> list(FreeReplyPageRequestDTO requestDTO) {


        boolean last = requestDTO.isLast();
        // requestDTO의 값에서 last 값만 가져와 last변수에 담는다.
        log.info(last);

        int pageNum = requestDTO.getPage();
        log.info(pageNum);
        // 이하동문

        if (last) {
            long totalCount = replyRepository.getCountBoard(requestDTO.getFreeBno());
            // 만약 댓글이 50개인 99번 게시물은 totlacount가 50이 떠야한다.

            pageNum = (int) (Math.ceil(totalCount / (double) requestDTO.getSize()));

            pageNum = pageNum <= 0 ? 1 : pageNum;
        }

        Pageable pageable = PageRequest.of(pageNum - 1, requestDTO.getSize(), Sort.by("freeRno").ascending());

        Page<FreeReply> result = replyRepository.listBoard(requestDTO.getFreeBno(), pageable);

        log.info("------------------------------------");

        long totalReplyCount = result.getTotalElements();

        List<FreeReplyDTO> dtoList = result.get()
                .map(en -> modelMapper.map(en, FreeReplyDTO.class))
                .collect(Collectors.toList());
                // entity의 값을 dto로 바꾸고 리스트로 만들고 dtoList에 저장한다.
                log.info(dtoList+ "Reply dto List ");

        PageResponseDTO<FreeReplyDTO> responseDTO = new PageResponseDTO<>(dtoList, totalReplyCount, requestDTO);
        responseDTO.setPage(pageNum);

        log.info(responseDTO+" responseDTO");
        return responseDTO;
        
    }

    @Override
    public Long register(FreeReplyDTO replyDTO) {

        FreeReply reply = modelMapper.map(replyDTO, FreeReply.class);

        log.info("reply....");
        log.info(reply);

        Long newRno = replyRepository.save(reply).getFreeRno();

        return newRno;

    }

    @Override
    public FreeReplyDTO read(Long freeRno) {

        Optional<FreeReply> result = replyRepository.findById(freeRno);

        FreeReply reply = result.orElseThrow();

        return modelMapper.map(reply, FreeReplyDTO.class);

    }

    @Override
    public void remove(Long freeRno) {

        Optional<FreeReply> result = replyRepository.findById(freeRno);

        FreeReply reply = result.orElseThrow();

        reply.changeText("해당 글은 삭제되었습니다.");
        reply.changeFile(null);

        replyRepository.save(reply);

    }

    @Override
    public void modify(FreeReplyDTO replyDTO) {

        Optional<FreeReply> result = replyRepository.findById(replyDTO.getFreeRno());

        FreeReply reply = result.orElseThrow();

        reply.changeText(replyDTO.getReplyText());
        reply.changeFile(replyDTO.getReplyFile());

        replyRepository.save(reply);

    }

}