package com.burgerplace.bproduct.repositroy;

import com.burgerplace.bproduct.repositroy.search.FileBoardSearch;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.burgerplace.bproduct.entity.FileBoard;

public interface FileBoardRepository extends JpaRepository<FileBoard, Long>, FileBoardSearch {
    
    @EntityGraph(attributePaths = {"images"})
    @Query("select b from FileBoard b where b.bno = :bno")
    FileBoard selectOne(@Param("bno") Long bno);
}
