package model;

public class FreeParkingSquare extends Square {

    public FreeParkingSquare() {
        super("Free Parking");
    }

    @Override
    public void action(Player player) {
        System.out.println(player.getName() + " lands on Free Parking. Nothing happens.");
    }
}
