package controller;

import model.Dice;
import view.GameView;

import java.util.Scanner;

public class MainMenu {

    private GameController gameController; // The GameController instance to manage the game
    private Dice dice;
    private GameView view;
    private BoardController boardController;

    public MainMenu() {
        // Initialize the components once and reuse them
        this.dice = new Dice();
        this.view = new GameView();
        this.boardController = new BoardController();
    }

    public void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            // Clear the console for a better visual experience (not always supported in all environments)
            System.out.print("\033[H\033[2J");
            System.out.flush();

            // Display ASCII art and menu options
            System.out.println("=====================================================");
            System.out.println("              WELCOME TO MONOPOLY!                   ");
            System.out.println("=====================================================");
            System.out.println("|                                                   |");
            System.out.println("|                    MAIN MENU                      |");
            System.out.println("|                                                   |");
            System.out.println("|  1. New Game                                      |");
            System.out.println("|  2. Continue                                      |");
            System.out.println("|  3. User Manual                                   |");
            System.out.println("|  4. Quit                                          |");
            System.out.println("|                                                   |");
            System.out.println("=====================================================");
            System.out.print("Please select an option (1-4): ");

            int choice = -1;

            // Get valid user input
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
            }

            switch (choice) {
                case 1:
                    // Start a new game
                    System.out.println("Starting a new game...");
                    startNewGame();
                    break;
                case 2:
                    // Continue a saved game
                    System.out.println("Loading saved game...");
                    continueGame();
                    break;
                case 3:
                    // Show user manual
                    displayUserManual();
                    break;
                case 4:
                    // Quit the game
                    System.out.println("Thank you for playing Monopoly! Goodbye.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please enter a number between 1 and 4.");
                    pauseForUser();
            }
        }

        scanner.close();
    }

    private void startNewGame() {
        // Initialize a new game with new board settings
        gameController = new GameController(dice, view, boardController.initializeBoard());
        gameController.startGame();
    }

    private void continueGame() {
        if (gameController == null) {
            // Create a new GameController to load a saved game if one does not exist
            gameController = new GameController(dice, view, boardController.initializeBoard());
        }
        gameController.loadGameFromFile();  // Load a previously saved game
        gameController.startGame();         // Continue the game from the loaded state
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
        pauseForUser();
    }

    private void pauseForUser() {
        System.out.print("Press Enter to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void main(String[] args) {
        MainMenu menu = new MainMenu();
        menu.displayMainMenu();
    }
}
