package pl.kliniewski.battleships.map;

import pl.kliniewski.battleships.map.field.MapField;
import pl.kliniewski.battleships.map.field.MapShipField;
import pl.kliniewski.battleships.ship.Ship;

public class Map
{
    private final MapField[][] fields = new MapField[10][10];

    public Map()
    {
        this.fillEmptyFields();
    }

    private void fillEmptyFields()
    {
        for (int z = 0; z < this.fields.length; z++)
        {
            MapField[] column = this.fields[z];
            for (int x = 0; x < column.length; x++)
            {
                if (column[x] == null)
                {
                    column[x] = new MapField();
                }
            }
        }
    }

    public boolean collidedOtherShips(Ship ship)
    {
        return this.collidedOtherShips(ship.getStartPosition(), ship.getSize(), ship.getDirection());
    }

    public boolean collidedOtherShips(MapPosition startPosition, int size, MapDirection direction)
    {
        MapPosition currentPosition = startPosition;
        for (int i = 0; i < size; i++)
        {
            int x = currentPosition.getX();
            int z = currentPosition.getZ();
            if (this.getField(x, z) instanceof MapShipField)
            {
                return true;
            }
            currentPosition = currentPosition.add(direction.getPosition());
        }
        return false;
    }

    public void addShip(Ship ship)
    {
        MapPosition currentPosition = ship.getStartPosition();
        for (int i = 0; i < ship.getSize(); i++)
        {
            int x = currentPosition.getX();
            int z = currentPosition.getZ();
            this.fields[z][x] = new MapShipField(ship);
            currentPosition = currentPosition.add(ship.getDirection().getPosition());
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

    public boolean isFinished()
    {
        for (MapField[] row : this.fields)
        {
            for (MapField field : row)
            {
                if (field instanceof MapShipField && !field.isAlreadyHit())
                {
                    return false;
                }
            }
        }
        return true;
    }

    public MapField getField(int x, int z)
    {
        return this.fields[z][x];
    }

    public MapField[][] getFields()
    {
        return fields;
    }
}
