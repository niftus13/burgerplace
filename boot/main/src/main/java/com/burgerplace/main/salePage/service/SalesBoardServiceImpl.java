package com.burgerplace.main.salePage.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.burgerplace.main.salePage.domain.SalesEntity;

public class SalesBoardServiceImpl implements SalesBoardService {

    @Override
    public Page<SalesEntity> search1(String searchType, String searchKeyword, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search1'");
    }

    @Override
    public Page<Object[]> searchWithRcnt(String searchType, String searchKeyword, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchWithRcnt'");
    }
    
}
