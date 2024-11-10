package model;

public class GoJailSquare extends Square {

    public GoJailSquare() {
        super("Go to Jail");
    }

    @Override
    public void action(Player player) {
        player.setInJail(true); // Moves the player to jail status
        player.setPosition(6);  // Assuming position 6 is the "In Jail" position
        System.out.println(player.getName() + " goes to jail!");
    }
}
