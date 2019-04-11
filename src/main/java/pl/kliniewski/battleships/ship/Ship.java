package pl.kliniewski.battleships.ship;

import pl.kliniewski.battleships.map.MapDirection;
import pl.kliniewski.battleships.map.MapPosition;

public class Ship
{
    private final String       name;
    private final MapPosition  startPosition;
    private final int          size;
    private final MapDirection direction;

    private int receivedShots;

    public Ship(String name, MapPosition startPosition, int size, MapDirection direction)
    {
        this.name = name;
        this.startPosition = startPosition;
        this.size = size;
        this.direction = direction;
    }

    public String getName()
    {
        return name;
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

    public int getReceivedShots()
    {
        return receivedShots;
    }

    public void shootShip()
    {
        if (!this.isSunk())
        {
            this.receivedShots++;
        }
    }

    public boolean isSunk()
    {
        return this.receivedShots >= this.size;
    }
}
