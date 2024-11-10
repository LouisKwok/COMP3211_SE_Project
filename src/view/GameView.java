package view;

import model.Board;
import model.Player;
import model.Square;

import java.util.ArrayList;

public class GameView {
    // Method to display the current status of the player
    public void displayPlayerStatus(Player player) {
        System.out.println(player.getName() + " - Money: HKD " + player.getMoney() + " - Position: " + player.getPosition());
    }

    // Method to display the current state of the board
    public void displayBoard(Board board, ArrayList<Player> players) {
        System.out.println("\n--- Current Board State ---");
        for (int i = 0; i < 20; i++) {
            Square square = board.getSquare(i);
            StringBuilder squareInfo = new StringBuilder();
            squareInfo.append("[").append(i + 1).append(": ").append(square.getName()).append("] ");

            // Check if any players are on this square
            ArrayList<String> playersOnSquare = new ArrayList<>();
            for (Player player : players) {
                if (player.getPosition() == i) {
                    playersOnSquare.add(player.getName() + " ($" + player.getMoney() + ")");
                }
            }

            if (!playersOnSquare.isEmpty()) {
                squareInfo.append("-> Players: ").append(String.join(", ", playersOnSquare));
            }

            System.out.println(squareInfo.toString());
        }
        System.out.println("--- End of Board State ---\n");
    }

    // Method to display a general message
    public void showMessage(String message) {
        System.out.println(message);
    }
}
