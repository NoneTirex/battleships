package pl.kliniewski.battleships.map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapBuilderTests
{
    @Test
    void buildTest()
    {
        MapBuilder builder = new MapBuilder();

        assertNotNull(builder.build());
    }
}
