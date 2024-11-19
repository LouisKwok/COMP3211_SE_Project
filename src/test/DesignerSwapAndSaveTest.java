package test;

import controller.BoardController;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DesignerSwapAndSaveTest {

    private Board board;
    private BoardController boardController;

    @BeforeEach
    public void setUp() {
        // Use the default board setup
        board = new Board();
        boardController = new BoardController(board);
    }

    // Test swapping two squares and saving the board
    @Test
    public void testSwapSquaresAndSave() {
        // Swap squares at positions 8 and 10 (Mong Kok and Tsing Yi)
        // Note: Indices in Java start at 0, so position 8 is index 7 and position 10 is index 9.
        List<Square> squares = board.getSquares();
        Square square1 = squares.get(7); // Mong Kok (at index 7)
        Square square2 = squares.get(9); // Tsing Yi (at index 9)

        // Perform the swap
        Square temp = squares.get(7);
        squares.set(7, squares.get(9));
        squares.set(9, temp);

        // Print a message indicating the swap operation
        System.out.println("Swapping Mong Kok with Tsing Yi.");

        // Verify that the squares have been swapped
        assertEquals(square2, squares.get(7), "Square at position 8 should now be Tsing Yi");
        assertEquals(square1, squares.get(9), "Square at position 10 should now be Mong Kok");

        // Save the board to a TXT file
        boardController.saveBoardToTXT();

        // Verify that the file exists and is not empty
        File file = new File("custom_board.txt");
        assertTrue(file.exists(), "The saved board file should exist");
        assertTrue(file.length() > 0, "The saved board file should not be empty");

        // Output success message if all assertions pass
        System.out.println("DesignerSwapAndSaveTest: testSwapSquaresAndSave passed successfully.");
    }
}
