package test;
import controller.BoardController;
import model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardControllerTest {

    private BoardController boardController;
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(); // Initialize a default Board
        boardController = new BoardController(board); // Initialize BoardController with the Board
    }

    // 1. Test modifying property details
    @Test
    void testModifyPropertyDetails() {
        // Assume that "Central" is the property at position 1
        Square square = board.getSquares().get(1);
        assertTrue(square instanceof Property);

        Property property = (Property) square;

        // Modify property details
        String newName = "New Central";
        int newPrice = 900;
        int newRent = 90;

        property.setName(newName);
        property.setPrice(newPrice);
        property.setRent(newRent);

        // Verify that the modifications were successful
        assertEquals(newName, property.getName());
        assertEquals(newPrice, property.getPrice());
        assertEquals(newRent, property.getRent());
    }

    // 2. Test swapping two squares
    @Test
    void testSwapSquares() {
        // Swap squares at positions 1 and 2
        List<Square> squares = board.getSquares();
        Square square1 = squares.get(1);
        Square square2 = squares.get(2);

        // Swap squares
        squares.set(1, square2);
        squares.set(2, square1);

        // Verify that the squares have been swapped
        assertEquals(square2, squares.get(1));
        assertEquals(square1, squares.get(2));
    }

    // 3. Test changing square type
    @Test
    void testChangeSquareType() {
        // Change the square at position 1 from Property to GoSquare
        List<Square> squares = board.getSquares();
        squares.set(1, new GoSquare());

        // Verify that the square has been changed to GoSquare
        assertTrue(squares.get(1) instanceof GoSquare);
    }

    // 4. Test loading board layout from TXT file
    @Test
    void testLoadBoardFromTXT() {
        Board loadedBoard = boardController.loadBoardFromTXT();

        // Simply verify if the board layout is successfully loaded
        assertNotNull(loadedBoard.getSquares());
        assertFalse(loadedBoard.getSquares().isEmpty());

        // Verify a specific square to check if it matches the expected layout (assuming known layout in file)
        Square loadedSquare = loadedBoard.getSquares().get(0);
        assertTrue(loadedSquare instanceof GoSquare);
    }

    // 5. Test saving board layout to TXT file
    @Test
    void testSaveBoardToTXT() {
        // Modify the board layout
        List<Square> squares = board.getSquares();
        squares.set(1, new Property("New Property", 1000, 100));

        // Save the layout to the TXT file
        boardController.saveBoardToTXT();

        // Create a new controller to reload the saved layout
        BoardController newController = new BoardController(new Board());
        Board newBoard = newController.loadBoardFromTXT();

        // Verify that the newly loaded layout matches the previously saved layout
        assertEquals("New Property", newBoard.getSquares().get(1).getName());
    }
}