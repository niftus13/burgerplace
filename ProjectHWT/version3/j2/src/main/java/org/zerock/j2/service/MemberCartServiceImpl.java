package org.zerock.j2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.j2.dto.MemberCartDTO;
import org.zerock.j2.entity.MemberCart;
import org.zerock.j2.repository.MemberCartRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberCartServiceImpl implements MemberCartService{

    private final MemberCartRepository cartRepository;


    @Override
    public List<MemberCartDTO> addCart(MemberCartDTO memberCartDTO) {

        MemberCart cart = dtoToEntity(memberCartDTO);

        cartRepository.save(cart);
        // dto가 entity로 변경되고 그 값을 저장한다.

        List<MemberCart> cartList =cartRepository.selectCart(memberCartDTO.getEmail());
        // 들어온 매개변수 중에서 email만 selectCart메서드로 처리한 후에 저장하고 List<memberCart> cartList의
        // 이름으로 저장한다.

        return cartList.stream().map(entity -> entityToDTO(entity)).collect(Collectors.toList());
        // 그리고 cartList 컬렉션을 entityToDTO의 형식으로 change하여 list로 만들고 반환한다.
    }



    @Override
    public List<MemberCartDTO> getCart(String email) {

        List<MemberCart> cartList =cartRepository.selectCart(email);

        return cartList.stream().map(entity -> entityToDTO(entity)).collect(Collectors.toList());
    }
}
