package controller;

import model.Board;
import model.Dice;
import view.GameView;

public class Main {
    public static void main(String[] args) {
        // Initialize game components
        Board board = new Board();
        Dice dice = new Dice();
        GameView view = new GameView();

        // Create the game controller, which will handle player initialization
        GameController gameController = new GameController(board, dice, view);

        // Start the game
        gameController.startGame();
    }
}
