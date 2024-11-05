package controller;

import model.*;
import view.GameView;

import java.util.Scanner;

public class GameController {
    private Game game;
    private GameView view;
    private Scanner scanner;

    public GameController(Game game, GameView view) {
        this.game = game;
        this.view = view;
        this.scanner = new Scanner(System.in); // For interactive prompts
    }

    public void startGame() {
        view.displayMessage("Welcome to Monopoly!");

        // Main game loop - will continue until the game ends
        while (!game.isGameOver()) {
            Player currentPlayer = game.getCurrentPlayer();
            view.displayMessage("\n=== " + currentPlayer.getName() + "'s turn ===");

            // Display the current game status
            view.displayGameStatus(game);

            // Player rolls the dice
            rollDiceAndMove(currentPlayer);

            // Check the square the player landed on and handle actions
            handleSquareLanding(currentPlayer);

            // End the current player's turn and move to the next player
            game.nextTurn();

            view.displayMessage("=== End of " + currentPlayer.getName() + "'s turn ===\n");
        }

        view.displayMessage("Game Over!");
    }

    // Roll the dice and move the player
    private void rollDiceAndMove(Player currentPlayer) {
        // Roll the dice and calculate the total roll
        int rollResult = rollDice();
        view.displayMessage(currentPlayer.getName() + " rolled a " + rollResult);

        // Move the player based on the roll result
        currentPlayer.move(rollResult);
        view.displayMessage(currentPlayer.getName() + " moved to position " + currentPlayer.getPosition());
    }

    // Rolls two 4-sided dice and returns the total result
    private int rollDice() {
        int dice1 = (int) (Math.random() * 4) + 1; // Simulating a 4-sided dice roll
        int dice2 = (int) (Math.random() * 4) + 1;
        return dice1 + dice2;
    }

    // Handle actions when a player lands on a square
    private void handleSquareLanding(Player player) {
        Square currentSquare = game.getBoard().getSquare(player.getPosition());

        // Check the type of square and perform the corresponding action
        if (currentSquare instanceof Property) {
            handlePropertySquare(player, (Property) currentSquare);
        } else if (currentSquare instanceof ChanceSquare) {
            handleChanceSquare(player, (ChanceSquare) currentSquare);
        } else if (currentSquare instanceof GoToJailSquare) {
            handleGoToJailSquare(player);
        } else if (currentSquare instanceof IncomeTaxSquare) {
            handleIncomeTaxSquare(player, (IncomeTaxSquare) currentSquare);
        } else if (currentSquare instanceof FreeParkingSquare) {
            handleFreeParkingSquare();
        } else if (currentSquare instanceof JailSquare) {
            handleJailSquare(player);
        }
    }

    // Handle landing on a property square
    private void handlePropertySquare(Player player, Property property) {
        if (!property.isOwned()) {
            // Prompt the player to buy the property if it’s unowned
            view.displayMessage(player.getName() + " landed on " + property.getName() + ", which costs " + property.getPrice());
            view.displayMessage("Would you like to buy this property? (yes/no)");

            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("yes") && player.getMoney() >= property.getPrice()) {
                property.buyProperty(player);
                view.displayMessage(player.getName() + " bought " + property.getName());
            } else {
                view.displayMessage(player.getName() + " chose not to buy " + property.getName() + " or doesn't have enough money.");
            }
        } else if (property.getOwner() != player) {
            // If the property is owned by another player, pay rent
            view.displayMessage(player.getName() + " landed on " + property.getName() + ", owned by " + property.getOwner().getName());
            property.payRent(player);
            view.displayMessage(player.getName() + " paid rent of " + property.getRent() + " to " + property.getOwner().getName());
        }
    }

    // Handle landing on a Chance square
    private void handleChanceSquare(Player player, ChanceSquare chanceSquare) {
        view.displayMessage(player.getName() + " landed on a Chance square.");
        chanceSquare.applyChanceEffect(player);
        view.displayMessage(player.getName() + "'s new balance is: " + player.getMoney());
    }

    // Handle landing on a Go To Jail square
    private void handleGoToJailSquare(Player player) {
        view.displayMessage(player.getName() + " landed on Go To Jail and is now being sent to Jail.");
        player.setPosition(9); // Assuming 9 is the Jail square position
        player.setInJail(true);
    }

    // Handle landing on an Income Tax square
    private void handleIncomeTaxSquare(Player player, IncomeTaxSquare incomeTaxSquare) {
        int taxAmount = incomeTaxSquare.calculateTax(player);
        player.deductMoney(taxAmount);
        view.displayMessage(player.getName() + " landed on Income Tax and paid " + taxAmount + ". New balance: " + player.getMoney());
    }

    // Handle landing on Free Parking
    private void handleFreeParkingSquare() {
        view.displayMessage("Free Parking! Nothing happens.");
    }

    // Handle landing on Jail / Just Visiting
    private void handleJailSquare(Player player) {
        if (player.isInJail()) {
            view.displayMessage(player.getName() + " is in Jail.");
        } else {
            view.displayMessage(player.getName() + " is just visiting Jail.");
        }
    }
}
