package my.example.GuessingGame.exception;

public class GameOverException extends RuntimeException{
    public GameOverException(String message) {
        super(message);
    }
}
