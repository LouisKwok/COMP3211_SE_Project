package test;

import model.Player;
import model.Property;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {

    @Test
    void testBuyingAndPayingRent() {
        // Test property ownership and rent payments
        Player owner = new Player("Harry");
        Player visitor = new Player("Isabelle");
        Property property = new Property("Central", 800, 90);

        // Owner buys the property
        property.setOwner(owner);
        assertEquals(owner, property.getOwner(), "Owner should be set correctly.");

        // Visitor lands on the property
        property.action(visitor);
        assertEquals(1410, visitor.getMoney(), "Visitor should pay rent to the owner.");
        assertEquals(1590, owner.getMoney(), "Owner should receive rent from the visitor.");
    }
}

