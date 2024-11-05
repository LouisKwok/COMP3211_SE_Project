package model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Square> squares;

    public Board() {
        squares = new ArrayList<>();
        initializeBoard();
    }

    // Initializes the board with different types of squares based on the provided layout
    private void initializeBoard() {
        // Adding squares in the order as per the image provided
        squares.add(new GoSquare()); // Position 0 (Go)
        squares.add(new Property("Central", 800, 90)); // Position 1
        squares.add(new Property("Wan Chai", 700, 65)); // Position 2
        squares.add(new IncomeTaxSquare()); // Position 3 (Income Tax)
        squares.add(new Property("Stanley", 600, 60)); // Position 4
        squares.add(new ChanceSquare()); // Position 5 (Chance)
        squares.add(new Property("Shek O", 400, 10)); // Position 6
        squares.add(new Property("Mong Kok", 500, 40)); // Position 7
        squares.add(new ChanceSquare()); // Position 8 (Chance)
        squares.add(new Property("Tsing Yi", 400, 15)); // Position 9
        squares.add(new FreeParkingSquare()); // Position 10 (Free Parking)
        squares.add(new Property("Shatin", 700, 75)); // Position 11
        squares.add(new ChanceSquare()); // Position 12 (Chance)
        squares.add(new Property("Tuen Mun", 400, 20)); // Position 13
        squares.add(new Property("Tai Po", 500, 25)); // Position 14
        squares.add(new GoToJailSquare()); // Position 15 (Go to Jail)
        squares.add(new Property("Sai Kung", 400, 10)); // Position 16
        squares.add(new Property("Yuen Long", 400, 25)); // Position 17
        squares.add(new ChanceSquare()); // Position 18 (Chance)
        squares.add(new Property("Tai O", 600, 25)); // Position 19
    }

    // Method to retrieve a square at a specific position
    public Square getSquare(int position) {
        // Ensure the position is within the bounds of the board
        return squares.get(position % squares.size());
    }
}
