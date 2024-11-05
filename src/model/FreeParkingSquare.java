package model;

public class FreeParkingSquare extends Square {
    public FreeParkingSquare() {
        super("Free Parking", SquareType.FREE_PARKING);
    }

    @Override
    public void landOn(Player player, Game game) {
        // No effect
    }
}
