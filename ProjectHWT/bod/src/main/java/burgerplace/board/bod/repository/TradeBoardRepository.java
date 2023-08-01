package burgerplace.board.bod.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import burgerplace.board.bod.entity.TradeBoard;

public interface TradeBoardRepository extends JpaRepository<TradeBoard, Integer>{
    
}
