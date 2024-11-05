package model;

public class TitleDeed {
    private Property property;
    private Player owner;

    public TitleDeed(Property property, Player owner) {
        this.property = property;
        this.owner = owner;
    }

    public Property getProperty() {
        return property;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
