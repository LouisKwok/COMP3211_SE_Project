package model;

public class FreeParking extends Square {
    public FreeParking(String name) {
        super(name);
    }

    public void park() {
        System.out.println("This is Free Parking. No action required.");
    }
}