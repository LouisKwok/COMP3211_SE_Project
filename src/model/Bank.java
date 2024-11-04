package model;

public class Bank {
    public void transferMoney(Player from, Player to, int amount) {
        if (from.getMoney() >= amount) {
            from.setMoney(from.getMoney() - amount);
            to.setMoney(to.getMoney() + amount);
            System.out.println("Transferred HKD " + amount + " from " + from.getName() + " to " + to.getName());
        } else {
            System.out.println(from.getName() + " does not have enough money to transfer HKD " + amount);
        }
    }

    public void payFine(Player player, int amount) {
        player.setMoney(player.getMoney() - amount);
        System.out.println(player.getName() + " paid a fine of HKD " + amount);
    }
}