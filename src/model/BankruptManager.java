package model;
public class BankruptManager {
    public boolean isBankrupt(Player player) {
        return player.getMoney() < 0;
    }

    public void declareBankruptcy(Player player) {
        if (isBankrupt(player)) {
            System.out.println(player.getName() + " is bankrupt and out of the game.");
        }
    }
}