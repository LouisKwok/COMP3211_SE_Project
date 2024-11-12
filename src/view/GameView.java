package view;

import model.Board;
import model.Player;

import java.util.List;

public class GameView {

    // Display a large, ASCII-style representation of the Monopoly board with numbered positions
    public void displayBoardLarge(Board board, List<Player> players) {
        System.out.println("+---------------------+---------------------+---------------------+---------------------+------------------------------------------+");
        System.out.println("| 11. Free Parking    | 12. Shatin          | 13. ? CHANCE        | 14. Tuen Mun        | 15. Tai Po          | 16. Go To Jail     |");
        System.out.println("|                     | HKD 700             |                     | HKD 400             | HKD 500             |                    |");
        System.out.println("+---------------------+---------------------+---------------------+---------------------+---------------------+--------------------+");
        System.out.println("| 10. Tsing Yi        |                                                                                       | 17. Sai Kung       |");
        System.out.println("| HKD 400             |                                                                                       | HKD 400            |");
        System.out.println("+---------------------+                                                                                       +--------------------+");
        System.out.println("| 9. ? CHANCE         |                                                                                       | 18. Yuen Long      |");
        System.out.println("|                     |                                                                                       | HKD 400            |");
        System.out.println("+---------------------+                                                                                       +--------------------+");
        System.out.println("| 8. Mong Kok         |                                                                                       | 19. ? CHANCE       |");
        System.out.println("| HKD 500             |                                                                                       |                    |");
        System.out.println("+---------------------+                                                                                       +--------------------+");
        System.out.println("| 7. Shek O           |                                                                                       | 20. Tai O          |");
        System.out.println("| HKD 400             |                                                                                       | HKD 600            |");
        System.out.println("+---------------------+---------------------+---------------------+---------------------+------------------------------------------+");
        System.out.println("| 6. Visiting         | 5. Stanley          | 4. Income Tax 10%   | 3. Wan Chai         | 2. Central          | 1. Go              |");
        System.out.println("| (In Jail)           | HKD 600             |                     | HKD 700             | HKD 800             |                    |");
        System.out.println("+---------------------+---------------------+---------------------+---------------------+------------------------------------------+");


        // Display Players' Positions
        System.out.println("Players' Positions:");
        for (Player player : players) {
            System.out.println(player.getName() + " is on square " + (player.getPosition() + 1));
        }
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
