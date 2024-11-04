package model;
import java.util.ArrayList;

public class Board {
    private ArrayList<Square> squares;

    public Board() {
        squares = new ArrayList<>();
        initializeBoard();
    }

    private void initializeBoard() {
        squares.add(new GoSquare("GO"));
        squares.add(new Property("Central", 800, 90));
        squares.add(new Property("Wan Chai", 700, 65));
        squares.add(new Chance("Chance 1"));
        squares.add(new Property("Stanley", 600, 60));
        squares.add(new IncomeTax("Income Tax"));
        squares.add(new Property("Shek O", 400, 10));
        squares.add(new Jail("Go to Jail"));
        squares.add(new FreeParking("Free Parking"));
        squares.add(new Property("Mong Kok", 500, 40));
        squares.add(new Chance("Chance 2"));
        squares.add(new Property("Tsing Yi", 400, 15));
        squares.add(new Property("Shatin", 700, 75));
        squares.add(new Property("Tuen Mun", 400, 20));
        squares.add(new Property("Tai Po", 500, 25));
        squares.add(new Property("Sai Kung", 400, 10));
        squares.add(new Property("Yuen Long", 400, 25));
        squares.add(new Property("Tai O", 600, 25));
        squares.add(new CommunityChest("Community Chest 1"));
        squares.add(new LuxuryTax("Luxury Tax"));
    }

    public Square getSquare(int position) {
        return squares.get(position);
    }

    public int getTotalSquares() {
        return squares.size();
    }
}
