package org.zerock.j2.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.j2.entity.Product;
import org.zerock.j2.repository.search.ProductSearch;


public interface ProductRepository extends JpaRepository<Product, Long>, ProductSearch {
    
    // 상세보기용을 위한 JPQL 작업
    @EntityGraph(attributePaths = "images") // elementCollection의 arrayList의 형식의 list의 oneTomany
    @Query("select p from Product p where p.delFlag = false and p.pno = :pno ")
    // 찾는다, product를, 조건은 delFlage가 flase이고, p.Pno의 값은 요청들어온 pno이어야 한다.
    Product selectOne(@Param("pno")Long pno);
    // 내가 selectOne메서드를 호출하면 

}
