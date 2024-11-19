package test;

import model.IncomeTaxSquare;
import model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IncomeTaxSquareTest {

    @Test
    void testIncomeTaxDeduction() {
        // Arrange
        Player player = new Player("Charlie");
        player.updateMoney(5000); // Set player's money to 6500 total
        IncomeTaxSquare taxSquare = new IncomeTaxSquare();

        // Act
        taxSquare.action(player);

        // Assert
        assertEquals(5850, player.getMoney(), "Income tax should deduct 10% of player's money.");
    }

}
