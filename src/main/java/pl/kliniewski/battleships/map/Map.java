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
            MapPosition currentPosition = ship.getStartPosition();
            for (int i = 0; i < ship.getSize(); i++)
            {
                int x = currentPosition.getX();
                int z = currentPosition.getZ();
                this.fields[x][z] = new MapShipField(x, z, ship);
                currentPosition = currentPosition.add(ship.getDirection().getPosition());
            }
        }
        this.fillEmptyFields();
    }

    private void fillEmptyFields()
    {
        for (int x = 0; x < this.fields.length; x++)
        {
            MapField[] column = this.fields[x];
            for (int z = 0; z < column.length; z++)
            {
                if (column[z] == null)
                {
                    column[z] = new MapField();
                }
            }
        }
    }

    public Ship getShip(int x, int z)
    {
        MapField field = this.getField(x, z);
        if (field instanceof MapShipField)
        {
            return ((MapShipField) field).getShip();
        }
        return null;
    }

    public void shootIntoField(int x, int z)
    {
        MapField field = this.getField(x, z);
        field.setAlreadyHit(true);
    }

    public MapField getField(int x, int z)
    {
        return this.fields[x][z];
    }
}
