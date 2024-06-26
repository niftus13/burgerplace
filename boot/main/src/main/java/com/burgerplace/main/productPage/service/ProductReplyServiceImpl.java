package com.burgerplace.main.productPage.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.burgerplace.main.common.pageDto.PageResponseDTO;
import com.burgerplace.main.productPage.domain.ProductReply;
import com.burgerplace.main.productPage.dto.ProductReplyDTO;
import com.burgerplace.main.productPage.dto.ReplyPageRequestDTO;
import com.burgerplace.main.productPage.repository.ProductReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductReplyServiceImpl implements ProductReplyService{

    private final ProductReplyRepository replyRepository;
    private final ModelMapper modelMapper;

    @Override
    public PageResponseDTO<ProductReplyDTO> list(ReplyPageRequestDTO requestDTO) {
        
        boolean last = requestDTO.isLast();

        int pageNum = requestDTO.getPage();

        if(last){
            long totalCount = replyRepository.getCountProduct(requestDTO.getPno());

            pageNum = (int)(Math.ceil((totalCount/(double)requestDTO.getSize())));
        }

        Pageable pageable = PageRequest.of(pageNum -1, requestDTO.getSize(), Sort.by("prno").ascending());

        Page<ProductReply> result = replyRepository.listProduct(requestDTO.getPno(), pageable);

        log.info("--------------------------");
        log.info(result.getContent());

        long totalReplyCount = result.getTotalElements();
        log.info(totalReplyCount);

        
        List<ProductReplyDTO> dtoList = result.get().map(entity -> modelMapper.map(entity, ProductReplyDTO.class)).collect(Collectors.toList());
        
        PageResponseDTO<ProductReplyDTO> responseDTO = new PageResponseDTO<>(dtoList, totalReplyCount, requestDTO);

        responseDTO.setPage(pageNum);

        return responseDTO;

    }

    @Override
    public Long register(ProductReplyDTO replyDTO) {
       
        ProductReply reply = modelMapper.map(replyDTO,ProductReply.class);

        Long newPrno = replyRepository.save(reply).getPrno();

        return newPrno;

    }

   @Override
    public ProductReplyDTO read(Long pno) {
        
        Optional<ProductReply> result = replyRepository.findById(pno);
        
        ProductReply reply = result.orElseThrow();

        return modelMapper.map(reply, ProductReplyDTO.class);
    }

    @Override
    public void remove(Long pno) {
     
        Optional<ProductReply> result = replyRepository.findById(pno);
        

        ProductReply reply = result.orElseThrow();

        reply.changeText("해당 글은 삭제 되었습니다.");
        reply.changeFile(null);

        replyRepository.save(reply);
    }

    @Override
    public void modify(ProductReplyDTO ProductReplyDTO) {
        
        Optional<ProductReply> result = replyRepository.findById(ProductReplyDTO.getPrno());
        

        ProductReply reply = result.orElseThrow();

        reply.changeText(ProductReplyDTO.getReplyText());
        reply.changeFile(ProductReplyDTO.getReplyFile());

        replyRepository.save(reply);
    }


    
}
