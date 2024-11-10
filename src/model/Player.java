package model;

public class Player {
    private String name;
    private int money;
    private int position;
    private boolean inJail;
    private int jailTurns;

    public Player(String name) {
        this.name = name;
        this.money = 1500; // Starting money
        this.position = 0; // Player starts at position 0
        this.inJail = false; // Player is not in jail initially
        this.jailTurns = 0; // Number of turns left in jail, initially 0 since not in jail
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    // Method to update the player's money (add or subtract)
    public void updateMoney(int amount) {
        this.money += amount;
        if (money < 0) {
            System.out.println(name + " is bankrupt!");
        }
    }

    // Method to get the player's current position
    public int getPosition() {
        return position;
    }

    // Method to set the player's position (useful for "Go to Jail" and other scenarios)
    public void setPosition(int position) {
        this.position = position;
    }

    // Method to check if the player is in jail
    public boolean isInJail() {
        return inJail;
    }

    // Method to set whether the player is in jail
    public void setInJail(boolean inJail) {
        this.inJail = inJail;
        if (inJail) {
            jailTurns = 3; // Set the initial number of jail turns if player is sent to jail
        } else {
            jailTurns = 0; // Reset jail turns if player gets out of jail
        }
    }

    // Method to decrease the number of turns left in jail
    public void decreaseJailTurn() {
        if (jailTurns > 0) {
            jailTurns--;
        }
    }

    // Method to get the number of turns left in jail
    public int getJailTurns() {
        return jailTurns;
    }

    // Method to move the player forward by a certain number of spaces
    public void move(int spaces) {
        if (!inJail) {
            this.position = (this.position + spaces) % 20; // Assuming there are 20 squares on the board
            System.out.println(name + " moves to position " + position + ".");
        } else {
            System.out.println(name + " is in jail and cannot move.");
        }
    }
}
