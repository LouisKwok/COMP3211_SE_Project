package view;

import model.Board;
import model.Player;
import model.Property;
import model.Square;

import java.util.List;

public class GameView {

    // Display a large, ASCII-style representation of the Monopoly board with numbered positions
    public void displayBoardLarge(Board board, List<Player> players) {
        List<Square> squares = board.getSquares();

        // 加大每個方格的寬度到 36 個字符，並確保每一行都對齊
        System.out.println("╔═══════════════════════════════════════╦══════════════════════════════════════╦══════════════════════════════════════╦══════════════════════════════════════╦══════════════════════════════════════╦══════════════════════════════════════╗");
        System.out.printf(" ║ %-36s ║ %-36s ║ %-36s ║ %-36s ║ %-36s ║ %-36s ║\n",
                formatSquare(squares.get(10), 10, players),
                formatSquare(squares.get(11), 11, players),
                formatSquare(squares.get(12), 12, players),
                formatSquare(squares.get(13), 13, players),
                formatSquare(squares.get(14), 14, players),
                formatSquare(squares.get(15), 15, players));
        System.out.println("╠══════════════════════════════════════╩═══════════════════════════════════════╩══════════════════════════════════════╩══════════════════════════════════════╩══════════════════════════════════════╩══════════════════════════════════════╣");
        System.out.printf(" ║ %-36s ║                                                                                                                                                         ║ %-36s ║\n",
                formatSquare(squares.get(9), 9, players),
                formatSquare(squares.get(16), 16, players));
        System.out.println("╠══════════════════════════════════════╣                                                                                                                                                            ╠══════════════════════════════════════╣");
        System.out.printf(" ║ %-36s ║                                                                                                                                                           ║ %-36s ║\n",
                formatSquare(squares.get(8), 8, players),
                formatSquare(squares.get(17), 17, players));
        System.out.println("╠══════════════════════════════════════╣                                                                                                                                                            ╠══════════════════════════════════════╣");
        System.out.printf(" ║ %-36s ║                                                                                                                                                          ║ %-36s ║\n",
                formatSquare(squares.get(7), 7, players),
                formatSquare(squares.get(18), 18, players));
        System.out.println("╠══════════════════════════════════════╣                                                                                                                                                            ╠══════════════════════════════════════╣");
        System.out.printf(" ║ %-36s ║                                                                                                                                                          ║ %-36s ║\n",
                formatSquare(squares.get(6), 6, players),
                formatSquare(squares.get(19), 19, players));
        System.out.println("╠══════════════════════════════════════╦══════════════════════════════════════╦══════════════════════════════════════╦══════════════════════════════════════╦══════════════════════════════════════╦══════════════════════════════════════╣");
        System.out.printf(" ║ %-36s ║ %-36s ║ %-36s ║ %-36s ║ %-36s ║ %-36s ║\n",
                formatSquare(squares.get(5), 5, players),
                formatSquare(squares.get(4), 4, players),
                formatSquare(squares.get(3), 3, players),
                formatSquare(squares.get(2), 2, players),
                formatSquare(squares.get(1), 1, players),
                formatSquare(squares.get(0), 0, players));
        System.out.println("╚══════════════════════════════════════╩══════════════════════════════════════╩══════════════════════════════════════╩══════════════════════════════════════╩══════════════════════════════════════╩══════════════════════════════════════╝");

        // 顯示玩家位置
        System.out.println("Players' Positions:");
        for (Player player : players) {
            System.out.println(player.getName() + " is on square " + (player.getPosition() + 1));
        }
    }

    // Helper method to format square details dynamically
    private String formatSquare(Square square, int position, List<Player> players) {
        StringBuilder squareInfo = new StringBuilder(" " + (position + 1) + ". " + square.getName() + " ");

        // 如果是Property類型，顯示價格和租金
        if (square instanceof Property) {
            Property property = (Property) square;
            squareInfo.append("(P: HKD ").append(property.getPrice())
                    .append(", R: HKD ").append(property.getRent()).append(") ");
        }

        // 檢查是否有玩家在該方格，並顯示玩家名稱
        for (Player player : players) {
            if (player.getPosition() == position) {
                squareInfo.append("[").append(player.getName()).append("] ");
            }
        }

        return squareInfo.toString();
    }

    // 顯示特定玩家的狀態
    public void displayPlayerStatus(Player player) {
        System.out.println("-----------------------------------------------------");
        System.out.println("Player: " + player.getName());
        System.out.println("Position: " + (player.getPosition() + 1));
        System.out.println("Money: " + player.getMoney() + " HKD");
        System.out.println("-----------------------------------------------------");
    }

    // 向玩家顯示一般消息
    public void showMessage(String message) {
        System.out.println(message);
    }
}
