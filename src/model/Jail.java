package model;

public class Jail extends Square {
    public Jail(String name) {
        super(name);
    }

    public void sendToJail(Player player) {
        player.setInJail(true);
        System.out.println(player.getName() + " is sent to Jail!");
    }
}