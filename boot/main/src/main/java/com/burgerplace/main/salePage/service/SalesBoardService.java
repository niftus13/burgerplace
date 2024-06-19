package com.burgerplace.main.salePage.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.burgerplace.main.salePage.domain.SalesEntity;

import jakarta.transaction.Transactional;

@Transactional
public interface SalesBoardService {

    Page<SalesEntity> search1 (String searchType, String searchKeyword, Pageable pageable);

    Page<Object[]> searchWithRcnt(String searchType, String searchKeyword, Pageable pageable);

}