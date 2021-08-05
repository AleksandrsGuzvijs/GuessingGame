package my.example.GuessingGame.model;
import my.example.GuessingGame.enums.GameStatus;

import javax.persistence.*;

import static my.example.GuessingGame.enums.GameStatus.IN_PROGRESS;

@Entity
@Table(name="game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false, updatable = false)
    private Long id;
    private String secretNumber;
    private GameStatus gameStatus;
    private int numberOfRounds;
    private Long playerId;

    public Game() {}

    public Game(String secretNumber, Long playerId) {
        this.secretNumber = secretNumber;
        this.gameStatus = IN_PROGRESS;
        this.playerId = playerId;
        this.numberOfRounds = 0;
    }

    public Long getGameId() {
        return id;
    }

    public void setGameId(Long gameId) {
        this.id = id;
    }

    public String getSecretNumber() {
        return secretNumber;
    }

    public void setSecretNumber(String secretNumber) {
        this.secretNumber = secretNumber;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Long getGamePlayerId() {
        return playerId;
    }

    public void setGamePlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", secretNumber='" + secretNumber + '\'' +
                ", gameStatus='" + gameStatus + '\'' +
                ", numberOfRounds='" + numberOfRounds + '\'' +
                ", playerId='" + playerId + '\'' +
                '}';
    }
}
