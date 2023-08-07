package com.burgerplace.sales.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.burgerplace.sales.domain.SalesBoard;

// 검색기능을위한 interface 
// 이름 일치시켜야된다. Search --> SearchImpl
public interface SalesBoardSearch {

    Page<SalesBoard> search1 (String searchType, String searchKeyword, Pageable pageable);

}
