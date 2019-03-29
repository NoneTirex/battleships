package pl.kliniewski.battleships.map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapPositionTests
{
    @Test
    @DisplayName("(5, 3) + (1, 3) = (6, 6)")
    void addTwoPositions()
    {
        MapPosition first = new MapPosition(5, 3);
        MapPosition second = new MapPosition(1, 3);

        MapPosition sum = first.add(second);

        assertEquals(6, sum.getX());
        assertEquals(6, sum.getZ());
    }

    @Test
    @DisplayName("(5, 3) * 3 = (15, 9)")
    void multiplyPositionByMultiplier()
    {
        MapPosition first = new MapPosition(5, 3);
        MapPosition ratio = first.multiple(3);

        assertEquals(15, ratio.getX());
        assertEquals(9, ratio.getZ());
    }

    @Test
    @DisplayName("Check if MapPosition object are always immutable")
    void provideImmutable()
    {
        MapPosition first = new MapPosition(5, 3);
        MapPosition second = new MapPosition(1, 3);

        MapPosition sum = first.add(second);
        MapPosition ratio = first.multiple(3);

        assertNotEquals(sum, first);
        assertNotEquals(sum, second);
        assertNotEquals(ratio, first);
    }
}
