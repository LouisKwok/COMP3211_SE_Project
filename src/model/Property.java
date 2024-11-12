package model;

import java.util.Scanner;

public class Property extends Square {
    private int price;
    private int rent;
    private Player owner;

    public Property(String name, int price, int rent) {
        super(name);
        this.price = price;
        this.rent = rent;
        this.owner = null;
    }

    // Getter methods
    public int getPrice() {
        return price;
    }

    public int getRent() {
        return rent;
    }

    public Player getOwner() {
        return owner;
    }

    // Setter methods
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    // Use the setName method from the Square superclass to modify the property name
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void action(Player player) {
        if (owner == null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(player.getName() + " lands on " + name + ". The property is available for HKD " + price + ".");
            System.out.print("Would you like to buy this property? (yes/no): ");
            String response = scanner.next().toLowerCase();

            if (response.equals("yes")) {
                if (player.getMoney() >= price) {
                    player.updateMoney(-price);
                    this.setOwner(player);
                    System.out.println(player.getName() + " buys " + name + " for HKD " + price + ".");
                } else {
                    System.out.println(player.getName() + " does not have enough money to buy " + name + ".");
                }
            } else {
                System.out.println(player.getName() + " chose not to buy " + name + ".");
            }
        } else if (owner != player) {
            System.out.println(player.getName() + " lands on " + name + " which is owned by " + owner.getName() + ". Rent is HKD " + rent + ".");
            if (player.getMoney() >= rent) {
                player.updateMoney(-rent);
                owner.updateMoney(rent);
                System.out.println(player.getName() + " pays HKD " + rent + " to " + owner.getName() + ".");
            } else {
                System.out.println(player.getName() + " does not have enough money to pay rent and is bankrupt.");
                player.updateMoney(-player.getMoney()); // Set player's money to 0 (bankrupt)
            }
        } else {
            System.out.println(player.getName() + " lands on their own property: " + name + ".");
        }
    }
}
