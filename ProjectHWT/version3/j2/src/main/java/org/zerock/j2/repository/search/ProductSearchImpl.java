package org.zerock.j2.repository.search;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.j2.dto.PageRequestDTO;
import org.zerock.j2.dto.PageResponseDTO;
import org.zerock.j2.dto.ProductListDTO;
import org.zerock.j2.entity.Product;
import org.zerock.j2.entity.QProduct;
import org.zerock.j2.entity.QProductImage;
import org.zerock.j2.entity.QProductReview;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ProductSearchImpl extends QuerydslRepositorySupport implements ProductSearch {

    public ProductSearchImpl() {
        super(Product.class);
        // 부모 클래스의 생성자를 가져온다.
    }

    @Override
    public PageResponseDTO<ProductListDTO> list(PageRequestDTO pageRequestDTO) {

        QProduct product = QProduct.product;
        // q도메인의  product값을 기존의 product Entity에 넣는다.
        QProductImage productImage = QProductImage.productImage;
        // q도메인의  productImage값을 기존의 productImage Entity에 넣는다.


        JPQLQuery<Product> query = from(product);
        // from(product) => select * from product

        query.leftJoin(product.images, productImage);
        // 일대 다 관계의 images와 productImage를 조인한다.

        query.where(productImage.ord.eq(0));
        // 조건은 => productImage.ord가 0인 것만 찾아라.


        int pageNum = pageRequestDTO.getPage() <= 0 ? 0 : pageRequestDTO.getPage() - 1;
        // 들어오는 pageNum이 0이면 0을 반환하고 그렇지 않다면 들어오는 페이지num에서 -1를 한것을
        // 넣어준다.


        Pageable pageable = PageRequest.of(pageNum, pageRequestDTO.getSize(),
                Sort.by("pno").descending());
                // page, size, pno, desc

        this.getQuerydsl().applyPagination(pageable, query);
        // querydsl로 pageable 처리하는 방식
        // querydsl를 통해서 pageable를 처리하는 방식임으로 그냥 외우자


        JPQLQuery<ProductListDTO> dtoQuery = query.select(
                Projections.bean(ProductListDTO.class,
                        product.pno, product.pname,
                        product.price,
                        productImage.fname));
                        // 조인으로 얻은 테이터들 중 원하는 데이터만 뽑아내서 저장한다.
                        // 이때 dtoQuery는 엔티티이다.

        List<ProductListDTO> dtoList = dtoQuery.fetch();
        // 리스트 형식으로 쿼리를 실행한 결과를 반환한다.

        long totalCount = dtoQuery.fetchCount();
        // 위의 쿼리 결과의 개수를 반환한다.

        log.info(dtoList+" dtoList hwt");
        log.info(totalCount + " totalCount hwt");
        log.info(pageRequestDTO + " pageRequestDTO hwt");

        return new PageResponseDTO<>(dtoList, totalCount, pageRequestDTO);
    
    }

    @Override
    public PageResponseDTO<ProductListDTO> listWithReview(PageRequestDTO pageRequestDTO) {

        QProduct product = QProduct.product;
        QProductImage productImage = QProductImage.productImage;
        QProductReview review = QProductReview.productReview;
        // q도메인을 생성한다.

        JPQLQuery<Product> query = from(product);
        // select * from product

        query.leftJoin(product.images, productImage);
        // product의 images의 fk left join priductImage 

        query.leftJoin(review).on(review.product.eq(product));
        // review.product => product.pno는 eq 같아야 한다. product와?

        query.where(productImage.ord.eq(0));
        // where 조건은 productImage.ord는 0과 같아야 한다.
        query.where(product.delFlag.eq(Boolean.FALSE));
        // product.delFlag는 false와 같아야 한다.

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
                        review.count().as("reviewCnt")
                        )); // 조인된 데이터들 중 원하는 데이터만 가져와서 변수에 집어넣는다.

        List<ProductListDTO> dtoList = dtoQuery.fetch();
        long totalCount = dtoQuery.fetchCount();

        return new PageResponseDTO<>(dtoList, totalCount, pageRequestDTO);
    }

}
