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
import org.zerock.j1.domain.TradeReply;
import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.dto.ReplyPageRequestDTO;
import org.zerock.j1.dto.TradeReplyDTO;
import org.zerock.j1.dto.TradeReplyPageRequestDTO;
import org.zerock.j1.repository.TradeReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class TradeReplyServiceImpl implements TradeReplyService {


    private final TradeReplyRepository replyRepository;
    private final ModelMapper modelMapper;

    @Override
    public PageResponseDTO<TradeReplyDTO> list(TradeReplyPageRequestDTO requestDTO) {

        boolean last = requestDTO.isLast();

        int pageNum = requestDTO.getPage();

        if (last) {
            long totalCount = replyRepository.getCountBoard(requestDTO.getTBno());
            pageNum = (int) (Math.ceil(totalCount / (double) requestDTO.getSize()));
            pageNum = pageNum <= 0 ? 1 : pageNum;
        }

        Pageable pageable = PageRequest.of(pageNum - 1, requestDTO.getSize(), Sort.by("tRno").ascending());

        Page<TradeReply> result = replyRepository.listBoard(requestDTO.getTBno(), pageable);

        log.info("------------------------------------");

        long totalReplyCount = result.getTotalElements();

        List<TradeReplyDTO> dtoList = result.get()
                .map(en -> modelMapper.map(en, TradeReplyDTO.class))
                .collect(Collectors.toList());

        PageResponseDTO<TradeReplyDTO> responseDTO = new PageResponseDTO<>(dtoList, totalReplyCount, requestDTO);
        responseDTO.setPage(pageNum);

        return responseDTO;
    }

    @Override
    public Long register(TradeReplyDTO replyDTO) {

        TradeReply reply = modelMapper.map(replyDTO, TradeReply.class);

        log.info("reply....");
        log.info(reply);

        Long newRno = replyRepository.save(reply).getTRno();

        return newRno;

    }

    @Override
    public TradeReplyDTO read(Long tRno) {

        Optional<TradeReply> result = replyRepository.findById(tRno);

        TradeReply reply = result.orElseThrow();

        return modelMapper.map(reply, TradeReplyDTO.class);

    }

    @Override
    public void remove(Long tRno) {

        Optional<TradeReply> result = replyRepository.findById(tRno);

        TradeReply reply = result.orElseThrow();

        reply.changerText("해당 글은 삭제되었습니다.");
        reply.changeFile(null);

        replyRepository.save(reply);

    }

    @Override
    public void modify(TradeReplyDTO replyDTO) {

        Optional<TradeReply> result = replyRepository.findById(replyDTO.getTRno());

        TradeReply reply = result.orElseThrow();

        reply.changerText(replyDTO.getReplyText());
        reply.changeFile(replyDTO.getReplyFile());

        replyRepository.save(reply);

    }

    
}
