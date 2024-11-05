package model;

public class JailManager {
    public void handleJail(Player player) {
        if (player.isInJail()) {
            System.out.println(player.getName() + " is in Jail and cannot move until released.");
        }
    }
}