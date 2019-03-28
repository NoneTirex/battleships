package pl.kliniewski.battleships.ship;

import pl.kliniewski.battleships.map.MapDirection;
import pl.kliniewski.battleships.map.MapPosition;

public abstract class AbstractShip
        implements Ship
{
    private final MapPosition  startPosition;
    private final int          size;
    private final MapDirection direction;

    private int receivedShots;

    public AbstractShip(MapPosition startPosition, int size, MapDirection direction)
    {
        this.startPosition = startPosition;
        this.size = size;
        this.direction = direction;
    }

    @Override
    public MapPosition getStartPosition()
    {
        return startPosition;
    }

    @Override
    public int getSize()
    {
        return size;
    }

    @Override
    public MapDirection getDirection()
    {
        return direction;
    }

    @Override
    public int getReceivedShots()
    {
        return receivedShots;
    }

    @Override
    public boolean isShotted()
    {
        return this.receivedShots >= this.size;
    }

    @Override
    public void shootShip()
    {
        if (!this.isShotted())
        {
            this.receivedShots++;
        }
    }
}
