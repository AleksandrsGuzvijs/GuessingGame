package my.example.GuessingGame.model;

import my.example.GuessingGame.enums.GameStatus;

import javax.persistence.*;

@Entity
@Table(name="round")
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false, updatable = false)
    private Long id;

    @Column(name="guess")
    private String guess;

    @Column(name="result")
    private String result;

    @Column(name="gameId")
    private Long gameId;

    private GameStatus gameStatus;

    private int roundNumInGame;

    public Round() {
    }

    public Round(String guess, Long gameId) {
        this.guess = guess;
        this.gameId = gameId;
        this.gameStatus = GameStatus.IN_PROGRESS;
    }

    public Round(Long id, String guess, String result, Long gameId) {
        this.id = id;
        this.guess = guess;
        this.result = result;
        this.gameId = gameId;
    }

    public Long getRoundId() {
        return id;
    }

    public void setRoundId(Long id) {
        this.id = id;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getRoundGameId() {
        return gameId;
    }

    public void setRoundGameId(Long gameId) {
        this.gameId = gameId;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getRoundNumInGame() {
        return roundNumInGame;
    }

    public void setRoundNumInGame(int roundNumInGame) {
        this.roundNumInGame = roundNumInGame;
    }

    @Override
    public String toString() {
        return "Round{" +
                "id=" + id +
                ", guess='" + guess + '\'' +
                ", result='" + result + '\'' +
                ", gameId='" + gameId + '\'' +
                ", roundNumInGame='" + roundNumInGame + '\'' +
                '}';
    }
}
