package pl.kliniewski.battleships.map;

import org.junit.jupiter.api.Test;
import pl.kliniewski.battleships.map.field.MapField;
import pl.kliniewski.battleships.ship.BattleShip;

import static org.junit.jupiter.api.Assertions.*;

public class MapTests
{
    @Test
    public void addShipToMap()
    {
        BattleShip battleShip = new BattleShip(new MapPosition(5, 5), MapDirection.HORIZONTAL_NEGATIVE);
        Map map = new Map(battleShip);

        assertEquals(battleShip, map.getShip(5, 5));
        assertEquals(battleShip, map.getShip(4, 5));
        assertEquals(battleShip, map.getShip(3, 5));
        assertEquals(battleShip, map.getShip(2, 5));
        assertEquals(battleShip, map.getShip(1, 5));
        assertNull(map.getShip(0, 5), "In this field shouldn't be a ship");
    }

    @Test
    public void checkStateOfNotShootedField()
    {
        Map map = new Map();
        MapField mapField = map.getField(7, 7);

        assertNotNull(mapField);
        assertFalse(mapField.isAlreadyHit(), "Field shouldn't be hit");
    }

    @Test
    public void checkStateOfShootedField()
    {
        Map map = new Map();
        map.getField(7, 7).shootField();

        MapField mapField = map.getField(7, 7);

        assertNotNull(mapField);
        assertTrue(mapField.isAlreadyHit(), "Field should be hit");
    }
}
