package pl.kliniewski.battleships.ship;

import pl.kliniewski.battleships.map.MapDirection;
import pl.kliniewski.battleships.map.MapPosition;

public class DestroyerShip
        extends AbstractShip
{
    public DestroyerShip(MapPosition startPosition, MapDirection direction)
    {
        super(startPosition, DESTROYER_SIZE, direction);
    }

    @Override
    public String getName()
    {
        return "Destroyer";
    }
}
