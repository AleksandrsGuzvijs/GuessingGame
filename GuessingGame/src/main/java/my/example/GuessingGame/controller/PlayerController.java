package my.example.GuessingGame.controller;

import my.example.GuessingGame.model.Player;
import my.example.GuessingGame.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @PostMapping("/add")
    public ResponseEntity<Player> createAccount(@RequestBody Player Player) {
        Player newPlayer = playerService.addPlayer(Player);
        return new ResponseEntity<>(newPlayer, HttpStatus.CREATED);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Player>> getPlayers() {
        List<Player> players = playerService.getAllPlayers();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/find/{game_id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable("player_id") Long playerId) {
            Player player = playerService.findPlayerById(playerId);
            return new ResponseEntity<>(player, HttpStatus.OK);
    }

}