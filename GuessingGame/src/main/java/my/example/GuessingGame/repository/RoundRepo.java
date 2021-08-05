package my.example.GuessingGame.repository;

import my.example.GuessingGame.model.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepo extends JpaRepository<Round,Long> {
    Round getRoundById(Long roundId);
}
