package pl.kliniewski.battleships.ship;

import pl.kliniewski.battleships.map.MapDirection;
import pl.kliniewski.battleships.map.MapPosition;

public abstract class AbstractShip
        implements Ship
{
    private final MapPosition  startPosition;
    private final int          size;
    private final MapDirection direction;

    public AbstractShip(MapPosition startPosition, int size, MapDirection direction)
    {
        this.startPosition = startPosition;
        this.size = size;
        this.direction = direction;
    }

    public MapPosition getStartPosition()
    {
        return startPosition;
    }

    public int getSize()
    {
        return size;
    }

    public MapDirection getDirection()
    {
        return direction;
    }
}
