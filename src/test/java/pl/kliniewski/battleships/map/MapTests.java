package pl.kliniewski.battleships.map;

import org.junit.jupiter.api.Test;
import pl.kliniewski.battleships.map.field.MapField;
import pl.kliniewski.battleships.ship.Ship;
import pl.kliniewski.battleships.ship.ShipTests;

import static org.junit.jupiter.api.Assertions.*;

class MapTests
{
    @Test
    void addShipToMap()
    {
        Ship battleShip = new ShipTests.TestShip(new MapPosition(5, 5), MapDirection.HORIZONTAL_NEGATIVE);
        Map map = new Map(battleShip);

        assertEquals(battleShip, map.getShip(5, 5));
        assertEquals(battleShip, map.getShip(4, 5));
        assertEquals(battleShip, map.getShip(3, 5));

        assertNull(map.getShip(2, 5));
        assertNull(map.getShip(6, 5));
        assertNull(map.getShip(5, 4));
        assertNull(map.getShip(5, 6));
    }

    @Test
    void checkStateOfNotShootedField()
    {
        Map map = new Map();
        MapField mapField = map.getField(7, 7);

        assertNotNull(mapField);
        assertFalse(mapField.isAlreadyHit(), "Field shouldn't be hit");
    }

    @Test
    void checkStateOfShootedField()
    {
        Map map = new Map();
        map.getField(7, 7).shootField();

        MapField mapField = map.getField(7, 7);

        assertNotNull(mapField);
        assertTrue(mapField.isAlreadyHit(), "Field should be hit");
    }
}
