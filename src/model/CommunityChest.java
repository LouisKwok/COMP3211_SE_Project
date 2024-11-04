package model;
import java.util.Random;

public class CommunityChest extends Square {
    public CommunityChest(String name) {
        super(name);
    }

    public void applyEffect(Player player) {
        Random rand = new Random();
        int effect = rand.nextInt(2);
        int amount = (rand.nextInt(30) + 1) * 10;
        if (effect == 0) {
            player.setMoney(player.getMoney() + amount);
            System.out.println(player.getName() + " gained HKD " + amount + " from Community Chest!");
        } else {
            player.setMoney(player.getMoney() - amount);
            System.out.println(player.getName() + " lost HKD " + amount + " from Community Chest!");
        }
    }
}
