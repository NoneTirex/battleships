package pl.kliniewski.battleships.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapPositionTests
{
    @Test
    void addTwoPositions()
    {
        MapPosition first = new MapPosition(5, 3);
        MapPosition second = new MapPosition(1, 3);

        MapPosition sum = first.add(second);

        Assertions.assertEquals(6, sum.getX());
        Assertions.assertEquals(6, sum.getZ());
    }
}
