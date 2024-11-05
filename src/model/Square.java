package model;

public abstract class Square {
    private String name;
    private SquareType type;

    public Square(String name, SquareType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public SquareType getType() {
        return type;
    }

    public abstract void landOn(Player player, Game game);
}
