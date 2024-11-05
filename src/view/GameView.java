package view;

import model.Game;

public interface GameView {
    void displayMessage(String message);
    void displayError(String error);
    void displayGameStatus(Game game);
}
