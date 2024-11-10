package model;

public class InJailOrVisitingSquare extends Square {

    public InJailOrVisitingSquare() {
        super("In Jail/Just Visiting");
    }

    @Override
    public void action(Player player) {
        if (player.isInJail()) {
            System.out.println(player.getName() + " is in Jail. Must roll doubles or pay fine to get out.");
        } else {
            System.out.println(player.getName() + " is just visiting Jail.");
        }
    }
}
