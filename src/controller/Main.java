package controller;

import model.*;
import view.GameView;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize game components
        GameView view = new GameView();
        Board board = new Board();
        Dice dice = new Dice();
        ArrayList<Player> players = new ArrayList<>();

        // Creating players
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of players (2-6): ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine(); // consume newline

        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter name for Player " + (i + 1) + ": ");
            String playerName = scanner.nextLine();
            players.add(new Player(playerName, 1500));
        }

        // Game loop
        boolean gameOn = true;
        int currentPlayerIndex = 0;
        int rounds = 0;

        while (gameOn && rounds < 100) {
            Player currentPlayer = players.get(currentPlayerIndex);
            if (currentPlayer.isInJail()) {
                // Handle Jail Condition
                JailManager jailManager = new JailManager();
                jailManager.handleJail(currentPlayer);
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
                continue;
            }

            // Display player status
            view.displayPlayerStatus(currentPlayer);

            // Roll dice and move
            Command moveCommand = new MoveCommand(currentPlayer, board, view);
            moveCommand.execute();

            // If the player lands on a property and it is available, ask if they want to buy it
            Square currentSquare = board.getSquare(currentPlayer.getPosition());
            if (currentSquare instanceof Property && ((Property) currentSquare).getOwner() == null) {
                System.out.print(currentPlayer.getName() + ", do you want to buy " + currentSquare.getName() + " for HKD " + ((Property) currentSquare).getPrice() + "? (yes/no): ");
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("yes")) {
                    Command buyCommand = new BuyPropertyCommand(currentPlayer, (Property) currentSquare, view);
                    buyCommand.execute();
                }
            }
            // Check for bankruptcy
            BankruptManager bankruptManager = new BankruptManager();
            if (bankruptManager.isBankrupt(currentPlayer)) {
                bankruptManager.declareBankruptcy(currentPlayer);
                players.remove(currentPlayerIndex);
                if (players.size() == 1) {
                    gameOn = false;
                    break;
                }
                continue;
            }

            // Move to next player
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            rounds++;
        }

        // Game over, announce winner(s)
        if (players.size() == 1) {
            System.out.println("\nGame Over! The winner is " + players.get(0).getName() + " with HKD " + players.get(0).getMoney() + "!");
        } else {
            System.out.println("\nGame Over! The game ended after 100 rounds.");
            for (Player player : players) {
                System.out.println(player.getName() + " has HKD " + player.getMoney());
            }
        }

        scanner.close();
    }
}
