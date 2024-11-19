package model;

public class IncomeTaxSquare extends Square {

    public IncomeTaxSquare() {
        super("Income Tax");
    }

    @Override
    public void action(Player player) {
        // Deduct 10% of the player's money
        int tax = (int) (player.getMoney() * 0.1);
        player.updateMoney(-tax); // Deduct the tax
        System.out.println(player.getName() + " pays HKD " + tax + " as income tax.");
    }

}
