package com.burgerplace.bproduct.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.burgerplace.bproduct.dto.PageResponseDTO;
import com.burgerplace.bproduct.dto.ReplyDTO;
import com.burgerplace.bproduct.dto.ReplyPageRequestDTO;
import com.burgerplace.bproduct.entity.Reply;
import com.burgerplace.bproduct.repositroy.ReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{
    
    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper;

    @Override
    public PageResponseDTO<ReplyDTO> list(ReplyPageRequestDTO requestDTO) {

        boolean last = requestDTO.isLast();

        int pageNum = requestDTO.getPage();
        
        if(last) {
            long totalCount = replyRepository.getCountBoard(requestDTO.getBno());

            pageNum = (int)(Math.ceil(totalCount/(double)requestDTO.getSize()));
        }

        Pageable pageable = PageRequest.of(pageNum -1, requestDTO.getSize(), Sort.by("bno").ascending());

        Page<Reply> result = replyRepository.listBoard(requestDTO.getBno(), pageable);

        log.info("--------------------------");

        log.info(result.getNumber());
        log.info(result.getContent());

        long totalReplyCount = result.getTotalElements();

        List<ReplyDTO> dtoList = result.get().map(en -> modelMapper.map(en, ReplyDTO.class)).collect(Collectors.toList());
        

        PageResponseDTO<ReplyDTO> responseDTO = new PageResponseDTO<>(dtoList, totalReplyCount, requestDTO);

        responseDTO.setPage(pageNum);

        return responseDTO;
    }
}