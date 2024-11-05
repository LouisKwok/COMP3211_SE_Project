package model;

public class IncomeTaxSquare extends Square {
    private static final double TAX_RATE = 0.1; // 10% tax rate

    public IncomeTaxSquare() {
        super("Income Tax", SquareType.INCOME_TAX);
    }

    // Calculates the tax based on the player's current money
    public int calculateTax(Player player) {
        return (int) Math.max(player.getMoney() * TAX_RATE, 10); // Minimum tax of 10 if 10% is less than 10
    }

    @Override
    public void landOn(Player player, Game game) {
        int tax = calculateTax(player);
        player.deductMoney(tax);
        System.out.println(player.getName() + " paid " + tax + " as Income Tax.");
    }
}
