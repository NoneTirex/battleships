package pl.kliniewski.battleships.map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.kliniewski.battleships.map.field.MapField;
import pl.kliniewski.battleships.ship.Ship;
import pl.kliniewski.battleships.ship.ShipTests;

import static org.junit.jupiter.api.Assertions.*;

class MapTests
{
    @Test
    @DisplayName("Add ship to map")
    void addShipToMap()
    {
        Ship ship = new ShipTests.TestShip(new MapPosition(5, 5), MapDirection.HORIZONTAL_NEGATIVE);
        Map map = new Map();
        map.addShip(ship);

        assertEquals(ship, map.getShip(5, 5));
        assertEquals(ship, map.getShip(4, 5));
        assertEquals(ship, map.getShip(3, 5));

        assertNull(map.getShip(2, 5));
        assertNull(map.getShip(6, 5));
        assertNull(map.getShip(5, 4));
        assertNull(map.getShip(5, 6));
    }

    @Test
    @DisplayName("Check if the ship collide with other")
    void collideOtherShips()
    {
        Ship ship = new ShipTests.TestShip(new MapPosition(5, 5), MapDirection.HORIZONTAL_NEGATIVE);
        Ship secondShip = new ShipTests.TestShip(new MapPosition(4, 5), MapDirection.VERTICAL_POSITIVE);

        Map map = new Map();
        map.addShip(ship);

        assertTrue(map.collidedOtherShips(secondShip));
    }

    @Test
    @DisplayName("Check if the ship aren't collide with other")
    void notCollideOtherShips()
    {
        Ship ship = new ShipTests.TestShip(new MapPosition(5, 5), MapDirection.HORIZONTAL_NEGATIVE);
        Ship secondShip = new ShipTests.TestShip(new MapPosition(4, 6), MapDirection.HORIZONTAL_NEGATIVE);

        Map map = new Map();
        map.addShip(ship);

        assertFalse(map.collidedOtherShips(secondShip));
    }

    @Test
    @DisplayName("Check state of not shooted field")
    void checkStateOfNotShootedField()
    {
        Map map = new Map();
        MapField mapField = map.getField(7, 7);

        assertNotNull(mapField);
        assertFalse(mapField.isAlreadyHit(), "Field shouldn't be hit");
    }

    @Test
    @DisplayName("Check state of shooted field")
    void checkStateOfShootedField()
    {
        Map map = new Map();
        map.getField(7, 7).shootField();

        MapField mapField = map.getField(7, 7);

        assertNotNull(mapField);
        assertTrue(mapField.isAlreadyHit(), "Field should be hit");
    }

    @Test
    @DisplayName("Check if the map is completed")
    void completedMap()
    {
        Ship ship = new ShipTests.TestShip(new MapPosition(5, 5), MapDirection.HORIZONTAL_NEGATIVE);
        Map map = new Map();

        assertTrue(map.isFinished());

        map.addShip(ship);

        assertFalse(map.isFinished());

        map.getField(5, 5).shootField();
        map.getField(4, 5).shootField();
        map.getField(3, 5).shootField();

        assertTrue(map.isFinished());
    }
}
