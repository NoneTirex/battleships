package pl.kliniewski.battleships.map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapPositionTests
{
    @Test
    void addTwoPositions()
    {
        MapPosition first = new MapPosition(5, 3);
        MapPosition second = new MapPosition(1, 3);

        MapPosition sum = first.add(second);

        assertEquals(6, sum.getX());
        assertEquals(6, sum.getZ());
    }

    @Test
    void multiplyPositionByMultiplier()
    {
        MapPosition first = new MapPosition(5, 3);
        MapPosition ratio = first.multiple(3);

        assertEquals(15, ratio.getX());
        assertEquals(9, ratio.getZ());
    }

    @Test
    void provideImmutable()
    {
        MapPosition first = new MapPosition(5, 3);
        MapPosition second = new MapPosition(1, 3);

        MapPosition sum = first.add(second);

        assertNotEquals(sum, first);
        assertNotEquals(sum, second);
    }
}
