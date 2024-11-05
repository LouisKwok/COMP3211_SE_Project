package controller;

import model.Game;
import model.Player;
import model.Dice;
import view.GameView;

public class RollDiceCommand implements Command {
    private Game game;
    private GameView view;
    private Dice dice;

    public RollDiceCommand(Game game, GameView view) {
        this.game = game;
        this.view = view;
        this.dice = new Dice();
    }

    @Override
    public void execute() {
        Player currentPlayer = game.getCurrentPlayer();
        int roll = dice.roll() + dice.roll();
        currentPlayer.move(roll);
        view.displayMessage(currentPlayer.getName() + " rolled a " + roll);
        game.handlePlayerLanding(currentPlayer);

        if (currentPlayer.isInJail()) {
            view.displayMessage(currentPlayer.getName() + " is in Jail.");
        }
    }
}
