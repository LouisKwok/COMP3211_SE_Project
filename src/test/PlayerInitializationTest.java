package test;


import model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerInitializationTest {

    @Test
    void testPlayerInitialization() {
        // Verify player initialization
        Player player = new Player("Alice");

        assertEquals("Alice", player.getName(), "Player's name should be initialized correctly.");
        assertEquals(1500, player.getMoney(), "Player should start with HKD 1500.");
        assertEquals(0, player.getPosition(), "Player should start at position 0.");
        assertFalse(player.isInJail(), "Player should not start in jail.");
        assertEquals(0, player.getJailTurns(), "Player should have 0 jail turns at initialization.");
    }
}
