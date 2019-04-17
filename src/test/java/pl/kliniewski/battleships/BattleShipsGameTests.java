package pl.kliniewski.battleships;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.kliniewski.battleships.map.Map;
import pl.kliniewski.battleships.map.MapDirection;
import pl.kliniewski.battleships.map.MapField;
import pl.kliniewski.battleships.map.MapPosition;
import pl.kliniewski.battleships.ship.ShipTests;

import static org.junit.jupiter.api.Assertions.*;

class BattleShipsGameTests
{
    private BattleShipsGame game;

    @BeforeEach
    void setUp()
    {
        Map map = new Map();
        map.addShip(new ShipTests.TestShip(new MapPosition(9, 9), MapDirection.VERTICAL_NEGATIVE));

        this.game = new BattleShipsGame(map);
    }

    @DisplayName("Check if parseMove method are correctly")
    @ParameterizedTest(name = "Coordinates {0} = ({1}, {2})")
    @CsvSource({
                       "A0, 0, 0", "A1, 0, 1", "A9, 0, 9", "J0, 9, 0", "E5, 4, 5"
               })
    void parseMoveTest(String coordinates, int expectedX, int expectedZ)
    {
        MapPosition position = this.game.parseMove(coordinates);

        assertNotNull(position);
        assertEquals(expectedX, position.getX());
        assertEquals(expectedZ, position.getZ());
    }

    @Test
    void interactFieldTest()
    {
        MapField field = this.game.getMap().getField(5, 5);

        assertTrue(this.game.interactField(field));
        assertTrue(field.isAlreadyHit());
        assertFalse(this.game.interactField(field));
    }

    @Test
    void interactFieldWithSunkenShipTest()
    {
        assertEquals(0, this.game.getSunkenShips());

        this.game.interactField(this.game.getMap().getField(9, 9));
        this.game.interactField(this.game.getMap().getField(9, 8));
        this.game.interactField(this.game.getMap().getField(9, 7));

        assertEquals(1, this.game.getSunkenShips());
    }
}
