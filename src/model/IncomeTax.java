package model;

public class IncomeTax extends Square {
    public IncomeTax(String name) {
        super(name);
    }

    public void applyTax(Player player) {
        int tax = (int) (player.getMoney() * 0.1);
        tax = (tax / 10) * 10; // Round down to nearest multiple of 10
        player.setMoney(player.getMoney() - tax);
        System.out.println(player.getName() + " paid HKD " + tax + " as income tax.");
    }
}