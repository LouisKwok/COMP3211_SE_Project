package model;

public class JailSquare extends Square {
    public JailSquare() {
        super("In Jail/Just Visiting", SquareType.JAIL);
    }

    @Override
    public void landOn(Player player, Game game) {
        if (!player.isInJail()) {
            // Just visiting
        }
    }
}
