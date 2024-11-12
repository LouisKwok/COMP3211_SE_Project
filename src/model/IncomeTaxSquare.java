package model;

public class IncomeTaxSquare extends Square {

    public IncomeTaxSquare() {
        super("Income Tax");
    }

    @Override
    public void action(Player player) {
        int taxAmount = player.getMoney() / 10;
        player.updateMoney(-taxAmount);
        System.out.println(player.getName() + " pays HKD " + taxAmount + " as income tax.");
    }
}
