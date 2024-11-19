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

    // Define shared test data
    private String newName;
    private int newPrice;
    private int newRent;

    @BeforeEach
    void setUp() {
        board = new Board(); // Initialize a default Board
        boardController = new BoardController(board); // Initialize BoardController with the Board

        // Initialize shared test data
        newName = "New Central";
        newPrice = 900;
        newRent = 90;

        System.out.println("[Setup] Initialized test board and shared data.");
    }

    // 1. Test modifying property details and saving to file
    @Test
    void testModifyPropertyDetails() {
        System.out.println("[Test] Starting testModifyPropertyDetails...");

        // Assume that "Central" is the property at position 1
        Square square = board.getSquares().get(1);
        assertTrue(square instanceof Property);

        Property property = (Property) square;

        System.out.println("[Action] Modifying property details...");
        // Modify property details using shared test data
        property.setName(newName);
        property.setPrice(newPrice);
        property.setRent(newRent);

        // Save the modified board to the TXT file
        System.out.println("[Action] Saving modified board to file...");
        boardController.saveBoardToTXT();

        // Create a new controller to reload the saved layout
        System.out.println("[Action] Reloading board from saved file...");
        BoardController newController = new BoardController(new Board());
        Board newBoard = newController.loadBoardFromTXT();

        // Verify that the newly loaded layout matches the modified layout
        Property loadedProperty = (Property) newBoard.getSquares().get(1);
        System.out.println("[Verify] Verifying loaded property details...");
        assertEquals(newName, loadedProperty.getName());
        assertEquals(newPrice, loadedProperty.getPrice());
        assertEquals(newRent, loadedProperty.getRent());

        System.out.println("[Test] testModifyPropertyDetails completed successfully.\n");
    }

    // 2. Test swapping two squares and saving to file
    @Test
    void testSwapSquares() {
        System.out.println("[Test] Starting testSwapSquares...");

        // Swap squares at positions 1 and 2
        List<Square> squares = board.getSquares();
        Square square1 = squares.get(1);
        Square square2 = squares.get(2);

        System.out.println("[Action] Swapping squares at position 1 and 2...");
        // Swap squares
        squares.set(1, square2);
        squares.set(2, square1);

        // Save the swapped board to the TXT file
        System.out.println("[Action] Saving swapped board to file...");
        boardController.saveBoardToTXT();

        // Create a new controller to reload the saved layout
        System.out.println("[Action] Reloading board from saved file...");
        BoardController newController = new BoardController(new Board());
        Board newBoard = newController.loadBoardFromTXT();

        // Verify that the squares have been swapped
        System.out.println("[Verify] Verifying swapped squares...");
        assertEquals(square2.getName(), newBoard.getSquares().get(1).getName());
        assertEquals(square1.getName(), newBoard.getSquares().get(2).getName());

        System.out.println("[Test] testSwapSquares completed successfully.\n");
    }

    // 3. Test changing square type and saving to file
    @Test
    void testChangeSquareType() {
        System.out.println("[Test] Starting testChangeSquareType...");

        // Change the square at position 1 from Property to GoSquare
        List<Square> squares = board.getSquares();
        System.out.println("[Action] Changing square at position 1 from Property to GoSquare...");
        squares.set(1, new GoSquare());

        // Save the changed board to the TXT file
        System.out.println("[Action] Saving modified board to file...");
        boardController.saveBoardToTXT();

        // Create a new controller to reload the saved layout
        System.out.println("[Action] Reloading board from saved file...");
        BoardController newController = new BoardController(new Board());
        Board newBoard = newController.loadBoardFromTXT();

        // Verify that the square has been changed to GoSquare
        System.out.println("[Verify] Verifying changed square type...");
        assertTrue(newBoard.getSquares().get(1) instanceof GoSquare);

        System.out.println("[Test] testChangeSquareType completed successfully.\n");
    }

    // 4. Test loading board layout from TXT file
    @Test
    void testLoadBoardFromTXT() {
        System.out.println("[Test] Starting testLoadBoardFromTXT...");

        // Load the board from TXT file
        System.out.println("[Action] Loading board from file...");
        Board loadedBoard = boardController.loadBoardFromTXT();

        // Simply verify if the board layout is successfully loaded
        System.out.println("[Verify] Verifying board layout...");
        assertNotNull(loadedBoard.getSquares());
        assertFalse(loadedBoard.getSquares().isEmpty());

        // Verify a specific square to check if it matches the expected layout (assuming known layout in file)
        Square loadedSquare = loadedBoard.getSquares().get(0);
        assertTrue(loadedSquare instanceof GoSquare);

        System.out.println("[Test] testLoadBoardFromTXT completed successfully.\n");
    }

    // 5. Test saving board layout to TXT file
    @Test
    void testSaveBoardToTXT() {
        System.out.println("[Test] Starting testSaveBoardToTXT...");

        // Modify the board layout using shared test data
        List<Square> squares = board.getSquares();
        System.out.println("[Action] Modifying square at position 1...");
        squares.set(1, new Property(newName, newPrice, newRent));

        // Save the layout to the TXT file
        System.out.println("[Action] Saving modified board to file...");
        boardController.saveBoardToTXT();

        // Create a new controller to reload the saved layout
        System.out.println("[Action] Reloading board from saved file...");
        BoardController newController = new BoardController(new Board());
        Board newBoard = newController.loadBoardFromTXT();

        // Verify that the newly loaded layout matches the previously saved layout
        System.out.println("[Verify] Verifying saved property details...");
        assertEquals(newName, newBoard.getSquares().get(1).getName());
        assertTrue(newBoard.getSquares().get(1) instanceof Property);
        Property loadedProperty = (Property) newBoard.getSquares().get(1);
        assertEquals(newPrice, loadedProperty.getPrice());
        assertEquals(newRent, loadedProperty.getRent());

        System.out.println("[Test] testSaveBoardToTXT completed successfully.\n");
    }
}
