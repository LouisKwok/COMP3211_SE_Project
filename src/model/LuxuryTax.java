package model;

public class LuxuryTax extends Square {
    public LuxuryTax(String name) {
        super(name);
    }

    public void applyTax(Player player) {
        player.setMoney(player.getMoney() - 100);
        System.out.println(player.getName() + " paid HKD 100 as luxury tax.");
    }
}