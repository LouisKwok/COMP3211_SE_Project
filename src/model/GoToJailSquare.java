package model;

public class GoToJailSquare extends Square {

    private Board board;

    // Constructor accepts a reference to the board to allow lookup of the InJailSquare
    public GoToJailSquare(Board board) {
        super("Go to Jail");
        this.board = board;
    }

    @Override
    public void action(Player player) {
        // Find the "In Jail" square's position on the board
        int inJailPosition = findInJailPosition();

        if (inJailPosition != -1) {
            player.setPosition(inJailPosition);
            player.setInJail(true);
            System.out.println(player.getName() + " is sent directly to jail!");
        } else {
            System.out.println("Error: Could not find the 'In Jail' square on the board.");
        }
    }

    // Method to find the "In Jail" square's position on the board
    private int findInJailPosition() {
        for (int i = 0; i < board.getSquares().size(); i++) {
            Square square = board.getSquares().get(i);
            if (square instanceof InJailOrVisitingSquare) {
                return i;
            }
        }
        return -1; // Returns -1 if no "In Jail" square is found
    }
}
