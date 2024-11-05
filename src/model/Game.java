package model;

import java.util.List;

public class Game {
    private List<Player> players;
    private Board board;
    private int currentPlayerIndex;

    public Game(List<Player> players) {
        this.players = players;
        this.board = new Board();
        this.currentPlayerIndex = 0;
    }

    // New method to access the board
    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    public boolean isGameOver() {
        return players.size() == 1; // Adjust the game-over logic as needed
    }

    public void handlePlayerLanding(Player player) {
        Square currentSquare = board.getSquare(player.getPosition());
        currentSquare.landOn(player, this);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }
}
