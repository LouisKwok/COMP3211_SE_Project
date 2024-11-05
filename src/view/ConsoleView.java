package view;

import model.Game;
import model.Player;
import model.Property;
import model.Square;

public class ConsoleView implements GameView {

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void displayError(String error) {
        System.err.println("Error: " + error);
    }

    @Override
    public void displayGameStatus(Game game) {
        System.out.println("=== Game Status ===");

        // Display current player
        Player currentPlayer = game.getCurrentPlayer();
        System.out.println("Current Player: " + currentPlayer.getName());
        System.out.println("Position: " + currentPlayer.getPosition());
        System.out.println("Money: " + currentPlayer.getMoney());
        if (currentPlayer.isInJail()) {
            System.out.println(currentPlayer.getName() + " is in Jail.");
        }

        // Display board information
        System.out.println("\nBoard:");
        for (int i = 0; i < 20; i++) {
            Square square = game.getBoard().getSquare(i);
            String squareInfo = i + ": " + square.getName();
            if (square instanceof Property) {
                Property property = (Property) square;
                squareInfo += " (Price: " + property.getPrice() + ", Rent: " + property.getRent() + ")";
                if (property.isOwned()) {
                    squareInfo += " - Owned by " + property.getOwner().getName();
                }
            }
            System.out.println(squareInfo);
        }

        // Display all players' status
        System.out.println("\nPlayers:");
        for (Player player : game.getPlayers()) {
            System.out.println("- " + player.getName() + ": Position " + player.getPosition() +
                    ", Money: " + player.getMoney() +
                    (player.isInJail() ? " (In Jail)" : ""));
        }

        System.out.println("===================");
    }
}
