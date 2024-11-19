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
    private boolean gameLoaded;
    private int currentRound;

    // Constructor accepting Dice, GameView, and Board, without automatically initializing players
    public GameController(Dice dice, GameView view, Board board) {
        this.board = board;
        this.dice = dice;
        this.view = view;
        this.players = new ArrayList<>();  // Initialize as an empty list, players are set during startGame or loadGame
        this.currentPlayerIndex = 0;
        this.gameLoaded = false;
        this.currentRound = 0;
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
        // Initialize players only if they haven't been loaded from a saved game
        if (players.isEmpty()) {
            players = initializePlayers();  // Initialize players during game start if not loaded
        }

        boolean gameEnded = false;
        Scanner scanner = new Scanner(System.in);

        while (!gameEnded) {
            // Display the current state of the board with round information
            view.displayBoardLarge(board, players, currentRound);

            Player currentPlayer = players.get(currentPlayerIndex);


            boolean turnEnded = false;

            while (!turnEnded) {
                // Display options for the player
                System.out.println("\n" + currentPlayer.getName() + "'s Turn - Choose an action:");
                System.out.println("1. Roll the dice");
                System.out.println("2. See the status of any specific player or all players");
                System.out.println("3. Query the next player");
                System.out.println("4. Save the current game to a file");
                System.out.println("5. Quit the game");

                int choice;
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number between 1 and 5.");
                    continue;
                }

                switch (choice) {
                    case 1: // Roll the dice
                        view.showMessage(currentPlayer.getName() + " is rolling the dice...");
                        int roll1 = dice.roll();
                        int roll2 = dice.roll();
                        int rollTotal = roll1 + roll2;
                        view.showMessage("Rolled: " + roll1 + " and " + roll2 + " (Total: " + rollTotal + ")");
                        handlePlayerMove(currentPlayer, rollTotal, roll1 == roll2);
                        turnEnded = true; // End the player's turn after rolling
                        break;

                    case 2: // See player status
                        System.out.println("Do you want to see the status of a specific player or all players?");
                        System.out.println("1. Specific player");
                        System.out.println("2. All players");

                        int subChoice;
                        try {
                            subChoice = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter either 1 or 2.");
                            continue;
                        }

                        if (subChoice == 1) {
                            System.out.println("Enter player number (1-" + players.size() + "): ");
                            int playerNum;
                            try {
                                playerNum = Integer.parseInt(scanner.nextLine());
                                if (playerNum < 1 || playerNum > players.size()) {
                                    System.out.println("Invalid player number.");
                                    continue;
                                }
                                displayPlayerStatus(players.get(playerNum - 1));
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid player number.");
                            }
                        } else if (subChoice == 2) {
                            for (Player player : players) {
                                displayPlayerStatus(player);
                            }
                        } else {
                            System.out.println("Invalid choice. Please enter either 1 or 2.");
                        }
                        break;

                    case 3: // Query the next player
                        Player nextPlayer = players.get((currentPlayerIndex + 1) % players.size());
                        view.showMessage("The next player will be: " + nextPlayer.getName());
                        break;

                    case 4: // Save the current game to a file
                        saveGameToTextFile();
                        break;

                    case 5: // Quit the game
                        view.showMessage("Thank you for playing Monopoly! Goodbye.");
                        return;

                    default:
                        System.out.println("Invalid option. Please enter a number between 1 and 5.");
                        break;
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

            // End game if only one player left or if 100 rounds have passed
            if (players.size() == 1 || currentRound >= 100) {
                gameEnded = true;
            }

            // Increment round only if the current player is the last in the list
            if (currentPlayerIndex == players.size() - 1) {
                currentRound++;
            }

            // Move to the next player
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }

        // Determine the winner(s) at the end of the game
        determineWinners();
    }

    private void determineWinners() {
        if (players.size() == 1) {
            view.showMessage("Game Over! Winner: " + players.get(0).getName());
        } else {
            // Find the player(s) with the most money
            int maxMoney = players.stream().mapToInt(Player::getMoney).max().orElse(0);
            List<Player> winners = new ArrayList<>();
            for (Player player : players) {
                if (player.getMoney() == maxMoney) {
                    winners.add(player);
                }
            }

            if (winners.size() == 1) {
                view.showMessage("Game Over! Winner: " + winners.get(0).getName());
            } else {
                view.showMessage("Game Over! It's a tie between the following players:");
                for (Player winner : winners) {
                    view.showMessage(winner.getName() + " with " + winner.getMoney() + " HKD");
                }
            }
        }
    }

    // Method to handle player's move
    private void handlePlayerMove(Player player, int rollTotal, boolean rolledDoubles) {
        if (player.isInJail()) {
            view.showMessage(player.getName() + " is in jail. Rolling for doubles or paying a fine.");
            if (rolledDoubles) {
                view.showMessage("Doubles rolled! " + player.getName() + " is free from jail!");
                player.setInJail(false);
            } else {
                player.decreaseJailTurn();
                if (player.getJailTurns() == 0) {
                    view.showMessage(player.getName() + " has paid HKD 150 fine to get out of jail.");
                    player.updateMoney(-150);
                    player.setInJail(false);
                } else {
                    return; // End the player's turn if they remain in jail
                }
            }
        }

        player.move(rollTotal);
        Square currentSquare = board.getSquare(player.getPosition());
        view.showMessage(player.getName() + " landed on " + currentSquare.getName());

        if (currentSquare instanceof Property) {
            Property property = (Property) currentSquare;
            if (property.getOwner() == null) {
                property.action(player); // Handle the purchase decision inside the action method
            } else if (!property.getOwner().equals(player)) {
                property.action(player); // Handle paying rent
            } else {
                view.showMessage(player.getName() + " owns this property.");
            }
        } else {
            currentSquare.action(player);
        }
    }

    // Method to save the current game state to a text file
    public void saveGameToTextFile() {
        File saveFile = new File("saved_game.txt");

        try (PrintWriter writer = new PrintWriter(new FileWriter(saveFile))) {
            writer.println(currentPlayerIndex);
            writer.println(players.size());
            for (Player player : players) {
                writer.println(player.getName());
                writer.println(player.getMoney());
                writer.println(player.getPosition());
                writer.println(player.isInJail());
                writer.println(player.getJailTurns());
            }

            System.out.println("Game saved successfully to saved_game.txt.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save the game.");
        }
    }

    // Method to load the game state from a text file
    public void loadGameFromTextFile() {
        File saveFile = new File("saved_game.txt");

        if (!saveFile.exists()) {
            System.out.println("No saved game found. Starting a new game instead.");
            gameLoaded = false;
            return;
        }

        try (Scanner scanner = new Scanner(saveFile)) {
            currentPlayerIndex = Integer.parseInt(scanner.nextLine());
            int numPlayers = Integer.parseInt(scanner.nextLine());
            players = new ArrayList<>();

            for (int i = 0; i < numPlayers; i++) {
                String name = scanner.nextLine();
                int money = Integer.parseInt(scanner.nextLine());
                int position = Integer.parseInt(scanner.nextLine());
                boolean inJail = Boolean.parseBoolean(scanner.nextLine());
                int jailTurns = Integer.parseInt(scanner.nextLine());

                Player player = new Player(name);
                player.updateMoney(money - player.getMoney());
                player.setPosition(position);
                player.setInJail(inJail);
                for (int j = 0; j < jailTurns; j++) {
                    player.decreaseJailTurn();
                }

                players.add(player);
            }

            System.out.println("Game loaded successfully from saved_game.txt.");
            gameLoaded = true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to load the game. Starting a new game instead.");
            gameLoaded = false;
        }
    }

    // Display the status of a specific player, including properties owned and jail status
    public void displayPlayerStatus(Player player) {
        System.out.println("-----------------------------------------------------");
        System.out.println("Player: " + player.getName());
        System.out.println("Position: " + (player.getPosition() + 1));
        System.out.println("Money: " + player.getMoney() + " HKD");

        // Show if the player is in jail or not
        if (player.isInJail()) {
            System.out.println("Status: In Jail");
        } else {
            System.out.println("Status: Free");
        }

        // Display properties owned by the player
        List<Property> ownedProperties = getPlayerProperties(player);
        if (ownedProperties.isEmpty()) {
            System.out.println("Owned Properties: None");
        } else {
            System.out.print("Owned Properties: ");
            for (Property property : ownedProperties) {
                System.out.print(property.getName() + " (Price: " + property.getPrice() + ", Rent: " + property.getRent() + "); ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------------");
    }

    // Get the list of properties owned by a player
    private List<Property> getPlayerProperties(Player player) {
        List<Property> ownedProperties = new ArrayList<>();
        for (Square square : board.getSquares()) {
            if (square instanceof Property) {
                Property property = (Property) square;
                if (player.equals(property.getOwner())) {
                    ownedProperties.add(property);
                }
            }
        }
        return ownedProperties;
    }


    // Method to check if the game was successfully loaded
    public boolean isGameLoaded() {
        return gameLoaded;
    }
}
