package model;

import controller.GameController;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

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

    @Test
    void testPassingGoIncreasesMoney() {
        // Test for money increment after passing "Go"
        Player player = new Player("Bob");
        GoSquare goSquare = new GoSquare();

        goSquare.action(player);
        assertEquals(3000, player.getMoney(), "Player should receive HKD 1500 after passing Go.");
    }

    @Test
    void testIncomeTaxDeduction() {
        // Test for income tax deduction
        Player player = new Player("Charlie");
        player.updateMoney(5000); // Add extra money for testing
        IncomeTaxSquare taxSquare = new IncomeTaxSquare();

        taxSquare.action(player);
        assertEquals(4500, player.getMoney(), "Income tax should deduct 10% of player's money.");
    }

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

    @Test
    void testPlayerBankruptcy() {
        // Test for player bankruptcy
        Player player = new Player("Edward");
        player.updateMoney(-2000); // Reduce money to a negative value

        assertTrue(player.getMoney() < 0, "Player should be bankrupt when money is negative.");
    }

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

    @Test
    void testPlayerMovementAcrossBoard() {
        // Test player movement and wrapping around the board
        Player player = new Player("Grace");

        player.move(19); // Move near the end of the board
        assertEquals(19, player.getPosition(), "Player should be at position 19.");

        player.move(2); // Move beyond the board
        assertEquals(1, player.getPosition(), "Player should wrap around to position 1.");
    }

    @Test
    void testBuyingAndPayingRent() {
        // Test property ownership and rent payments
        Player owner = new Player("Harry");
        Player visitor = new Player("Isabelle");
        Property property = new Property("Central", 800, 90);

        // Owner buys the property
        property.setOwner(owner);
        assertEquals(owner, property.getOwner(), "Owner should be set correctly.");

        // Visitor lands on the property
        property.action(visitor);
        assertEquals(1410, visitor.getMoney(), "Visitor should pay rent to the owner.");
        assertEquals(1590, owner.getMoney(), "Owner should receive rent from the visitor.");
    }

    @Test
    void testLoadingAndSavingGame() {
        // Placeholder for testing loading and saving game state
        GameController controller = new GameController(null, null, new Board());
        controller.saveGameToTextFile();

        // Validate file creation
        File savedFile = new File("saved_game.txt");
        assertTrue(savedFile.exists(), "Saved game file should exist.");

        // Validate loading the game
        controller.loadGameFromTextFile();
        assertTrue(controller.isGameLoaded(), "Game should load successfully.");
    }
}
