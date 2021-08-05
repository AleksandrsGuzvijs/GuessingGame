package my.example.GuessingGame.repository;

import my.example.GuessingGame.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepo extends JpaRepository<Game, Long> {
    Game getGameById(Long gameId);
}
