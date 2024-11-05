package model;

public class GoToJailSquare extends Square {
    public GoToJailSquare() {
        super("Go to Jail", SquareType.GO_TO_JAIL);
    }

    @Override
    public void landOn(Player player, Game game) {
        player.setInJail(true);
        player.setPosition(10); // Send to Jail square position
    }
}
