package burgerplace.board.bod.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import burgerplace.board.bod.entity.FreeReply;

public interface FreeReplyRepository extends JpaRepository<FreeReply, Integer> {
    
}
