package pl.kliniewski.battleships;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.kliniewski.battleships.map.Map;
import pl.kliniewski.battleships.map.MapPosition;

import static org.junit.jupiter.api.Assertions.*;

public class BattleShipsGameTests
{
    private final BattleShipsGame game = new BattleShipsGame(new Map());

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
}
