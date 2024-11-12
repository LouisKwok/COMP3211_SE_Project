package controller;

import model.Board;
import model.Property;
import model.Square;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class BoardController {
    private Board board;

    public BoardController(Board board) {
        this.board = board;
    }

    // Method to modify the properties of the gameboard
    public void modifyPropertySquares() {
        Scanner scanner = new Scanner(System.in);
        List<Square> squares = board.getSquares();

        while (true) {
            System.out.println("Gameboard Designer: Modify Property Squares");
            System.out.println("Select a property to modify by entering the corresponding number, or enter 0 to exit:");

            // Display all property squares
            for (int i = 0; i < squares.size(); i++) {
                Square square = squares.get(i);
                if (square instanceof Property) {
                    Property property = (Property) square;
                    System.out.println((i + 1) + ". " + property.getName() + " (Price: HKD " + property.getPrice() + ", Rent: HKD " + property.getRent() + ")");
                }
            }

            // Get designer input
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 0) {
                    break; // Exit if 0 is entered
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number corresponding to the property.");
                continue;
            }

            if (choice > 0 && choice <= squares.size() && squares.get(choice - 1) instanceof Property) {
                Property property = (Property) squares.get(choice - 1);

                // Prompt for new property details
                System.out.print("Enter new name for property (" + property.getName() + "): ");
                String newName = scanner.nextLine();

                System.out.print("Enter new price for property (current price: HKD " + property.getPrice() + "): ");
                int newPrice;
                try {
                    newPrice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Price must be an integer.");
                    continue;
                }

                System.out.print("Enter new rent for property (current rent: HKD " + property.getRent() + "): ");
                int newRent;
                try {
                    newRent = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Rent must be an integer.");
                    continue;
                }

                // Update property attributes
                property.setName(newName);
                property.setPrice(newPrice);
                property.setRent(newRent);

                System.out.println("Property updated successfully!");
            } else {
                System.out.println("Invalid property selection. Please try again.");
            }
        }
    }
    // Method to initialize the board based on user choice
    public Board initializeBoard() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Would you like to load an existing gameboard or use the default one? (load/default): ");
            String choice = scanner.nextLine().toLowerCase();

            if (choice.equals("load")) {
                return loadBoardFromFile();
            } else if (choice.equals("default")) {
                return new Board(); // Initializes with the default configuration
            } else {
                System.out.println("Invalid choice. Please enter 'load' or 'default'.");
            }
        }
    }

    // Method to load a gameboard from a file
    private Board loadBoardFromFile() {
        System.out.println("Loading existing gameboard from file...");
        // Placeholder for loading board data
        // Add logic here to read from a file and initialize a custom board

        // For demonstration purposes, we will create and return a default board.
        try {
            File file = new File("gameboard.txt");
            Scanner fileScanner = new Scanner(file);
            // You would parse the data here and use it to create your custom board
            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                System.out.println("Read from file: " + data);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Using default board instead.");
        }

        return new Board(); // Return a default board if loading fails
    }
}
