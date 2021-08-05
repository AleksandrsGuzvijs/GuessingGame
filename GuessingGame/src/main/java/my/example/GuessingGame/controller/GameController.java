package my.example.GuessingGame.controller;

import my.example.GuessingGame.model.Game;
import my.example.GuessingGame.model.Round;
import my.example.GuessingGame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameService gameService;

    @PostMapping("/begin")
    public ResponseEntity<Game> createGame(@RequestBody Long playerId) {
        Game game = gameService.createNewGame(playerId);
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    @PostMapping("/guess/{game_id}")
    public Round makeGuess(@RequestBody String guess, @PathVariable("game_id") Long gameId) {
        Round newRound = gameService.newRound(guess, gameId);
        return gameService.makeGuess(newRound);
    }

    @GetMapping("/all")
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/find/{game_id}")
    public Game getGameById(@PathVariable("game_id") Long gameId) {
        return gameService.getGameById(gameId);
    }

    @GetMapping("/rounds/{game_id}")
    public List<Round> getRoundsForGame(@PathVariable("game_id") Long gameId) {
        return gameService.getAllRoundsByGameId(gameId);
    }
}
