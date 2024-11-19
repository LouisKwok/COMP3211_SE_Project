package test;

import model.ChanceSquare;
import model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChanceSquareTest {

    @Test
    void testLandingOnChanceSquare() {
        // Test for random chance events
        Player player = new Player("Diana");
        ChanceSquare chanceSquare = new ChanceSquare();

        // Execute the action multiple times to validate both outcomes (gain/loss)
        for (int i = 0; i < 10; i++) {
            chanceSquare.action(player);
            assertTrue(player.getMoney() >= 0, "Player's money should not go below 0 due to chance.");
        }
    }
}
