package controller;
import model.Player;
import model.Board;
import model.Square;
import model.Chance;
import model.IncomeTax;
import model.Jail;
import model.FreeParking;
import model.GoSquare;
import model.CommunityChest;
import view.GameView;

public class MoveCommand implements Command {
    private Player player;
    private Board board;
    private GameView view;

    public MoveCommand(Player player, Board board, GameView view) {
        this.player = player;
        this.board = board;
        this.view = view;
    }

    @Override
    public void execute() {
        int diceRoll = (int) (Math.random() * 4) + 1; // Rolling a 4-sided die
        int newPosition = (player.getPosition() + diceRoll) % board.getTotalSquares();
        player.setPosition(newPosition);
        view.displayMessage(player.getName() + " rolled a " + diceRoll + " and moved to position " + newPosition);

        // Handle the action of the square the player lands on
        Square currentSquare = board.getSquare(newPosition);
        if (currentSquare instanceof Chance) {
            ((Chance) currentSquare).applyEffect(player);
        } else if (currentSquare instanceof IncomeTax) {
            ((IncomeTax) currentSquare).applyTax(player);
        } else if (currentSquare instanceof Jail) {
            ((Jail) currentSquare).sendToJail(player);
        } else if (currentSquare instanceof FreeParking) {
            ((FreeParking) currentSquare).park();
        } else if (currentSquare instanceof GoSquare) {
            ((GoSquare) currentSquare).passGo(player);
        } else if (currentSquare instanceof CommunityChest) {
            ((CommunityChest) currentSquare).applyEffect(player);
        }
    }
}