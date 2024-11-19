package test;


import controller.GameController;
import model.Board;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

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
