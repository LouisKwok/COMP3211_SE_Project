package test;

import model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerBankruptcyTest {

    @Test
    void testPlayerBankruptcy() {
        // Test for player bankruptcy
        Player player = new Player("Edward");
        player.updateMoney(-2000); // Reduce money to a negative value

        assertTrue(player.getMoney() < 0, "Player should be bankrupt when money is negative.");
    }
}
