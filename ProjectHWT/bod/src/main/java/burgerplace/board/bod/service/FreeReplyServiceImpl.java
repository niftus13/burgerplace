package burgerplace.board.bod.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import burgerplace.board.bod.dto.FreeReplyDTO;
import burgerplace.board.bod.dto.PageResponseDTO;
import burgerplace.board.bod.dto.ReplyPageRequestDTO;
import burgerplace.board.bod.repository.FreeReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class FreeReplyServiceImpl implements FreeReplyService {
    
    private final FreeReplyRepository replyRepository;
    private final ModelMapper modelMapper;


    @Override
    public PageResponseDTO<FreeReplyDTO> list(ReplyPageRequestDTO requestDTO) {
        
        boolean last = requestDTO.isLast();

        int pageNum = requestDTO.getPage();

        if (last) {
            Long totalCount = replyRepository.getCountBoard(requestDTO.getFBno());
            pageNum = (int) (Math.ceil(totalCount / (double) requestDTO.getSize()));
            pageNum = pageNum <= 0 ? 1 : pageNum;
        }


        return null;
    }

















    @Override
    public Long register(FreeReplyDTO replyDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }
    @Override
    public FreeReplyDTO read(Long rno) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }
    @Override
    public void remove(Long rno) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }
    @Override
    public void modify(FreeReplyDTO replyDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modify'");
    }

}
