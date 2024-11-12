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

    // Method to modify the properties of the gameboard
    public void modifyPropertySquares() {
        Scanner scanner = new Scanner(System.in);
        List<Square> squares = board.getSquares();

        while (true) {
            System.out.println("Gameboard Designer: Modify Property Squares");
            System.out.println("Select a property to modify by entering the corresponding number, or enter 0 to exit:");

            // 顯示所有屬性方格，使用從文件中載入的名稱
            for (int i = 0; i < squares.size(); i++) {
                Square square = squares.get(i);
                if (square instanceof Property) {
                    Property property = (Property) square;
                    System.out.println((i + 1) + ". " + property.getName() + " (Price: HKD " + property.getPrice() + ", Rent: HKD " + property.getRent() + ")");
                }
            }

            // 用戶輸入選擇
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 0) {
                    break; // 用戶選擇退出
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number corresponding to the property.");
                continue;
            }

            if (choice > 0 && choice <= squares.size() && squares.get(choice - 1) instanceof Property) {
                Property property = (Property) squares.get(choice - 1);

                // 提示用戶輸入新的屬性細節
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

                // 更新屬性
                property.setName(newName);
                property.setPrice(newPrice);
                property.setRent(newRent);

                System.out.println("Property updated successfully!");
            } else {
                System.out.println("Invalid property selection. Please try again.");
            }
        }

        // 保存修改後的地圖到 TXT 文件
        saveBoardToTXT();
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
                return new Board();
            } else {
                System.out.println("Invalid choice. Please enter 'load' or 'default'.");
            }
        }
    }


    public Board loadBoardFromTXT() {
        Board newBoard = new Board();
        List<Square> squares = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("custom_board.txt"))) {
            // 跳過 TXT 文件的表頭
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                // 跳過空行
                if (line.isEmpty()) {
                    continue;
                }

                // 分割字串並檢查欄位長度
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
                        square = new GoToJailSquare();
                        break;
                    default:
                        square = new BasicSquare(name);
                        break;
                }

                squares.add(square);
            }

            // 更新 Board 的 squares 列表
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


    // Method to load a gameboard from a file
    private Board loadBoardFromFile() {
        System.out.println("Loading existing gameboard from file...");
        try (FileInputStream fileIn = new FileInputStream("board.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            Board loadedBoard = (Board) in.readObject();
            System.out.println("Board loaded successfully.");
            return loadedBoard;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed to load the board. Using default board instead.");
            return new Board(); // 如果加载失败，返回默认的游戏板
        }
    }
}
