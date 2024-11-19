package test;

import controller.BoardController;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DesignerChangeSquareTypeAndSaveTest {

    private Board board;
    private BoardController boardController;

    @BeforeEach
    public void setUp() {
        // Use the default board setup
        board = new Board();
        boardController = new BoardController(board);
    }

    // Test changing the square type and saving the board
    @Test
    public void testChangeSquareTypeAndSave() {
        // Change square type at position 8 (Mong Kok)
        // Note: Position 8 is index 7 in zero-based indexing.
        int pos = 7;
        List<Square> squares = board.getSquares();

        // Replace Mong Kok (which is a Property) with a new Income Tax square
        Square newSquare = new IncomeTaxSquare();
        squares.set(pos, newSquare);

        // Print a message indicating the change operation
        System.out.println("Changing Mong Kok to an Income Tax square.");

        // Verify the type of the square has changed
        assertTrue(squares.get(pos) instanceof IncomeTaxSquare, "Square at position 8 should now be an Income Tax square");

        // Save the board to a TXT file
        boardController.saveBoardToTXT();

        // Verify that the file exists and is not empty
        File file = new File("custom_board.txt");
        assertTrue(file.exists(), "The saved board file should exist");
        assertTrue(file.length() > 0, "The saved board file should not be empty");

        // Output success message if all assertions pass
        System.out.println("DesignerChangeSquareTypeAndSaveTest: testChangeSquareTypeAndSave passed successfully.");
    }
}
