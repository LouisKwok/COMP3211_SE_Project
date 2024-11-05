package controller;
import model.Player;
import model.Board;
import model.Property;
import view.GameView;

public class CommandFactory {
    public static Command createCommand(String commandType, Player player, Board board, GameView view) {
        switch (commandType.toLowerCase()) {
            case "move":
                return new MoveCommand(player, board, view);
            case "buy":
                Property property = (Property) board.getSquare(player.getPosition());
                return new BuyPropertyCommand(player, property, view);
            // Additional commands can be added here
            default:
                throw new IllegalArgumentException("Invalid command type");
        }
    }
}