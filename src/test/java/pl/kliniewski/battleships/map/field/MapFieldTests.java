package pl.kliniewski.battleships.map.field;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapFieldTests
{
    @Test
    @DisplayName("Check if field is shooted")
    void shootField()
    {
        MapField field = new MapField();

        field.shootField();

        assertTrue(field.isAlreadyHit());
    }
}
