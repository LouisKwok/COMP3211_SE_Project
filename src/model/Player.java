package model;
import java.util.ArrayList;

public class Player {
    private String name;
    private int money;
    private int position;
    private ArrayList<Property> properties;
    private boolean inJail;
    private boolean hasGetOutOfJailCard;

    public Player(String name, int startingMoney) {
        this.name = name;
        this.money = startingMoney;
        this.position = 0;
        this.properties = new ArrayList<>();
        this.inJail = false;
        this.hasGetOutOfJailCard = false;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void addProperty(Property property) {
        properties.add(property);
    }

    public boolean isInJail() {
        return inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public boolean hasGetOutOfJailCard() {
        return hasGetOutOfJailCard;
    }

    public void setHasGetOutOfJailCard(boolean hasGetOutOfJailCard) {
        this.hasGetOutOfJailCard = hasGetOutOfJailCard;
    }
}