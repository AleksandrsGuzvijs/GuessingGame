package my.example.GuessingGame.repository;

import my.example.GuessingGame.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Long> {
    Player findPlayerById(Long playerId);
}
