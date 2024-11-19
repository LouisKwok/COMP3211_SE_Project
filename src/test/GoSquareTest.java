package test;

import model.GoSquare;
import model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoSquareTest {

    @Test
    void testPassingGoIncreasesMoney() {
        // Test for money increment after passing "Go"
        Player player = new Player("Bob");
        GoSquare goSquare = new GoSquare();

        goSquare.action(player);
        assertEquals(3000, player.getMoney(), "Player should receive HKD 1500 after passing Go.");
    }
}
