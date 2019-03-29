package pl.kliniewski.battleships.map.field;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.kliniewski.battleships.map.MapDirection;
import pl.kliniewski.battleships.map.MapPosition;
import pl.kliniewski.battleships.ship.Ship;
import pl.kliniewski.battleships.ship.ShipTests;

import static org.junit.jupiter.api.Assertions.*;

public class MapShipFieldTests
{
    @Test
    @DisplayName("Check if ship a get shot from field")
    void shootField()
    {
        Ship ship = new ShipTests.TestShip(new MapPosition(0, 0), MapDirection.HORIZONTAL_POSITIVE);
        MapShipField shipField = new MapShipField(ship);

        shipField.shootField();

        assertEquals(1, ship.getReceivedShots());
        assertTrue(shipField.isAlreadyHit());
    }
}
