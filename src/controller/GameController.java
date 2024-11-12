package controller;

import model.Board;
import model.Dice;
import model.Player;
import model.Square;
import model.Property;
import view.GameView;

import java.io.*;
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

    // Constructor accepting Dice, GameView, and Board, and initializes players
    public GameController(Dice dice, GameView view, Board board) {
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
                    System.out.print("Enter name for Player " + i + ": ");
                    playerName = scanner.nextLine();
                    break;
                } else if (choice.equals("random")) {
                    if (randomNames.isEmpty()) {
                        System.out.println("No more random names available, please input a name.");
                        continue;
                    }
                    int randomIndex = random.nextInt(randomNames.size());
                    playerName = randomNames.get(randomIndex);
                    randomNames.remove(randomIndex);
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

    // Method to start the game
    public void startGame() {
        boolean gameEnded = false;

        while (!gameEnded) {
            // Display the current state of the board using the new large view
            view.displayBoardLarge(board, players);

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
                view.showMessage(currentPlayer.getName() + " landed on " + currentSquare.getName());

                // Handle property squares explicitly
                if (currentSquare instanceof Property) {
                    Property property = (Property) currentSquare;

                    // Display property details using getters
                    view.showMessage("Property Details: " + property.getName() + ", Price: HKD " + property.getPrice() + ", Rent: HKD " + property.getRent());

                    // Use `getOwner()` to determine the ownership status of the property
                    if (property.getOwner() == null) {
                        // Property is available for purchase
                        view.showMessage("This property is available for purchase.");
                        property.action(currentPlayer); // Handle the purchase decision inside the action method
                    } else if (property.getOwner().equals(currentPlayer)) {
                        // Player owns this property
                        view.showMessage(currentPlayer.getName() + " owns this property.");
                    } else {
                        // Another player owns this property
                        view.showMessage("This property is owned by " + property.getOwner().getName() + ".");
                        property.action(currentPlayer); // Handle paying rent inside the action method
                    }
                } else {
                    // Handle non-property squares
                    currentSquare.action(currentPlayer);
                }
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
                currentPlayerIndex--; // Decrement to adjust the index since we removed a player
            }

            // End game if only one player left
            gameEnded = players.size() == 1;

            // Move to the next player
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }

        // Declare the winner
        if (players.size() == 1) {
            view.showMessage("Game Over! Winner: " + players.get(0).getName());
        }
    }

//    // Method to save the current game to a file
//    public void saveBoardToFile() {
//        try (FileOutputStream fileOut = new FileOutputStream("board.ser");
//             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
//            out.writeObject(board);
//            System.out.println("Board saved successfully.");
//        } catch (IOException i) {
//            i.printStackTrace();
//        }
//    }


    // Method to load a game from a file
    public void loadGameFromFile() {
        File saveFile = new File("saved_game.ser");

        // 檢查文件是否存在
        if (!saveFile.exists()) {
            System.out.println("No saved game found. Starting a new game instead.");
            return;
        }

        try (FileInputStream fileIn = new FileInputStream(saveFile);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            players = (ArrayList<Player>) in.readObject();
            board = (Board) in.readObject();
            currentPlayerIndex = (int) in.readObject();
            System.out.println("Game loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed to load the game. Starting a new game instead.");
        }
    }

}
