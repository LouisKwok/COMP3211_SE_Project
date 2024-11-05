package controller;

import model.Game;
import view.GameView;

public class EndTurnCommand implements Command {
    private Game game;
    private GameView view;

    public EndTurnCommand(Game game, GameView view) {
        this.game = game;
        this.view = view;
    }

    @Override
    public void execute() {
        view.displayMessage(game.getCurrentPlayer().getName() + "'s turn has ended.");
    }
}
