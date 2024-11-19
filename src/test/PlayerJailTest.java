package test;

import model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerJailTest {

    @Test
    void testJailMechanics() {
        // Test jail mechanics
        Player player = new Player("Fiona");

        player.setInJail(true);
        assertTrue(player.isInJail(), "Player should be in jail.");
        assertEquals(3, player.getJailTurns(), "Player should start with 3 jail turns.");

        player.decreaseJailTurn();
        assertEquals(2, player.getJailTurns(), "Jail turns should decrease by 1.");
        player.setInJail(false);
        assertFalse(player.isInJail(), "Player should be out of jail.");
    }
}
