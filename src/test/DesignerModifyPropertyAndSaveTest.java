package test;

import controller.BoardController;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class DesignerModifyPropertyAndSaveTest {

    private Board board;
    private BoardController boardController;

    @BeforeEach
    public void setUp() {
        // Use the default board setup
        board = new Board();
        boardController = new BoardController(board);
    }

    // Test modifying a property and saving the board
    @Test
    public void testModifyPropertyAndSave() {
        // Modify property details for the square at position 3 (Wan Chai)
        // Note: Position 3 is index 2 in zero-based indexing.
        int pos = 2;
        Square square = board.getSquares().get(pos);

        // Check if the square is an instance of Property before modification
        if (square instanceof Property) {
            Property property = (Property) square;

            // Modify the property details
            property.setName("Tai Wai");
            property.setPrice(900);
            property.setRent(90);

            // Print a message indicating the modification operation
            System.out.println("Modified property details for Wan Chai to Tai Wai: Name, Price, and Rent.");

            // Verify the changes
            assertEquals("Tai Wai", property.getName(), "Property name should be updated to 'Tai Wai'");
            assertEquals(900, property.getPrice(), "Property price should be updated to 900");
            assertEquals(90, property.getRent(), "Property rent should be updated to 90");
        } else {
            fail("The square at position 3 should be a property.");
        }

        // Save the board to a TXT file
        boardController.saveBoardToTXT();

        // Verify that the file exists and is not empty
        File file = new File("custom_board.txt");
        assertTrue(file.exists(), "The saved board file should exist");
        assertTrue(file.length() > 0, "The saved board file should not be empty");

        // Output success message if all assertions pass
        System.out.println("DesignerModifyPropertyAndSaveTest: testModifyPropertyAndSave passed successfully.");
    }
}
