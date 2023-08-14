package org.zerock.j2.service;

import jakarta.transaction.Transactional;
import org.zerock.j2.dto.MemberCartDTO;
import org.zerock.j2.entity.MemberCart;

import java.util.List;

@Transactional
public interface MemberCartService {

    List<MemberCartDTO> addCart(MemberCartDTO memberCartDTO);

    List<MemberCartDTO> getCart(String email);


    default MemberCart dtoToEntity(MemberCartDTO dto) {

        // 매개변수로 dto가 들어오면 entity형식으로 change한다.
        // 그리고 해당 entity를 반환한다.

        MemberCart entity = MemberCart.builder()
                .cno(dto.getCno())
                .email(dto.getEmail())
                .pno(dto.getPno())
                .build();

        return entity;
    }

    default  MemberCartDTO entityToDTO(MemberCart entity){

        // entity 매개변수를 dto로 변환하여 반환한다.

        MemberCartDTO dto = MemberCartDTO.builder()
                .cno(entity.getCno())
                .email(entity.getEmail())
                .pno(entity.getPno())
                .build();

        return dto;
    }

}
