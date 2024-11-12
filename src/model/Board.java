package model;

import java.util.List;
import java.util.ArrayList;

public class Board {
    private List<Square> squares;

    // Constructor to initialize the board with squares
    public Board() {
        initializeBoard();
    }

    // Method to initialize the squares on the board
    private void initializeBoard() {
        squares = new ArrayList<>();

        // Define the squares on the board, in the same order as shown in the image
        squares.add(new GoSquare());                 // 1. GO
        squares.add(new Property("Central", 800, 80));  // 2. Central
        squares.add(new Property("Wan Chai", 700, 70)); // 3. Wan Chai
        squares.add(new IncomeTaxSquare());          // 4. Income Tax 10%
        squares.add(new Property("Stanley", 600, 60));  // 5. Stanley
        squares.add(new InJailOrVisitingSquare());   // 6. Just Visiting / In Jail
        squares.add(new Property("Shek O", 400, 40));   // 7. Shek O
        squares.add(new Property("Mong Kok", 500, 50)); // 8. Mong Kok
        squares.add(new ChanceSquare());             // 9. Chance
        squares.add(new Property("Tsing Yi", 400, 40)); // 10. Tsing Yi
        squares.add(new FreeParkingSquare());        // 11. Free Parking
        squares.add(new Property("Shatin", 700, 70));   // 12. Shatin
        squares.add(new ChanceSquare());             // 13. Chance
        squares.add(new Property("Tuen Mun", 400, 40)); // 14. Tuen Mun
        squares.add(new Property("Tai Po", 500, 50));   // 15. Tai Po
        squares.add(new GoToJailSquare());           // 16. Go To Jail
        squares.add(new Property("Sai Kung", 400, 40));  // 17. Sai Kung
        squares.add(new Property("Yuen Long", 400, 40)); // 18. Yuen Long
        squares.add(new ChanceSquare());             // 19. Chance
        squares.add(new Property("Tai O", 600, 60));    // 20. Tai O
    }

    // Method to get a square based on the position
    public Square getSquare(int position) {
        return squares.get(position);
    }

    // Method to get all squares
    public List<Square> getSquares() {
        return squares;
    }
}
