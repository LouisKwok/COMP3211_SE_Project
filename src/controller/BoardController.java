package controller;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoardController {
    private Board board;

    public BoardController(Board board) {
        this.board = board;
    }

    // Method to initialize the board based on user choice
    public Board initializeBoard() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Would you like to load a custom map (TXT file) or use the default map? (load/default): ");
            String choice = scanner.nextLine().toLowerCase();

            if (choice.equals("load")) {
                return loadBoardFromTXT();
            } else if (choice.equals("default")) {
                return new Board(); // Return a new instance of the default board
            } else {
                System.out.println("Invalid choice. Please enter 'load' or 'default'.");
            }
        }
    }

    // Method to load the board from a TXT file
    public Board loadBoardFromTXT() {
        Board newBoard = new Board();
        List<Square> squares = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("custom_board.txt"))) {
            // Skip the header of the TXT file
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                // Skip empty lines
                if (line.isEmpty()) {
                    continue;
                }

                // Split the line and check the number of fields
                String[] data = line.split("\t");
                if (data.length < 2) {
                    System.out.println("Invalid line format: " + line);
                    continue;
                }

                String type = data[0].trim();
                String name = data[1].trim();
                int price = (data.length > 2 && !data[2].isEmpty()) ? Integer.parseInt(data[2].trim()) : 0;
                int rent = (data.length > 3 && !data[3].isEmpty()) ? Integer.parseInt(data[3].trim()) : 0;

                Square square;
                switch (type) {
                    case "Go":
                        square = new GoSquare();
                        break;
                    case "Property":
                        square = new Property(name, price, rent);
                        break;
                    case "IncomeTax":
                        square = new IncomeTaxSquare();
                        break;
                    case "InJail":
                        square = new InJailOrVisitingSquare();
                        break;
                    case "FreeParking":
                        square = new FreeParkingSquare();
                        break;
                    case "Chance":
                        square = new ChanceSquare();
                        break;
                    case "GoToJail":
                        square = new GoToJailSquare(board);
                        break;
                    default:
                        square = new BasicSquare(name);
                        break;
                }

                squares.add(square);
            }

            // Update the board's squares list
            newBoard.getSquares().clear();
            newBoard.getSquares().addAll(squares);
            System.out.println("The map has been successfully loaded from custom_board.txt.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("TXT file not found. Using the default map.");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Data format error. Using the default map.");
        }

        return newBoard;
    }

    // Method to display the current gameboard
    public void displayGameBoard() {
        System.out.println("=====================================================");
        System.out.println("              CURRENT GAMEBOARD DESIGN               ");
        System.out.println("=====================================================");
        List<Square> squares = board.getSquares();
        for (int i = 0; i < squares.size(); i++) {
            Square square = squares.get(i);
            if (square instanceof Property) {
                Property property = (Property) square;
                System.out.println((i + 1) + ". Property: " + property.getName() + " (Price: HKD " + property.getPrice() + ", Rent: HKD " + property.getRent() + ")");
            } else if (square instanceof GoSquare) {
                System.out.println((i + 1) + ". Go: " + square.getName());
            } else if (square instanceof IncomeTaxSquare) {
                System.out.println((i + 1) + ". IncomeTax: " + square.getName());
            } else if (square instanceof InJailOrVisitingSquare) {
                System.out.println((i + 1) + ". In Jail/Just Visiting: " + square.getName());
            } else if (square instanceof FreeParkingSquare) {
                System.out.println((i + 1) + ". Free Parking: " + square.getName());
            } else if (square instanceof ChanceSquare) {
                System.out.println((i + 1) + ". Chance: " + square.getName());
            } else if (square instanceof GoToJailSquare) {
                System.out.println((i + 1) + ". Go To Jail: " + square.getName());
            } else {
                System.out.println((i + 1) + ". Basic Square: " + square.getName());
            }
        }
        System.out.println("=====================================================");
    }

    // Method to modify property details, swap positions, or change square types
    public void modifyPropertySquares() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayGameBoard(); // Display the current gameboard before modifying

            System.out.println("Gameboard Designer: Modify Squares");
            System.out.println("1. Modify Property Details");
            System.out.println("2. Swap Two Squares");
            System.out.println("3. Change Square Type");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 0 and 3.");
                continue;
            }

            switch (choice) {
                case 1: // Modify Property Details
                    modifyPropertyDetails(scanner, board.getSquares());
                    break;

                case 2: // Swap Two Squares
                    swapSquares(scanner, board.getSquares());
                    break;

                case 3: // Change Square Type
                    changeSquareType(scanner, board.getSquares());
                    break;

                case 0: // Exit
                    saveBoardToTXT();
                    return;

                default:
                    System.out.println("Invalid choice. Please enter a number between 0 and 3.");
            }
        }
    }

    // Method to modify property details
    public void modifyPropertyDetails(Scanner scanner, List<Square> squares) {
        while (true) {
            displayGameBoard(); // Display the current gameboard to help the designer select the property

            System.out.println("Select a property to modify by entering the corresponding number, or enter 0 to exit:");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 0) {
                    break; // Exit modification loop
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number corresponding to the property.");
                continue;
            }

            if (choice > 0 && choice <= squares.size() && squares.get(choice - 1) instanceof Property) {
                Property property = (Property) squares.get(choice - 1);

                // Prompt user to input new property details
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

                // Update property
                property.setName(newName);
                property.setPrice(newPrice);
                property.setRent(newRent);

                System.out.println("Property updated successfully!");
            } else {
                System.out.println("Invalid property selection. Please try again.");
            }
        }
    }

    // Method to swap two squares
    public void swapSquares(Scanner scanner, List<Square> squares) {
        displayGameBoard(); // Display the current gameboard to help the designer select the squares to swap

        System.out.print("Enter the position of the first square to swap (1-" + squares.size() + "): ");
        int pos1 = getValidPosition(scanner, squares.size());
        if (pos1 == -1) return;

        System.out.print("Enter the position of the second square to swap (1-" + squares.size() + "): ");
        int pos2 = getValidPosition(scanner, squares.size());
        if (pos2 == -1) return;

        // Swap squares
        Square temp = squares.get(pos1 - 1);
        squares.set(pos1 - 1, squares.get(pos2 - 1));
        squares.set(pos2 - 1, temp);

        System.out.println("Squares swapped successfully!");
    }

    // Method to change square type
    public void changeSquareType(Scanner scanner, List<Square> squares) {
        displayGameBoard(); // Display the current gameboard to help the designer select the square to change

        System.out.print("Enter the position of the square to modify (1-" + squares.size() + "): ");
        int pos = getValidPosition(scanner, squares.size());
        if (pos == -1) return;

        System.out.println("Select the new type for the square:");
        System.out.println("1. Go");
        System.out.println("2. Property");
        System.out.println("3. Income Tax");
        System.out.println("4. In Jail/Just Visiting");
        System.out.println("5. Free Parking");
        System.out.println("6. Chance");
        System.out.println("7. Go to Jail");

        int newType;
        try {
            newType = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return;
        }

        // Change the square type
        Square newSquare = createSquareFromType(newType, scanner);
        if (newSquare != null) {
            squares.set(pos - 1, newSquare);
            System.out.println("Square type updated successfully!");
        }
    }

    // Helper method to get a valid position input
    private int getValidPosition(Scanner scanner, int size) {
        while (true) {
            try {
                int pos = Integer.parseInt(scanner.nextLine());
                if (pos < 1 || pos > size) {
                    System.out.println("Invalid position. Please enter a number between 1 and " + size + ".");
                } else {
                    return pos;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    // Helper method to create a square based on the chosen type
    private Square createSquareFromType(int newType, Scanner scanner) {
        switch (newType) {
            case 1:
                return new GoSquare();
            case 2:
                System.out.print("Enter the name of the property: ");
                String name = scanner.nextLine();
                System.out.print("Enter the price of the property: ");
                int price = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter the rent of the property: ");
                int rent = Integer.parseInt(scanner.nextLine());
                return new Property(name, price, rent);
            case 3:
                return new IncomeTaxSquare();
            case 4:
                return new InJailOrVisitingSquare();
            case 5:
                return new FreeParkingSquare();
            case 6:
                return new ChanceSquare();
            case 7:
                return new GoToJailSquare(board);
            default:
                System.out.println("Invalid type selection.");
                return null;
        }
    }

    // Method to save the board to a TXT file
    public void saveBoardToTXT() {
        try (PrintWriter writer = new PrintWriter(new File("custom_board.txt"))) {
            List<Square> squares = board.getSquares();
            writer.println("Type\tName\tPrice\tRent");

            for (Square square : squares) {
                if (square instanceof Property) {
                    Property property = (Property) square;
                    writer.println("Property\t" + property.getName() + "\t" + property.getPrice() + "\t" + property.getRent());
                } else if (square instanceof GoSquare) {
                    writer.println("Go\t" + square.getName() + "\t\t");
                } else if (square instanceof IncomeTaxSquare) {
                    writer.println("IncomeTax\t" + square.getName() + "\t\t");
                } else if (square instanceof InJailOrVisitingSquare) {
                    writer.println("InJail\t" + square.getName() + "\t\t");
                } else if (square instanceof FreeParkingSquare) {
                    writer.println("FreeParking\t" + square.getName() + "\t\t");
                } else if (square instanceof ChanceSquare) {
                    writer.println("Chance\t" + square.getName() + "\t\t");
                } else if (square instanceof GoToJailSquare) {
                    writer.println("GoToJail\t" + square.getName() + "\t\t");
                } else {
                    writer.println("Square\t" + square.getName() + "\t\t");
                }
            }
            System.out.println("The map has been successfully saved to custom_board.txt.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error saving the map to TXT file.");
        }
    }
}
