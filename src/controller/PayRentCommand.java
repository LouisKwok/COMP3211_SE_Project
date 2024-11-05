package controller;

import model.Game;
import model.Player;
import model.Property;
import model.Square;
import view.GameView;

public class PayRentCommand implements Command {
    private Game game;
    private GameView view;

    public PayRentCommand(Game game, GameView view) {
        this.game = game;
        this.view = view;
    }

    @Override
    public void execute() {
        Player currentPlayer = game.getCurrentPlayer();
        Square currentSquare = game.getBoard().getSquare(currentPlayer.getPosition());

        if (currentSquare instanceof Property) {
            Property property = (Property) currentSquare;
            if (property.isOwned() && property.getOwner() != currentPlayer) {
                property.payRent(currentPlayer);
                view.displayMessage(currentPlayer.getName() + " paid rent of " + property.getRent() + " to " + property.getOwner().getName());
            } else {
                view.displayError("No rent payment needed on this square.");
            }
        } else {
            view.displayError("Current square is not a property.");
        }
    }
}
