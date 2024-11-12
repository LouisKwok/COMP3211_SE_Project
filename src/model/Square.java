package model;

public abstract class Square {
    protected String name;

    // Constructor to initialize the name of the square
    public Square(String name) {
        this.name = name;
    }

    // Getter method for name
    public String getName() {
        return name;
    }

    // Add setter method for name
    public void setName(String name) {
        this.name = name;
    }

    // Abstract method for the action when a player lands on this square
    public abstract void action(Player player);
}
