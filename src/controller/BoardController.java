package controller;

import model.Board;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BoardController {

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
