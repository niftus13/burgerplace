package burgerplace.board.bod.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import burgerplace.board.bod.entity.FreeBoard;
import burgerplace.board.bod.repository.search.FreeBoardSearch;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Integer>, FreeBoardSearch {
    
    


}
