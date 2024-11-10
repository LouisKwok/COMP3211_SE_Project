package controller;

import model.Board;
import model.Dice;
import model.Player;
import model.Square;
import view.GameView;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameController {
    private Board board;
    private ArrayList<Player> players;
    private Dice dice;
    private GameView view;
    private int currentPlayerIndex;

    // Constructor accepting the Board, Dice, and GameView, and initializes players
    public GameController(Board board, Dice dice, GameView view) {
        this.board = board;
        this.dice = dice;
        this.view = view;
        this.players = initializePlayers();  // Initialize players during construction
        this.currentPlayerIndex = 0;
    }

    // Method to initialize players with user input or randomly generated names for each player individually
    private ArrayList<Player> initializePlayers() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Player> players = new ArrayList<>();
        int numPlayers = 0;

        // Get the number of players from the user with validation
        while (true) {
            System.out.print("Enter the number of players (2-6): ");
            try {
                numPlayers = scanner.nextInt();
                if (numPlayers >= 2 && numPlayers <= 6) {
                    break;
                } else {
                    System.out.println("Invalid number of players. Please enter a value between 2 and 6.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer between 2 and 6.");
                scanner.next(); // Clear the invalid input
            }
        }

        scanner.nextLine(); // Clear the newline character left in the buffer

        // List of random names for players
        List<String> randomNames = new ArrayList<>(List.of("Alex", "Taylor", "Jordan", "Morgan", "Sam", "Charlie"));
        Random random = new Random();

        // Generate player names based on each player's individual choice
        for (int i = 1; i <= numPlayers; i++) {
            String playerName;
            while (true) {
                System.out.print("Player " + i + ": Would you like to input your name or use a random name? (input/random): ");
                String choice = scanner.nextLine().toLowerCase();

                if (choice.equals("input")) {
                    // User inputs their name
                    System.out.print("Enter name for Player " + i + ": ");
                    playerName = scanner.nextLine();
                    break;
                } else if (choice.equals("random")) {
                    // System generates a random name
                    if (randomNames.isEmpty()) {
                        System.out.println("No more random names available, please input a name.");
                        continue;
                    }
                    int randomIndex = random.nextInt(randomNames.size());
                    playerName = randomNames.get(randomIndex);
                    randomNames.remove(randomIndex); // Remove the name from the list to avoid duplication
                    System.out.println("Player " + i + " is named: " + playerName);
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter 'input' or 'random'.");
                }
            }

            players.add(new Player(playerName));
        }

        return players;
    }

    // Main game loop
    public void startGame() {
        boolean gameEnded = false;

        while (!gameEnded) {
            // Display the current state of the board before each player's turn
            view.displayBoard(board, players);

            Player currentPlayer = players.get(currentPlayerIndex);
            view.displayPlayerStatus(currentPlayer);
            view.showMessage(currentPlayer.getName() + "'s turn. Rolling dice...");

            int roll1 = dice.roll();
            int roll2 = dice.roll();
            int rollTotal = roll1 + roll2;
            view.showMessage("Rolled: " + roll1 + " and " + roll2 + " (Total: " + rollTotal + ")");

            // Handle Jail Scenario
            if (currentPlayer.isInJail()) {
                view.showMessage(currentPlayer.getName() + " is in jail. Rolling for doubles or paying a fine.");
                if (roll1 == roll2) {
                    view.showMessage("Doubles rolled! " + currentPlayer.getName() + " is free from jail!");
                    currentPlayer.setInJail(false);
                    currentPlayer.move(rollTotal);
                } else {
                    currentPlayer.decreaseJailTurn();
                    if (currentPlayer.getJailTurns() == 0) {
                        view.showMessage(currentPlayer.getName() + " has paid HKD 150 fine to get out of jail.");
                        currentPlayer.updateMoney(-150);
                        currentPlayer.setInJail(false);
                        currentPlayer.move(rollTotal);
                    }
                }
            } else {
                currentPlayer.move(rollTotal);
                Square currentSquare = board.getSquare(currentPlayer.getPosition());
                currentSquare.action(currentPlayer); // Call the action method of the square
            }

            // Check player status (e.g., bankruptcy)
            if (currentPlayer.getMoney() < 0) {
                view.showMessage(currentPlayer.getName() + " is bankrupt and out of the game!");
                players.remove(currentPlayerIndex);
                if (players.size() == 0) {
                    view.showMessage("No players left. The game is over.");
                    gameEnded = true;
                    break;
                }
                currentPlayerIndex--;
            }

            // End game if only one player left or after 100 rounds (could be added as a counter)
            gameEnded = players.size() == 1;

            // Move to the next player
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }

        // Declare the winner
        if (players.size() == 1) {
            view.showMessage("Game Over! Winner: " + players.get(0).getName());
        }
    }
}
