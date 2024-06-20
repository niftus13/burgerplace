package com.burgerplace.main.memberPage.repository.search;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.burgerplace.main.common.pageDto.PageRequestDTO;
import com.burgerplace.main.common.pageDto.PageResponseDTO;
import com.burgerplace.main.memberPage.dto.MemberDTO;
import com.burgerplace.main.memberPage.entity.MemberEntity;
import com.burgerplace.main.memberPage.entity.QMemberEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MemberSearchImpl extends QuerydslRepositorySupport implements MemberSearch {

    public MemberSearchImpl() {
        super(MemberEntity.class);
    }

    @Override
    public Page<MemberEntity> search1(String searchType, String keyword, Pageable pageable) {

        QMemberEntity member = QMemberEntity.memberEntity;

        JPQLQuery<MemberEntity> query = from(member);

        if (keyword != null && searchType != null) {

            String[] searchArr = searchType.split("");
            // BooleanBuilder 생성
            BooleanBuilder searchBuilder = new BooleanBuilder();

            for (String type : searchArr) {

                switch (type) {
                    case "i" -> searchBuilder.or(member.id.contains(keyword));
                    case "n" -> searchBuilder.or(member.nickname.contains(keyword));
                }

            } // end for
              // for문 끝난후 where 로 searchBuilder 추가
            query.where(searchBuilder);
        }

        this.getQuerydsl().applyPagination(pageable, query);
        // list를 가져오는 방법
        List<MemberEntity> list = query.fetch();
        long count = query.fetchCount();

        log.info(list);
        log.info("count: " + count);
        // 동적쿼리까지 처리된 list
        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public PageResponseDTO<MemberDTO> searchDTORcnt(PageRequestDTO requestDTO) {

        Pageable pageable = makePageable(requestDTO);

        QMemberEntity member = QMemberEntity.memberEntity;

        // JPQL로 보드 관련 테이블 만드는데 member에서 만든다
        JPQLQuery<MemberEntity> query = from(member);

        String keyword = requestDTO.getKeyword();
        String searchType = requestDTO.getType();

        // 검색조건 추가
        if (keyword != null && searchType != null) {
            // tc => [t,c]
            String[] searchArr = searchType.split("");
            // BooleanBuilder 생성 ()
            BooleanBuilder searchBuilder = new BooleanBuilder();

            for (String type : searchArr) {

                switch (type) {
                    case "i" -> searchBuilder.or(member.id.contains(keyword));
                    case "n" -> searchBuilder.or(member.nickname.contains(keyword));
                }

            } // end for

            // for문 끝난후 where 로 searchBuilder 추가
            query.where(searchBuilder);
        }
        // paging 처리
        this.getQuerydsl().applyPagination(pageable, query);

        // group by
        query.groupBy(member);

        // 어제 했던 tuple 뽑는거 까진 똑같음
        // JPQL Query를 바로 MemberDTO로 추출하는 쿼리
        JPQLQuery<MemberDTO> listQuery = query.select(Projections.bean(
                MemberDTO.class,
                member.id,
                member.pw,
                member.nickname,
                member.admin
                ));

        // 쿼리를 List<MemberDTO>로 추출
        List<MemberDTO> list = listQuery.fetch();

        log.info("--------------------------");
        log.info(list);

        long totalCount = listQuery.fetchCount();

        return new PageResponseDTO<>(list, totalCount, requestDTO);

    }

}
