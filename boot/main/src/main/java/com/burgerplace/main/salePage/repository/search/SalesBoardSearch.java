package com.burgerplace.main.salePage.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.burgerplace.main.salePage.domain.SalesEntity;



// 검색기능을위한 interface 
// 이름 일치시켜야된다. Search --> SearchImpl
public interface SalesBoardSearch {

    Page<SalesEntity> search1 (String searchType, String searchKeyword, Pageable pageable);

}
