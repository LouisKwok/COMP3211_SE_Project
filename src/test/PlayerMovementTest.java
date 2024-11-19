package test;

import model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerMovementTest {

    @Test
    void testPlayerMovementAcrossBoard() {
        // Test player movement and wrapping around the board
        Player player = new Player("Grace");

        player.move(19); // Move near the end of the board
        assertEquals(19, player.getPosition(), "Player should be at position 19.");

        player.move(2); // Move beyond the board
        assertEquals(1, player.getPosition(), "Player should wrap around to position 1.");
    }
}
