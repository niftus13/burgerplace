package com.burgerplace.sales.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.burgerplace.sales.domain.SalesBoard;

import jakarta.transaction.Transactional;

@Transactional
public interface SalesBoardService {

    Page<SalesBoard> search1 (String searchType, String searchKeyword, Pageable pageable);

    Page<Object[]> searchWithRcnt(String searchType, String searchKeyword, Pageable pageable);

}