package model;

public class GoSquare extends Square {
    public GoSquare(String name) {
        super(name);
    }

    public void passGo(Player player) {
        player.setMoney(player.getMoney() + 1500);
        System.out.println(player.getName() + " passed GO and collected HKD 1500!");
    }
}