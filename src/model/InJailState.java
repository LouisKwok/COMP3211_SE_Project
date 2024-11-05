package model;

public class InJailState {
    private int turnsInJail;

    public InJailState() {
        this.turnsInJail = 0;
    }

    public void incrementTurns() {
        turnsInJail++;
    }

    public void resetTurns() {
        turnsInJail = 0;
    }

    public int getTurnsInJail() {
        return turnsInJail;
    }

    public boolean shouldRelease() {
        return turnsInJail >= 3;
    }

    public void releaseFromJail(Player player) {
        player.setInJail(false);
        resetTurns();
    }
}
