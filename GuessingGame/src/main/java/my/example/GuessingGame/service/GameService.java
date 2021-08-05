package my.example.GuessingGame.service;

import my.example.GuessingGame.exception.GameOverException;
import my.example.GuessingGame.model.Game;
import my.example.GuessingGame.model.Player;
import my.example.GuessingGame.model.Round;
import my.example.GuessingGame.repository.GameRepo;
import my.example.GuessingGame.repository.PlayerRepo;
import my.example.GuessingGame.repository.RoundRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

import static my.example.GuessingGame.enums.GameStatus.*;

@Service
public class GameService {

    @Autowired
    private GameRepo gameRepo;

    @Autowired
    private RoundRepo roundRepo;

    @Autowired
    private PlayerRepo playerRepo;

    public Game createNewGame(Long playerId) {
        Game game = new Game(generateRandom4DigitNumber(),playerId);

        Player player = playerRepo.findPlayerById(playerId);
        player.setGamesPlayed(player.getGamesPlayed()+1);
        player = playerRepo.save(player);

        game = gameRepo.save(game);
        return game;
    }

    private String generateRandom4DigitNumber() {
        Random rnd = new Random();
        int digit1 = rnd.nextInt(10);

        int digit2 = rnd.nextInt(10);
        while (digit2 == digit1) {
            digit2 = rnd.nextInt(10);
        }

        int digit3 = rnd.nextInt(10);
        while (digit3 == digit2 || digit3 == digit1) {
            digit3 = rnd.nextInt(10);
        }

        int digit4 = rnd.nextInt(10);
        while (digit4 == digit3 || digit4 == digit2 || digit4 == digit1) {
            digit4 = rnd.nextInt(10);
        }

        String number = String.valueOf(digit1) + String.valueOf(digit2) + String.valueOf(digit3) + String.valueOf(digit4);
        return number;
    }

    public Round makeGuess(Round round) {
        Game game = gameRepo.getGameById(round.getRoundGameId());
        if(game.getGameStatus().equals(IN_PROGRESS)) {
            String secretNumber = game.getSecretNumber();
            String guess = round.getGuess();
            String result = compareNumbers(guess, secretNumber);
            round.setResult(result);

            game.setNumberOfRounds(game.getNumberOfRounds()+1);
            round.setRoundNumInGame(game.getNumberOfRounds());

            if (game.getNumberOfRounds() == 8 && !guess.equals(secretNumber)) {
                game.setGameStatus(LOST);
                gameRepo.save(game);
                round.setGameStatus(LOST);

                return roundRepo.save(round);
            } else if (guess.equals(secretNumber)) {
                game.setGameStatus(WON);
                gameRepo.save(game);
                round.setGameStatus(WON);

                Player player = playerRepo.findPlayerById(game.getGamePlayerId());
                player.setGamesWon(player.getGamesWon()+1);

                return roundRepo.save(round);
            }
            return roundRepo.save(round);
        } else {
            throw new GameOverException("Game by ID:"+game.getGameId()+", is already "+game.getGameStatus());
        }
    }

    private String compareNumbers(String guess, String secretNumber) {
        char[] guessChars = guess.toCharArray();
        char[] answerChars = secretNumber.toCharArray();
        int place = 0;
        int match = 0;

        for (int i = 0; i < guessChars.length; i++) {
            if (secretNumber.indexOf(guessChars[i]) > -1) {
                if (guessChars[i] == answerChars[i]) {
                    place++;
                } else {
                    match++;
                }
            }
        }
        String result = "m:" + match + ":p:" + place;
        return result;
    }

    public Game getGameById(Long gameId) {
        Game game = gameRepo.getGameById(gameId);
        if (game.getGameStatus().equals(IN_PROGRESS)) {
            game.setSecretNumber("****");
        }
        return game;
    }

    public List<Game> getAllGames() {
        List<Game> games = gameRepo.findAll();
        for (Game game : games) {
            if (game.getGameStatus().equals(IN_PROGRESS)) {
                game.setSecretNumber("****");
            }
        }
        return games;
    }

    public List<Round> getAllRoundsByGameId(Long gameId) {
        List<Round> allRounds = roundRepo.findAll();
        List<Round> roundsByGameId = null;
        for (Round round : allRounds) {
            if(round.getRoundGameId().equals(gameId)){
                roundsByGameId.add(round);
            }
        }
        return roundsByGameId;
    }

    public Round newRound(String guess, Long gameId){
        Round round = new Round(guess, gameId);
        return roundRepo.save(round);
    }
}
