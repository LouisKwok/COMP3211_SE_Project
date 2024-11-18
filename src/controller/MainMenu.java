package controller;

import model.Board;
import model.Dice;
import view.GameView;

import java.util.Scanner;

public class MainMenu {

    private Board board; // Declare board at the class level so that it persists across method calls

    public void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Create necessary components for the game
        Dice dice = new Dice();
        GameView view = new GameView();
        BoardController boardController;

        // Initialize the board to null. It will be set when the user chooses New Game or Design Gameboard.
        board = null;

        while (running) {
            System.out.println("=====================================================");
            System.out.println("              WELCOME TO MONOPOLY!                   ");
            System.out.println("=====================================================");
            System.out.println("|  1. New Game                                      |");
            System.out.println("|  2. Continue                                      |");
            System.out.println("|  3. User Manual                                   |");
            System.out.println("|  4. Design Gameboard                              |");
            System.out.println("|  5. Quit                                          |");
            System.out.println("=====================================================");
            System.out.print("Please select an option (1-5): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                continue;
            }

            switch (choice) {
                case 1:
                    // Start a new game
                    System.out.println("Starting a new game...");

                    // If the board is null, initialize it with a new default or custom board
                    if (board == null) {
                        boardController = new BoardController(new Board());
                        board = boardController.initializeBoard();
                    }

                    // Start the game with the initialized board
                    GameController gameController = new GameController(dice, view, board);
                    gameController.startGame();
                    break;

                case 2:
                    // Continue a saved game
                    System.out.println("Loading saved game...");

                    // If the board is null, initialize it with a default board
                    if (board == null) {
                        boardController = new BoardController(new Board());
                        board = boardController.initializeBoard();
                    }

                    // Start the game with the loaded game state
                    GameController continueGameController = new GameController(dice, view, board);
                    continueGameController.loadGameFromTextFile();

                    // Check if the game was loaded successfully
                    if (continueGameController.isGameLoaded()) {
                        continueGameController.startGame();
                    } else {
                        System.out.println("Failed to load the game. Returning to the main menu.");
                    }
                    break;

                case 3:
                    // Show user manual
                    displayUserManual();
                    break;

                case 4:
                    // Design Gameboard (new feature)
                    if (board == null) {
                        board = new Board(); // Initialize a default board if it hasn't been initialized yet
                    }
                    boardController = new BoardController(board);
                    boardController.modifyPropertySquares(); // Modified to use the integrated modify method
                    break;

                case 5:
                    // Quit the game
                    System.out.println("Thank you for playing Monopoly! Goodbye.");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please enter a number between 1 and 5.");
            }
        }

        scanner.close();
    }

    private void displayUserManual() {
        System.out.println("=====================================================");
        System.out.println("                    USER MANUAL                      ");
        System.out.println("=====================================================");
        System.out.println("Welcome to Monopoly! Here are some basic rules:");
        System.out.println("- Players take turns rolling the dice and moving around the board.");
        System.out.println("- You can buy properties, pay rent, and collect money as you play.");
        System.out.println("- The goal is to bankrupt all other players and become the wealthiest.");
        System.out.println("Have fun and good luck!");
        System.out.println("=====================================================");
    }
}
