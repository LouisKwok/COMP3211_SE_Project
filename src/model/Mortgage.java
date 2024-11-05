package model;

public class Mortgage {
    public void mortgageProperty(Property property, Player player) {
        if (property.getOwner() == player) {
            int mortgageValue = property.getPrice() / 2;
            player.setMoney(player.getMoney() + mortgageValue);
            System.out.println(player.getName() + " mortgaged " + property.getName() + " for HKD " + mortgageValue);
        } else {
            System.out.println("Property not owned by player. Cannot mortgage.");
        }
    }
}