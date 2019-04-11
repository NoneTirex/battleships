package pl.kliniewski.battleships.map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.kliniewski.battleships.ship.Ship;
import pl.kliniewski.battleships.ship.ShipTests;

import static org.junit.jupiter.api.Assertions.*;

public class MapFieldTests
{
    @Test
    @DisplayName("Check if field is shooted")
    void shootField()
    {
        MapField field = new MapField();

        field.shootField();

        assertTrue(field.isAlreadyHit());
    }

    @Test
    @DisplayName("Check if ship a get shot from field")
    void shootShipField()
    {
        Ship ship = new ShipTests.TestShip(new MapPosition(0, 0), MapDirection.HORIZONTAL_POSITIVE);
        MapField shipField = new MapField(ship);

        shipField.shootField();

        assertEquals(1, ship.getReceivedShots());
        assertTrue(shipField.isAlreadyHit());
    }
}
