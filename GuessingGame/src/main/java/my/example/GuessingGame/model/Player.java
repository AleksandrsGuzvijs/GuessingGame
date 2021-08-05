package my.example.GuessingGame.model;
import javax.persistence.*;

@Entity
@Table(name="player")
public class Player{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false, updatable = false)
    private Long id;
    private String name;
    private int gamesPlayed;
    private int gamesWon;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
        this.gamesPlayed = 0;
        this.gamesWon = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public void addGamesPlayedPlusOne() {
        gamesPlayed++;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public void addGamesWonPlusOne() {
        gamesWon++;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gamesPlayed='" + gamesPlayed + '\'' +
                ", gamesWon='" + gamesWon + '\'' +
                '}';
    }
}