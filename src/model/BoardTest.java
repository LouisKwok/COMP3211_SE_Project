package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BoardTest {

    @Test
    void testPlayerMovementUpdatesPosition() {
        // Initialize the board and a player
        Board board = new Board();
        Player player = new Player("TestPlayer");

        // Ensure the player starts at position 0
        assertEquals(0, player.getPosition(), "Player should start at position 0");

        // Simulate player rolling a 3
        int diceRoll = 3;
        player.move(diceRoll);

        // Check if the player's position updates correctly
        assertEquals(3, player.getPosition(), "Player position should update correctly after moving 3 spaces");

        // Simulate player crossing the board (e.g., roll enough to loop back to the start)
        int boardSize = board.getSquares().size();
        player.setPosition(boardSize - 2); // Position player near the end of the board
        player.move(4); // Move forward past the board size

        // Check if the player's position loops back correctly
        assertEquals(1, player.getPosition(), "Player position should loop back correctly after exceeding board size");
    }
}
