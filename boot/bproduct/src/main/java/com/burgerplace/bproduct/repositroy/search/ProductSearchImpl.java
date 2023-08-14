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
import com.burgerplace.bproduct.entity.QProductReply;
import com.burgerplace.bproduct.entity.QTag;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;



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

        int pageNum = pageRequestDTO.getPage() <= 0 ? 0 : pageRequestDTO.getPage() - 1;

        Pageable pageable = PageRequest.of(pageNum, pageRequestDTO.getSize(),
                Sort.by("pno"));

        this.getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<ProductListDTO> dtoQuery = query.select(
                Projections.bean(ProductListDTO.class,
                        product.pno,
                        product.pname,
                        product.brand,
                        product.price,
                        productImage.pfname));
        List<ProductListDTO> dtoList = dtoQuery.fetch();
        long totalCount = dtoQuery.fetchCount();

        return new PageResponseDTO<>(dtoList, totalCount, pageRequestDTO);
    }

    @Override
    public PageResponseDTO<ProductListDTO> listWithReply(PageRequestDTO pageRequestDTO) {

        QProduct product = QProduct.product;
        QProductImage productImage = QProductImage.productImage;
        QProductReply reply = QProductReply.productReply;

        JPQLQuery<Product> query = from(product);
        query.leftJoin(product.images, productImage);
        query.leftJoin(reply).on(reply.product.eq(product));

        query.where(productImage.ord.eq(0));
        query.where(product.delFlag.eq(Boolean.FALSE));

        int pageNum = pageRequestDTO.getPage() <= 0 ? 0 : pageRequestDTO.getPage() - 1;

        Pageable pageable = PageRequest.of(pageNum, pageRequestDTO.getSize(),
                Sort.by("pno").ascending());

        this.getQuerydsl().applyPagination(pageable, query);
        // 에러가 나오는데 grouping 되지않는애가 있기때문
        // 해결책 group함수를 통해서 얘를 그룹함수에 영향을 받게한다.
        // 또는 그룹되지않는 영역을 그룹화 한다.
        query.groupBy(product);

        JPQLQuery<ProductListDTO> dtoQuery = query.select(
                Projections.bean(ProductListDTO.class,
                        product.pno,
                        product.pname,
                        product.brand,
                        product.price,
                        productImage.pfname.min().as("pfname"),
                        reply.grade.avg().as("gradeAvg"),
                        reply.count().as("replyCnt"),
                        productImage.UUID.min().as("UUID")));

        List<ProductListDTO> dtoList = dtoQuery.fetch();
        long totalCount = dtoQuery.fetchCount();

        return new PageResponseDTO<>(dtoList, totalCount, pageRequestDTO);
    }

    @Override
    public PageResponseDTO<ProductListDTO> listSearchWithReply(PageRequestDTO pageRequestDTO) {

        QProduct product = QProduct.product;
        QProductImage productImage = QProductImage.productImage;
        QProductReply reply = QProductReply.productReply;

        JPQLQuery<Product> query = from(product);
        query.leftJoin(product.images, productImage);
        query.leftJoin(reply).on(reply.product.eq(product));

        query.where(productImage.ord.eq(0));
        query.where(product.delFlag.eq(Boolean.FALSE));

        int pageNum = pageRequestDTO.getPage() <= 0 ? 0 : pageRequestDTO.getPage() - 1;

        Pageable pageable = PageRequest.of(pageNum, pageRequestDTO.getSize(),
                Sort.by("pno").ascending());

        String keyword = pageRequestDTO.getKeyword();
        String searchType = pageRequestDTO.getType();
        if (keyword != null && searchType != null) {

            String[] searchArr = searchType.split("");

            BooleanBuilder searchBuilder = new BooleanBuilder();

            for (String type : searchArr) {

                switch (type) {
                    case "b" -> searchBuilder.or(product.brand.contains(keyword));
                    case "t" -> searchBuilder.or(product.pname.contains(keyword));
                    // case "h" -> searchBuilder.or(tag.tagName.contains(keyword));
                }
            } // end for
            query.where(searchBuilder);
        }
        this.getQuerydsl().applyPagination(pageable, query);
        query.groupBy(product);

        JPQLQuery<ProductListDTO> dtoQuery = query.select(
                Projections.bean(ProductListDTO.class,
                        product.pno,
                        product.pname,
                        product.brand,
                        product.price,
                        productImage.pfname.min().as("pfname"),
                        reply.grade.avg().as("gradeAvg"),
                        reply.count().as("replyCnt"),
                        productImage.UUID.min().as("UUID")));
        List<ProductListDTO> dtoList = dtoQuery.fetch();
        long totalCount = dtoQuery.fetchCount();

        return new PageResponseDTO<>(dtoList, totalCount, pageRequestDTO);

    }

    @Override
    public PageResponseDTO<ProductListDTO> listSearchWithReplyAndHashTag(PageRequestDTO pageRequestDTO) {
        QProduct product = QProduct.product;
        QProductImage productImage = QProductImage.productImage;
        QProductReply reply = QProductReply.productReply;
        QTag tag = QTag.tag;


        JPQLQuery<Product> query = from(product);
        query.leftJoin(product.images, productImage);
        query.leftJoin(reply).on(reply.product.eq(product));
        query.leftJoin(product.hashTags, tag);

        query.where(productImage.ord.eq(0));
        query.where(product.delFlag.eq(Boolean.FALSE));

        int pageNum = pageRequestDTO.getPage() <= 0 ? 0 : pageRequestDTO.getPage() - 1;

        Pageable pageable = PageRequest.of(pageNum, pageRequestDTO.getSize(),
                Sort.by("pno").ascending());

        String keyword = pageRequestDTO.getKeyword();
        String searchType = pageRequestDTO.getType();
        if (keyword != null && searchType != null) {

            String[] searchArr = searchType.split("");

            BooleanBuilder searchBuilder = new BooleanBuilder();

            for (String type : searchArr) {

                switch (type) {
                    case "b" -> searchBuilder.or(product.brand.contains(keyword));
                    case "t" -> searchBuilder.or(product.pname.contains(keyword));
                    case "h" -> searchBuilder.or(product.hashTags.any().tagName.contains(keyword));
                }
            } // end for
            query.where(searchBuilder);
        }
        this.getQuerydsl().applyPagination(pageable, query);
        query.groupBy(product);

        

        JPQLQuery<ProductListDTO> dtoQuery = query.select(
                Projections.bean(ProductListDTO.class,
                        product.pno,
                        product.pname,
                        product.brand,
                        product.price,
                        productImage.pfname.min().as("pfname"),
                        Expressions.list(tag.tagName),
                        reply.grade.avg().as("gradeAvg"),
                        reply.count().as("replyCnt"),
                        productImage.UUID.min().as("UUID")));
        List<ProductListDTO> dtoList = dtoQuery.fetch();
        long totalCount = dtoQuery.fetchCount();

        return new PageResponseDTO<>(dtoList, totalCount, pageRequestDTO);
    }

}
