package model;

public class BasicSquare extends Square {

    public BasicSquare(String name) {
        super(name);
    }

    @Override
    public void action(Player player) {
        // 此方格沒有特殊行為
        System.out.println(player.getName() + " landed on " + getName() + ".");
    }
}
