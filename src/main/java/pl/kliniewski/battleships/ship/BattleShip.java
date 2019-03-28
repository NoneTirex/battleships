package pl.kliniewski.battleships.ship;

import pl.kliniewski.battleships.map.MapDirection;
import pl.kliniewski.battleships.map.MapPosition;

public class BattleShip
        extends AbstractShip
{
    public BattleShip(MapPosition startPosition, MapDirection direction)
    {
        super(startPosition, 5, direction);
    }

    @Override
    public String getName()
    {
        return "Battleship";
    }
}
