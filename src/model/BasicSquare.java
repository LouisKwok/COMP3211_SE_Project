package model;

public class BasicSquare extends Square {

    public BasicSquare(String name) {
        super(name);
    }

    @Override
    public void action(Player player) {

        System.out.println(player.getName() + " landed on " + getName() + ".");
    }
}
