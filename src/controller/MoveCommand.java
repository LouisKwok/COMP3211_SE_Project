package controller;

import model.Game;
import model.Player;
import model.Dice;
import model.Square;
import view.GameView;

public class MoveCommand implements Command {
    private Game game;
    private GameView view;
    private Dice dice;

    public MoveCommand(Game game, GameView view) {
        this.game = game;
        this.view = view;
        this.dice = new Dice(); // Initialize the dice for rolling
    }

    @Override
    public void execute() {
        Player currentPlayer = game.getCurrentPlayer();

        // Roll the dice to determine the number of moves
        int roll = dice.roll() + dice.roll(); // Simulate two dice rolls
        view.displayMessage(currentPlayer.getName() + " rolled a total of " + roll);

        // Move the player
        currentPlayer.move(roll);

        // Get the new position and display it
        int newPosition = currentPlayer.getPosition();
        view.displayMessage(currentPlayer.getName() + " moved to position " + newPosition);

        // Process the square the player landed on
        Square currentSquare = game.getBoard().getSquare(newPosition);
        currentSquare.landOn(currentPlayer, game);

        // Display the outcome of landing on the square
        switch (currentSquare.getType()) {
            case PROPERTY:
                view.displayMessage(currentPlayer.getName() + " landed on " + currentSquare.getName() + " (Property).");
                break;
            case GO:
                view.displayMessage(currentPlayer.getName() + " landed on Go and collected salary.");
                break;
            case CHANCE:
                view.displayMessage(currentPlayer.getName() + " landed on Chance and received a random event.");
                break;
            case INCOME_TAX:
                view.displayMessage(currentPlayer.getName() + " landed on Income Tax and paid tax.");
                break;
            case FREE_PARKING:
                view.displayMessage(currentPlayer.getName() + " landed on Free Parking. Nothing happens.");
                break;
            case GO_TO_JAIL:
                view.displayMessage(currentPlayer.getName() + " landed on Go To Jail and is now in Jail.");
                break;
            case JAIL:
                if (currentPlayer.isInJail()) {
                    view.displayMessage(currentPlayer.getName() + " is in Jail.");
                } else {
                    view.displayMessage(currentPlayer.getName() + " is just visiting Jail.");
                }
                break;
            default:
                view.displayMessage(currentPlayer.getName() + " landed on " + currentSquare.getName());
                break;
        }
    }
}
