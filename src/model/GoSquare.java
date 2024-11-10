package model;

public class GoSquare extends Square {

    public GoSquare() {
        super("Go");
    }

    @Override
    public void action(Player player) {
        player.updateMoney(1500);
        System.out.println(player.getName() + " passes Go and receives HKD 1500.");
    }
}
