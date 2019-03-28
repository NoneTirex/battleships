package pl.kliniewski.battleships.ship;

import pl.kliniewski.battleships.map.MapPosition;

public enum ShipDirection
{
    HORIZONTAL_POSITIVE(1, 0),
    HORIZONTAL_NEGATIVE(-1, 0),
    VERTICAL_POSITIVE(0, 1),
    VERTICAL_NEGATIVE(0, -1);

    private final MapPosition position;

    ShipDirection(int x, int z)
    {
        this.position = new MapPosition(x, z);
    }

    public MapPosition getPosition()
    {
        return position;
    }
}
