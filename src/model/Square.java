package model;

public abstract class Square {
    protected String name;

    public Square(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Abstract method to be implemented by all subclasses
    public abstract void action(Player player);
}
