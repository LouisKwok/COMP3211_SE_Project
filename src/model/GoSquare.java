package model;

public class GoSquare extends Square {
    public GoSquare() {
        super("Go", SquareType.GO);
    }

    @Override
    public void landOn(Player player, Game game) {
        player.addMoney(1500); // Collect salary
    }
}
