package model;

import java.util.Random;

public class ChanceSquare extends Square {
    private Random random;

    public ChanceSquare() {
        super("Chance");
        random = new Random();
    }

    @Override
    public void action(Player player) {
        int amount = (random.nextInt(50) + 1) * 10; // Random multiple of 10 up to 500
        boolean gain = random.nextBoolean();

        if (gain) {
            player.updateMoney(amount);
            System.out.println(player.getName() + " gains HKD " + amount + " from Chance.");
        } else {
            player.updateMoney(-amount);
            System.out.println(player.getName() + " loses HKD " + amount + " from Chance.");
        }
    }
}
