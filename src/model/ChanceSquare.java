package model;

import java.util.Random;

public class ChanceSquare extends Square {
    private Random random;

    public ChanceSquare() {
        super("Chance", SquareType.CHANCE);
        this.random = new Random();
    }

    // Applies a random effect on the player (either gain or lose money)
    public void applyChanceEffect(Player player) {
        // Randomly decide if the effect is positive or negative
        boolean isPositiveEffect = random.nextBoolean();
        int amount = (random.nextInt(20) + 1) * 10; // Random amount between 10 and 200

        if (isPositiveEffect) {
            player.addMoney(amount);
            System.out.println(player.getName() + " gains " + amount + " from Chance.");
        } else {
            player.deductMoney(amount);
            System.out.println(player.getName() + " loses " + amount + " from Chance.");
        }
    }

    @Override
    public void landOn(Player player, Game game) {
        applyChanceEffect(player);
    }
}
