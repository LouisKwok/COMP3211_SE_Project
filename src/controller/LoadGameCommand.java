package controller;

import model.Game;
import view.GameView;

public class LoadGameCommand implements Command {
    private Game game;
    private GameView view;

    public LoadGameCommand(Game game, GameView view) {
        this.game = game;
        this.view = view;
    }

    @Override
    public void execute() {
        // Implement file I/O to load game state
        view.displayMessage("Game loaded successfully.");
    }
}
