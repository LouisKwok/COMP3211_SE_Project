package view;

import model.Board;
import model.Player;
import model.Property;
import model.Square;

import java.util.List;

public class GameView {

    // Display a large, ASCII-style representation of the Monopoly board with numbered positions
    public void displayBoardLarge(Board board, List<Player> players) {
        List<Square> squares = board.getSquares();

        // Define box width for consistency
        int boxWidth = 30;

        // Top row
        System.out.println("╔════════════════════════════════╦════════════════════════════════╦════════════════════════════════╦════════════════════════════════╦════════════════════════════════╦════════════════════════════════╗");
        System.out.printf("║ %-30s ║ %-30s ║ %-30s ║ %-30s ║ %-30s ║ %-30s ║\n",
                formatSquareTitle(squares.get(10), 10),
                formatSquareTitle(squares.get(11), 11),
                formatSquareTitle(squares.get(12), 12),
                formatSquareTitle(squares.get(13), 13),
                formatSquareTitle(squares.get(14), 14),
                formatSquareTitle(squares.get(15), 15));
        System.out.printf("║ %-30s ║ %-30s ║ %-30s ║ %-30s ║ %-30s ║ %-30s ║\n",
                formatSquareDetails(squares.get(10), 10, players, boxWidth),
                formatSquareDetails(squares.get(11), 11, players, boxWidth),
                formatSquareDetails(squares.get(12), 12, players, boxWidth),
                formatSquareDetails(squares.get(13), 13, players, boxWidth),
                formatSquareDetails(squares.get(14), 14, players, boxWidth),
                formatSquareDetails(squares.get(15), 15, players, boxWidth));
        System.out.println("╠════════════════════════════════╩════════════════════════════════╩════════════════════════════════╩════════════════════════════════╩════════════════════════════════╩════════════════════════════════╣");

        // Display the middle section (manually without loop)

        // Square 9 and 16
        System.out.printf("║ %-30s ║                                                                                                                                   ║ %-30s ║\n",
                formatSquareTitle(squares.get(9), 9),
                formatSquareTitle(squares.get(16), 16));
        System.out.printf("║ %-30s ║                                                                                                                                   ║ %-30s ║\n",
                formatSquareDetails(squares.get(9), 9, players, 30),
                formatSquareDetails(squares.get(16), 16, players, 30));
        System.out.println("╠════════════════════════════════╣                                                                                                                                   ╠════════════════════════════════╣");

        // Square 8 and 17
        System.out.printf("║ %-30s ║                                                                                                                                   ║ %-30s ║\n",
                formatSquareTitle(squares.get(8), 8),
                formatSquareTitle(squares.get(17), 17));
        System.out.printf("║ %-30s ║                                                                                                                                   ║ %-30s ║\n",
                formatSquareDetails(squares.get(8), 8, players, 30),
                formatSquareDetails(squares.get(17), 17, players, 30));
        System.out.println("╠════════════════════════════════╣                                                                                                                                   ╠════════════════════════════════╣");

// Square 7 and 18
        System.out.printf("║ %-30s ║                                                                                                                                   ║ %-30s ║\n",
                formatSquareTitle(squares.get(7), 7),
                formatSquareTitle(squares.get(18), 18));
        System.out.printf("║ %-30s ║                                                                                                                                   ║ %-30s ║\n",
                formatSquareDetails(squares.get(7), 7, players, 30),
                formatSquareDetails(squares.get(18), 18, players, 30));
        System.out.println("╠════════════════════════════════╣                                                                                                                                   ╠════════════════════════════════╣");

// Square 6 and 19
        System.out.printf("║ %-30s ║                                                                                                                                   ║ %-30s ║\n",
                formatSquareTitle(squares.get(6), 6),
                formatSquareTitle(squares.get(19), 19));
        System.out.printf("║ %-30s ║                                                                                                                                   ║ %-30s ║\n",
                formatSquareDetails(squares.get(6), 6, players, 30),
                formatSquareDetails(squares.get(19), 19, players, 30));


        // Bottom row
        System.out.println("╠════════════════════════════════╦════════════════════════════════╦════════════════════════════════╦════════════════════════════════╦════════════════════════════════╦════════════════════════════════╣");
        System.out.printf("║ %-30s ║ %-30s ║ %-30s ║ %-30s ║ %-30s ║ %-30s ║\n",
                formatSquareTitle(squares.get(5), 5),
                formatSquareTitle(squares.get(4), 4),
                formatSquareTitle(squares.get(3), 3),
                formatSquareTitle(squares.get(2), 2),
                formatSquareTitle(squares.get(1), 1),
                formatSquareTitle(squares.get(0), 0));
        System.out.printf("║ %-30s ║ %-30s ║ %-30s ║ %-30s ║ %-30s ║ %-30s ║\n",
                formatSquareDetails(squares.get(5), 5, players, boxWidth),
                formatSquareDetails(squares.get(4), 4, players, boxWidth),
                formatSquareDetails(squares.get(3), 3, players, boxWidth),
                formatSquareDetails(squares.get(2), 2, players, boxWidth),
                formatSquareDetails(squares.get(1), 1, players, boxWidth),
                formatSquareDetails(squares.get(0), 0, players, boxWidth));
        System.out.println("╚════════════════════════════════╩════════════════════════════════╩════════════════════════════════╩════════════════════════════════╩════════════════════════════════╩════════════════════════════════╝");

        // Display players' positions
        System.out.println("Players' Positions:");
        for (Player player : players) {
            System.out.println(player.getName() + " is on square " + (player.getPosition() + 1));
        }
    }

    // Helper method to format square title (name and position)
    private String formatSquareTitle(Square square, int position) {
        return (position + 1) + ". " + square.getName();
    }

    // Helper method to format square details (price, rent, and players)
    private String formatSquareDetails(Square square, int position, List<Player> players, int boxWidth) {
        StringBuilder details = new StringBuilder();

        // If the square is a Property, show price and rent
        if (square instanceof Property) {
            Property property = (Property) square;
            details.append("(Price: HKD ").append(property.getPrice())
                    .append(", Rent: HKD ").append(property.getRent()).append(")");
        }

        // Check if there are players on this square, and show their names
        int playerCount = 0;
        for (Player player : players) {
            if (player.getPosition() == position) {
                if (details.length() > 0) details.append(" ");
                if (details.length() + player.getName().length() + 2 > boxWidth - 4) {
                    details.append("[...]");
                    break;
                } else {
                    details.append("[").append(player.getName()).append("]");
                    playerCount++;
                }
            }
        }

        return details.toString();
    }

    // Display the status of a specific player
    public void displayPlayerStatus(Player player) {
        System.out.println("-----------------------------------------------------");
        System.out.println("Player: " + player.getName());
        System.out.println("Position: " + (player.getPosition() + 1));
        System.out.println("Money: " + player.getMoney() + " HKD");
        System.out.println("-----------------------------------------------------");
    }

    // Display a general message to the players
    public void showMessage(String message) {
        System.out.println(message);
    }
}
