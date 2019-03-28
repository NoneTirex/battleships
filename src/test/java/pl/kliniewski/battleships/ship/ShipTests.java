package pl.kliniewski.battleships.ship;

import org.junit.jupiter.api.Test;
import pl.kliniewski.battleships.map.MapDirection;
import pl.kliniewski.battleships.map.MapPosition;

import static org.junit.jupiter.api.Assertions.*;

public class ShipTests
{
    @Test
    void shootingSystemToShip()
    {
        TestShip testShip = new TestShip(new MapPosition(0, 0), MapDirection.HORIZONTAL_POSITIVE);

        testShip.shootShip();
        assertEquals(1, testShip.getReceivedShots());
        assertFalse(testShip.isShotted());
    }

    @Test
    void checkShottedShip()
    {
        TestShip testShip = new TestShip(new MapPosition(0, 0), MapDirection.HORIZONTAL_POSITIVE);

        for (int i = 0; i < 3; i++)
        {
            testShip.shootShip();
        }
        assertEquals(3, testShip.getReceivedShots());
        assertTrue(testShip.isShotted());
    }

    public static class TestShip
            extends AbstractShip
    {
        public TestShip(MapPosition startPosition, MapDirection direction)
        {
            super(startPosition, 3, direction);
        }

        @Override
        public String getName()
        {
            return "Test";
        }
    }
}
