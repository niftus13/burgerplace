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
import org.zerock.j1.domain.FreeReply;
import org.zerock.j1.dto.FreeReplyDTO;
import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.dto.ReplyPageRequestDTO;
import org.zerock.j1.repository.FreeReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class FreeReplyServiceImpl implements FreeReplyService {

    private final FreeReplyRepository frRepository;
    private final ModelMapper modelMapper;

    @Override
    public PageResponseDTO<FreeReplyDTO> list(ReplyPageRequestDTO requestDTO) {

        boolean last = requestDTO.isLast();

        int pageNum = requestDTO.getPage();

        if (last) {
            long totalCount = frRepository.getCountBoard(requestDTO.getFBno());
            pageNum = (int) (Math.ceil(totalCount / (double) requestDTO.getSize()));
            pageNum = pageNum <= 0 ? 1 : pageNum;
        }

        Pageable pageable = PageRequest.of(pageNum - 1, requestDTO.getSize(), Sort.by("rno").ascending());

        Page<FreeReply> result = frRepository.listBoard(requestDTO.getFBno(), pageable);

        log.info("------------------------------------");

        long totalReplyCount = result.getTotalElements();

        List<FreeReplyDTO> dtoList = result.get()
                .map(en -> modelMapper.map(en, FreeReplyDTO.class))
                .collect(Collectors.toList());

        PageResponseDTO<FreeReplyDTO> responseDTO = new PageResponseDTO<>(dtoList, totalReplyCount, requestDTO);
        responseDTO.setPage(pageNum);

        return responseDTO;
    }

    @Override
    public Long register(FreeReplyDTO replyDTO) {

        FreeReply reply = modelMapper.map(replyDTO, FreeReply.class);

        log.info("reply....");
        log.info(reply);

        Long newRno = frRepository.save(reply).getFRno();

        return newRno;

    }

    @Override
    public FreeReplyDTO read(Long fRno) {

        Optional<FreeReply> result = frRepository.findById(fRno);

        FreeReply reply = result.orElseThrow();

        return modelMapper.map(reply, FreeReplyDTO.class);

    }

    @Override
    public void remove(Long fRno) {

        Optional<FreeReply> result = frRepository.findById(fRno);

        FreeReply reply = result.orElseThrow();

        reply.changeText("해당 글은 삭제되었습니다.");

        frRepository.save(reply);

    }

    @Override
    public void modify(FreeReplyDTO replyDTO) {

        Optional<FreeReply> result = frRepository.findById(replyDTO.getFRno());

        FreeReply reply = result.orElseThrow();

        reply.changeText(replyDTO.getReplyText());

        frRepository.save(reply);

    }

}