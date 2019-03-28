package pl.kliniewski.battleships.map.field;

import pl.kliniewski.battleships.ship.Ship;

public class MapShipField extends MapField
{
    private final Ship ship;

    public MapShipField(int x, int z, Ship ship)
    {
        super(x, z);
        this.ship = ship;
    }

    public Ship getShip()
    {
        return ship;
    }
}
