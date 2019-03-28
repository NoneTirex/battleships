package pl.kliniewski.battleships.ship;

import pl.kliniewski.battleships.map.MapPosition;

public abstract class AbstractShip
        implements Ship
{
    private final MapPosition startPosition;
    private final int size;
    private final ShipDirection direction;

    public AbstractShip(MapPosition startPosition, int size, ShipDirection direction)
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

    public ShipDirection getDirection()
    {
        return direction;
    }
}
