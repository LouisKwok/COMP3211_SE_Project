package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int money;
    private int position;
    private List<Property> properties;
    private boolean inJail;
    private InJailState jailState;

    public Player(String name, int initialMoney) {
        this.name = name;
        this.money = initialMoney;
        this.position = 0;
        this.properties = new ArrayList<>();
        this.inJail = false;
        this.jailState = new InJailState();
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isInJail() {
        return inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public InJailState getJailState() {
        return jailState;
    }

    public void addProperty(Property property) {
        properties.add(property);
    }

    public void deductMoney(int amount) {
        money -= amount;
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public void move(int steps) {
        position = (position + steps) % 20; // Assuming a 20-square board
    }

    public List<Property> getProperties() {
        return properties;
    }

    public boolean canAfford(int amount) {
        return money >= amount;
    }
}
