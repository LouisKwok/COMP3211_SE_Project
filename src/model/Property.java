package model;

public class Property extends Square {
    private int price;
    private int rent;
    private Player owner;

    public Property(String name, int price, int rent) {
        super(name, SquareType.PROPERTY);
        this.price = price;
        this.rent = rent;
        this.owner = null;
    }

    public int getPrice() {
        return price;
    }

    public int getRent() {
        return rent;
    }

    public Player getOwner() {
        return owner;
    }

    public boolean isOwned() {
        return owner != null;
    }

    public void buyProperty(Player player) {
        if (!isOwned() && player.canAfford(price)) {
            player.deductMoney(price);
            owner = player;
            player.addProperty(this);
        }
    }

    public void payRent(Player player) {
        if (isOwned() && owner != player) {
            player.deductMoney(rent);
            owner.addMoney(rent);
        }
    }

    @Override
    public void landOn(Player player, Game game) {
        if (isOwned()) {
            payRent(player);
        } else {
            // Optionally: Prompt player to buy this property
        }
    }
}
