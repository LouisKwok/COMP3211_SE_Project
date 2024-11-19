package test;

import model.IncomeTaxSquare;
import model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IncomeTaxSquareTest {

    @Test
    void testIncomeTaxDeduction() {
        // Test for income tax deduction
        Player player = new Player("Charlie");
        player.updateMoney(5000); // Add extra money for testing
        IncomeTaxSquare taxSquare = new IncomeTaxSquare();

        taxSquare.action(player);
        assertEquals(4500, player.getMoney(), "Income tax should deduct 10% of player's money.");
    }
}
