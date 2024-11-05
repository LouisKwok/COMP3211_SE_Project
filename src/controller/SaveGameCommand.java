package controller;

import model.Game;
import view.GameView;

public class SaveGameCommand implements Command {
    private Game game;
    private GameView view;

    public SaveGameCommand(Game game, GameView view) {
        this.game = game;
        this.view = view;
    }

    @Override
    public void execute() {
        // Implement file I/O to save game state
        view.displayMessage("Game saved successfully.");
    }
}
