package pl.kliniewski.battleships.map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

        assertEquals(ship, map.getShip(5, 5).orElse(null));
        assertEquals(ship, map.getShip(4, 5).orElse(null));
        assertEquals(ship, map.getShip(3, 5).orElse(null));
    }

    @Test
    @DisplayName("Check if the ship collide with other")
    void collideOtherShips()
    {
        Ship ship = new ShipTests.TestShip(new MapPosition(5, 5), MapDirection.HORIZONTAL_NEGATIVE);
        MapPosition position = new MapPosition(4, 5);
        MapDirection direction = MapDirection.VERTICAL_POSITIVE;
        Ship secondShip = new ShipTests.TestShip(position, direction);

        Map map = new Map();
        map.addShip(ship);

        assertTrue(map.collidedOtherShips(secondShip));
        assertTrue(map.collidedOtherShips(position, secondShip.getSize(), direction));
    }

    @Test
    @DisplayName("Check if the ship aren't collide with other")
    void notCollideOtherShips()
    {
        Ship ship = new ShipTests.TestShip(new MapPosition(5, 5), MapDirection.HORIZONTAL_NEGATIVE);
        MapPosition position = new MapPosition(4, 6);
        MapDirection direction = MapDirection.HORIZONTAL_NEGATIVE;
        Ship secondShip = new ShipTests.TestShip(position, direction);

        Map map = new Map();
        map.addShip(ship);

        assertFalse(map.collidedOtherShips(secondShip));
        assertFalse(map.collidedOtherShips(position, secondShip.getSize(), direction));
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
