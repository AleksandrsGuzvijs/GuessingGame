package my.example.GuessingGame.service;

import my.example.GuessingGame.model.Player;
import my.example.GuessingGame.repository.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepo playerRepo;

    public Player addPlayer(Player player) {
        return playerRepo.save(player);
    }

    public List<Player> getAllPlayers() {
        return playerRepo.findAll();
    }

    public Player findPlayerById(Long id) {
        return playerRepo.findPlayerById(id);
    }

}
