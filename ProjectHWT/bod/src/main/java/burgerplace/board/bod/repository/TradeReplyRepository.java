package burgerplace.board.bod.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import burgerplace.board.bod.entity.TradeReply;

public interface TradeReplyRepository extends JpaRepository<TradeReply, Integer> {
    
}
