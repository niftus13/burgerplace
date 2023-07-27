package board.free.project1.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import board.free.project1.dto.PageResponseDTO;
import board.free.project1.dto.ReplyDTO;
import board.free.project1.dto.ReplyPageRequestDTO;
import board.free.project1.entity.Reply;
import board.free.project1.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper;

    @Override
    public PageResponseDTO<ReplyDTO> list(ReplyPageRequestDTO requestDTO) {

        boolean last = requestDTO.isLast();

        int pageNum = requestDTO.getPage();

        if (last) {
            long totalCount = replyRepository.getCountBoard(requestDTO.getBno());

            pageNum = (int) (Math.ceil(totalCount / (double) requestDTO.getSize()));
            pageNum = pageNum <= 0 ? 1 : pageNum;
        }

        Pageable pageable = PageRequest.of(pageNum - 1, requestDTO.getSize(), Sort.by("rno").ascending());

        Page<Reply> result = replyRepository.listBoard((requestDTO.getBno()), pageable);

        log.info("--------------------------------------------");

        long totalReplyCount = result.getTotalElements();

        List<ReplyDTO> dtoList = result.get()
                .map(entity -> modelMapper.map(entity, ReplyDTO.class))
                .collect(Collectors.toList());

        PageResponseDTO<ReplyDTO> responseDTO = new PageResponseDTO<>(dtoList, totalReplyCount, requestDTO);
        responseDTO.setPage(pageNum);

        return responseDTO;

    }

    @Override
    public Long register(ReplyDTO replyDTO) {

        Reply reply = modelMapper.map(replyDTO, Reply.class);
        // 모델매퍼를 통해서 dto로 들어온 매개변수를 엔티티로 매칭시킨다.

        log.info("reply....");
        
        log.info(reply);
        // entity 속성으로 log가 나온다.

        Long newRno = replyRepository.save(reply).getRno();
        // 레파지토리에 모델매퍼로 매칭된 엔티티 객체를 저장하고 오토incre를 반환한다.
        log.info(newRno);
        log.info("내가 알고 싶은 값");


        return newRno; // 오토 인크리 반환
    }

    @Override
    public ReplyDTO read(Long rno) {

        Optional<Reply> result = replyRepository.findById(rno);
        // null처리 쉽게 하려고 Optional를 사용한다. 매개변수로 들어온 rno를 통해 해당 로우를 가져와서 result에 넣는다.

        Reply reply = result.orElseThrow();
        // 예외 발생하면 처리해라

        return modelMapper.map(reply, ReplyDTO.class);
        // 엔티티 객체를 dto 객체로 변환한 것을 반환한다.
    }

    @Override
    public void remove(Long rno) {

        Optional<Reply> result = replyRepository.findById(rno);
        // 매개변수로 들어온 rno를 통해 row를 가져온다. null 처리 => optional

        Reply reply = result.orElseThrow();
        // 예외 처리

        reply.changeText("해당 글은 삭제 되었습니다.");
        // setter 메서드라 생각하면 된다. reply 엔티티 객체의 text를 변환한다.

        reply.changeFile(null);
        // null 처리한다.

        replyRepository.save(reply);
        // 그 값을 db에 저장한다.
    }

    @Override
    public void modify(ReplyDTO replyDTO) {

        Optional<Reply> result = replyRepository.findById(replyDTO.getRno());
        // DTO가 매개변수로 들어온다. dto의 getRno를 가져온다. 등록되어 있으니까 rno 있다.

        Reply reply = result.orElseThrow();
        // 예외 처리

        reply.changeText(replyDTO.getReplyText());
        // replyDTO, 화면단에서 적은거 가져온다. 그리고 setter로 바꿔준다.

        reply.changeFile(replyDTO.getReplyFile());
        // replyDTO, 화면단에서 적은거 가져온다. 그리고 setter로 바꿔준다.


        replyRepository.save(reply);
        // 그 값을 저장한다. 반환값은 없다.
    }

}
