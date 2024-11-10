package model;

import java.util.ArrayList;

public class Board {
    private ArrayList<Square> squares;

    public Board() {
        squares = new ArrayList<>();
        initializeBoard();
    }

    private void initializeBoard() {
        squares.add(new GoSquare()); // Position 1: Go
        squares.add(new Property("Central", 800, 90)); // Position 2
        squares.add(new Property("Wan Chai", 700, 65)); // Position 3
        squares.add(new IncomeTaxSquare()); // Position 4: Income Tax
        squares.add(new Property("Stanley", 600, 60)); // Position 5
        squares.add(new InJailOrVisitingSquare()); // Position 6: Just Visiting / In Jail
        squares.add(new Property("Shek O", 400, 10)); // Position 7
        squares.add(new ChanceSquare()); // Position 8: Chance
        squares.add(new Property("Mong Kok", 500, 40)); // Position 9
        squares.add(new FreeParkingSquare()); // Position 10: Free Parking
        squares.add(new Property("Tsing Yi", 400, 15)); // Position 11
        squares.add(new ChanceSquare()); // Position 12: Chance
        squares.add(new Property("Shatin", 700, 75)); // Position 13
        squares.add(new GoJailSquare()); // Position 14: Go to Jail
        squares.add(new Property("Tuen Mun", 400, 20)); // Position 15
        squares.add(new Property("Tai Po", 500, 25)); // Position 16
        squares.add(new ChanceSquare()); // Position 17: Chance
        squares.add(new Property("Sai Kung", 400, 10)); // Position 18
        squares.add(new Property("Yuen Long", 400, 25)); // Position 19
        squares.add(new Property("Tai O", 600, 25)); // Position 20
    }

    public Square getSquare(int position) {
        return squares.get(position % squares.size());
    }
}
