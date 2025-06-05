package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository for managing players in the system.
 *
 * <p>This class provides functionalities to manage a list of players,
 * set a current active player, and add new ones while avoiding duplicates.
 * A default player ("Default Player") with a score of 1,000,000 is created
 * during initialization.</p>
 */
public class PlayerRepository {
    private List<Player> players;
    private Player currentPlayer;

    /**
     * Initializes the repository with an empty player list and a default player.
     */
    public PlayerRepository() {
        this.players = new ArrayList<>();
        createDefaultPlayer();

    }

    /**
     * Creates a default player and sets it as the current active player.
     */
    private void createDefaultPlayer() {
        Player defaultPlayer = new Player("Default Player", 200001);
        players.add(defaultPlayer);
        currentPlayer = defaultPlayer;
    }

    /**
     * Returns the current active player.
     *
     * @return the currently active player.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Updates the current active player.
     *
     * @param player the new active player.
     * @return {@code true} if the player was successfully set, {@code false} otherwise.
     */
    public boolean setCurrentPlayer(Player player) {
        if (this.currentPlayer == null) {
            return false;
        }

        currentPlayer = player;
        return true;
    }

    /**
     * Adds a new player if it's not null or already exists in the repository.
     *
     * @param player the player to add.
     * @return {@code true} if added successfully, {@code false} otherwise.
     */
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

    /**
     * Gets the list of players in the repository.
     *
     * @return the list of all players.
     */
    public List<Player> getPlayers() {
        return players;
    }
}
