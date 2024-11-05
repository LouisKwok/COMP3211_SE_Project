package controller;
import model.Player;
import model.Property;
import view.GameView;

public class BuyPropertyCommand implements Command {
    private Player player;
    private Property property;
    private GameView view;

    public BuyPropertyCommand(Player player, Property property, GameView view) {
        this.player = player;
        this.property = property;
        this.view = view;
    }

    @Override
    public void execute() {
        if (property.getOwner() == null && player.getMoney() >= property.getPrice()) {
            player.setMoney(player.getMoney() - property.getPrice());
            property.setOwner(player);
            player.addProperty(property);
            view.displayMessage(player.getName() + " bought " + property.getName() + " for HKD " + property.getPrice());
        } else if (property.getOwner() != null) {
            view.displayMessage("Property is already owned by " + property.getOwner().getName());
        } else {
            view.displayMessage(player.getName() + " does not have enough money to buy " + property.getName());
        }
    }
}