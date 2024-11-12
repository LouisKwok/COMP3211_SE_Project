package model;

public class GoToJailSquare extends Square {

    public GoToJailSquare() {
        super("Go to Jail");
    }

    @Override
    public void action(Player player) {
        // Move the player to the "In Jail" position
        player.setPosition(6); // Assuming position 6 is "In Jail" based on your board layout
        player.setInJail(true);
        System.out.println(player.getName() + " is sent directly to jail!");
    }
}
