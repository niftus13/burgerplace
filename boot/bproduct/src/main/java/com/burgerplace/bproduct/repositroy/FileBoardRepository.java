package com.burgerplace.bproduct.repositroy;

import com.burgerplace.bproduct.repositroy.search.FileBoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;

import com.burgerplace.bproduct.entity.FileBoard;

public interface FileBoardRepository extends JpaRepository<FileBoard, Long>, FileBoardSearch {
    
}
