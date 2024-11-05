package controller;

import model.Game;
import model.Player;
import view.ConsoleView;
import view.GameView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize players
        List<Player> players = setupPlayers();

        // Initialize game and view
        Game game = new Game(players);
        GameView view = new ConsoleView();
        GameController controller = new GameController(game, view);

        // Start the game
        controller.startGame();
    }

    // Method to initialize players for the game with user input for customization
    private static List<Player> setupPlayers() {
        Scanner scanner = new Scanner(System.in);
        List<Player> players = new ArrayList<>();

        System.out.print("Enter the number of players (2-6): ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Validate player count
        if (numPlayers < 2 || numPlayers > 6) {
            System.out.println("Invalid number of players. Please restart and enter a number between 2 and 6.");
            System.exit(1);
        }

        // Set up each player with a custom name
        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            String name = scanner.nextLine();
            players.add(new Player(name, 1500)); // Starting money set to 1500 as per the game rules
        }

        System.out.println("Players have been set up successfully!\n");
        return players;
    }
}
