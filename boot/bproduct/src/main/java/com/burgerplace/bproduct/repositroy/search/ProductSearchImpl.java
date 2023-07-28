package com.burgerplace.bproduct.repositroy.search;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import com.burgerplace.bproduct.dto.PageRequestDTO;
import com.burgerplace.bproduct.dto.PageResponseDTO;
import com.burgerplace.bproduct.dto.ProductListDTO;
import com.burgerplace.bproduct.entity.Product;
import com.burgerplace.bproduct.entity.QProduct;
import com.burgerplace.bproduct.entity.QProductImage;
import com.burgerplace.bproduct.entity.QProductReview;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ProductSearchImpl extends QuerydslRepositorySupport implements ProductSearch {

    public ProductSearchImpl() {
        super(Product.class);
    }

    @Override
    public PageResponseDTO<ProductListDTO> list(PageRequestDTO pageRequestDTO) {

            QProduct product = QProduct.product;
            QProductImage productImage = QProductImage.productImage;

            JPQLQuery<Product> query = from(product);
            query.leftJoin(product.images, productImage);

            query.where(productImage.ord.eq(0));

            int pageNum = pageRequestDTO.getPage() <= 0 ? 0: pageRequestDTO.getPage()-1;

            Pageable pageable =
             PageRequest.of(pageNum,pageRequestDTO.getSize(),
             Sort.by("pno").descending());        

             this.getQuerydsl().applyPagination(pageable, query);

            JPQLQuery<ProductListDTO>dtoQuery = 
            query.select(
                Projections.bean(ProductListDTO.class,
                 product.pno, product.pname,
                 product.price,
                 productImage.fname)
            );
            List<ProductListDTO> dtoList = dtoQuery.fetch();
            long totalCount = dtoQuery.fetchCount();

            return new PageResponseDTO<>(dtoList, totalCount, pageRequestDTO);
    }

    @Override
    public PageResponseDTO<ProductListDTO> listWithReview(PageRequestDTO pageRequestDTO) {

        QProduct product = QProduct.product;
        QProductImage productImage = QProductImage.productImage;
        QProductReview review = QProductReview.productReview;

        JPQLQuery<Product> query = from(product);
        query.leftJoin(product.images, productImage);
        query.leftJoin(review).on(review.product.eq(product));

        query.where(productImage.ord.eq(0));
        query.where(product.delFlag.eq(Boolean.FALSE));

        int pageNum = pageRequestDTO.getPage() <= 0 ? 0 : pageRequestDTO.getPage() - 1;

        Pageable pageable = PageRequest.of(pageNum, pageRequestDTO.getSize(),
                Sort.by("pno").descending());

        this.getQuerydsl().applyPagination(pageable, query);
        // 에러가 나오는데 grouping 되지않는애가 있기때문
        // 해결책 group함수를 통해서 얘를 그룹함수에 영향을 받게한다.
        // 또는 그룹되지않는 영역을 그룹화 한다.
        query.groupBy(product);

        JPQLQuery<ProductListDTO> dtoQuery = query.select(
                Projections.bean(ProductListDTO.class,
                        product.pno, product.pname,
                        product.price,
                        productImage.fname.min().as("fname"),
                        review.score.avg().as("reviewAvg"),
                        review.count().as("reviewCnt")));
        List<ProductListDTO> dtoList = dtoQuery.fetch();
        long totalCount = dtoQuery.fetchCount();

        return new PageResponseDTO<>(dtoList, totalCount, pageRequestDTO);
    }
    

}
