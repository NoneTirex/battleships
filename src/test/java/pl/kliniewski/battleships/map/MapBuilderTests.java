package pl.kliniewski.battleships.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MapBuilderTests
{
    private MapBuilder builder;

    @BeforeEach
    void setUp()
    {
        this.builder = new MapBuilder();
        this.builder.setRandom(new Random(1));
    }

    @Test
    void buildTest()
    {
        assertNotNull(this.builder.build());
    }

    @Test
    void randomDirectionTest()
    {
        MapDirection direction = this.builder.randomDirection();

        assertNotNull(direction);
        assertEquals(MapDirection.VERTICAL_POSITIVE, direction);
    }

    @Test
    void nextIntTest()
    {
        assertEquals(-5, this.builder.nextInt(-10, 10));
    }

    @Test
    void randomPositionTest()
    {
        MapPosition position = this.builder.randomPosition(3, MapDirection.VERTICAL_POSITIVE);

        assertNotNull(position);
        assertEquals(5, position.getX());
        assertEquals(4, position.getZ());
    }

    @Test
    void appendRandomShipTest()
    {
        this.builder.appendRandomShip("Test", 3);

        Map build = this.builder.build();
        assertNotNull(build.getShip(8, 1));
        assertNotNull(build.getShip(8, 2));
        assertNotNull(build.getShip(8, 3));
    }

    @Test
    void appendRandomDestroyerTest()
    {
        this.builder.appendRandomDestroyer();

        Map build = this.builder.build();
        assertNotNull(build.getShip(8, 1));
        assertNotNull(build.getShip(8, 2));
        assertNotNull(build.getShip(8, 3));
        assertNotNull(build.getShip(8, 4));
    }

    @Test
    void appendRandomBattleshipTest()
    {
        this.builder.appendRandomBattleship();

        Map build = this.builder.build();
        assertNotNull(build.getShip(8, 2));
        assertNotNull(build.getShip(8, 3));
        assertNotNull(build.getShip(8, 4));
        assertNotNull(build.getShip(8, 5));
        assertNotNull(build.getShip(8, 6));
    }
}
