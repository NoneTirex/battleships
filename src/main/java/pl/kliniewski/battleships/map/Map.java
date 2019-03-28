package pl.kliniewski.battleships.map;

import pl.kliniewski.battleships.map.field.MapField;
import pl.kliniewski.battleships.map.field.MapShipField;
import pl.kliniewski.battleships.ship.Ship;

public class Map
{
    private final MapField[][] fields = new MapField[10][10];

    public Map(Ship... ships)
    {
        for (Ship ship : ships)
        {
            MapPosition lastPosition = ship.getStartPosition();
            for (int i = 0; i < ship.getSize(); i++)
            {
                MapPosition currentPosition = lastPosition.add(ship.getDirection().getPosition());
                int x = currentPosition.getX();
                int z = currentPosition.getZ();
                this.fields[x][z] = new MapShipField(x, z, ship);
                lastPosition = currentPosition;
            }
        }
    }

    public Ship getShip(int x, int z)
    {
        return null;
    }

    public boolean shootIntoField(int x, int z)
    {
        return false;
    }
}
