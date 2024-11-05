package model;
import java.util.Random;

public class Chance extends Square {
    public Chance(String name) {
        super(name);
    }

    public void applyEffect(Player player) {
        Random rand = new Random();
        int effect = rand.nextInt(2);
        int amount = (rand.nextInt(20) + 1) * 10;
        if (effect == 0) {
            player.setMoney(player.getMoney() + amount);
            System.out.println(player.getName() + " gained HKD " + amount + " from Chance!");
        } else {
            player.setMoney(player.getMoney() - amount);
            System.out.println(player.getName() + " lost HKD " + amount + " from Chance!");
        }
    }
}
