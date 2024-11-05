package view;
import model.Player;
import java.util.List;

public class GameView {
    public void displayPlayerStatus(Player player) {
        System.out.println("Player: " + player.getName() + " | Money: " + player.getMoney() + " | Position: " + player.getPosition());
    }

    public void displayAllPlayers(List<Player> players) {
        for (Player player : players) {
            displayPlayerStatus(player);
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}