package controller;

import model.Game;
import view.GameView;

public class CommandFactory {
    private Game game;
    private GameView view;

    public CommandFactory(Game game, GameView view) {
        this.game = game;
        this.view = view;
    }

    public Command createCommand(String commandName) {
        switch (commandName.toLowerCase()) {
            case "roll":
                return new RollDiceCommand(game, view);
            case "buy":
                return new BuyPropertyCommand(game, view);
            case "pay rent":
                return new PayRentCommand(game, view);
            case "save":
                return new SaveGameCommand(game, view);
            case "load":
                return new LoadGameCommand(game, view);
            case "end turn":
                return new EndTurnCommand(game, view);
            default:
                return null;
        }
    }
}
