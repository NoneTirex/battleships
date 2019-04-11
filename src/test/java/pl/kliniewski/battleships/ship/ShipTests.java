package pl.kliniewski.battleships.ship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.kliniewski.battleships.map.MapDirection;
import pl.kliniewski.battleships.map.MapPosition;

import static org.junit.jupiter.api.Assertions.*;

public class ShipTests
{
    @Test
    @DisplayName("Check if ship gets a shot correctly")
    void shootingSystemToShip()
    {
        TestShip testShip = new TestShip(new MapPosition(0, 0), MapDirection.HORIZONTAL_POSITIVE);

        testShip.shootShip();
        assertEquals(1, testShip.getReceivedShots());
        assertFalse(testShip.isSunk());
    }

    @Test
    @DisplayName("Check if ship was sunk after get right amount of shots")
    void checkSunkShip()
    {
        TestShip testShip = new TestShip(new MapPosition(0, 0), MapDirection.HORIZONTAL_POSITIVE);

        for (int i = 0; i < 3; i++)
        {
            testShip.shootShip();
        }
        assertEquals(3, testShip.getReceivedShots());
        assertTrue(testShip.isSunk());
    }

    public static class TestShip
            extends Ship
    {
        public TestShip(MapPosition startPosition, MapDirection direction)
        {
            super("Test", startPosition, 3, direction);
        }
    }
}
