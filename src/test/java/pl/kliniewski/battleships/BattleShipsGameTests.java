package pl.kliniewski.battleships;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kliniewski.battleships.map.Map;
import pl.kliniewski.battleships.map.MapDirection;
import pl.kliniewski.battleships.map.MapPosition;
import pl.kliniewski.battleships.ship.ShipTests;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BattleShipsGameTests
{
    @Mock
    private DisplayEngine display;

    private BattleShipsGame game;

    @BeforeEach
    void setUp()
    {
        Map map = new Map();
        map.addShip(new ShipTests.TestShip(new MapPosition(9, 9), MapDirection.VERTICAL_NEGATIVE));

        this.game = new BattleShipsGame(this.display, map);
    }

    @DisplayName("Check if parseMove method are correctly")
    @ParameterizedTest(name = "Coordinates {0} = ({1}, {2})")
    @CsvSource({
                       "A2, 0, 1", "A1, 0, 0", "A10, 0, 9", "J1, 9, 0", "E5, 4, 4"
               })
    void parseMoveTest(String coordinates, int expectedX, int expectedZ)
    {
        MapPosition position = this.game.parseMove(coordinates);

        assertEquals(new MapPosition(expectedX, expectedZ), position);
    }

    @Test
    void parseMoveIncorrectCoordinatesTest()
    {
        MapPosition position = this.game.parseMove("A11");

        assertNull(position);
    }

    @Test
    void executeMoveTest()
    {
        MapPosition position = new MapPosition(5, 5);

        this.game.executeMove(position);
        Mockito.verify(this.display, Mockito.never()).printFieldAlreadyShot();

        this.game.executeMove(position);
        Mockito.verify(this.display).printFieldAlreadyShot();

        Mockito.verify(this.display).printMap(Mockito.any());
        Mockito.verify(this.display).printShot(Mockito.any());
    }

    @Test
    @DisplayName("Check if executeMove method finish game after sunk all ships")
    void executeMoveFinishAfterSunkShipsTest()
    {
        assertFalse(this.game.isFinished());

        this.game.executeMove(new MapPosition(9, 9));
        this.game.executeMove(new MapPosition(9, 8));
        this.game.executeMove(new MapPosition(9, 7));

        Mockito.verify(this.display).printVictory();
        assertTrue(this.game.isFinished());
    }

    @Test
    void preprocessInputTest()
    {
        this.game.preprocessInput("##");
        Mockito.verify(this.display, Mockito.only()).printIncorrectMove(Mockito.any());
    }
}
