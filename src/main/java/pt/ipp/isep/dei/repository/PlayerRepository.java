package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerRepository {
    private List<Player> players;
    private Player currentPlayer;

    public PlayerRepository() {
        this.players = new ArrayList<>();
        createDefaultPlayer();

    }

    private void createDefaultPlayer() {
        Player defaultPlayer = new Player("Default Player", 1000000);
        players.add(defaultPlayer);
        currentPlayer = defaultPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean setCurrentPlayer(Player player) {
        if (this.currentPlayer == null) {
            return false;
        }

        currentPlayer = player;
        return true;
    }

    public boolean addPlayer(Player player) {
        if (player == null) {
            return false;
        }

        for (Player p : players) {
            if (p.equals(player)) {
                return false;
            }
        }

        return players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
