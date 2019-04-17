package pl.kliniewski.battleships.map;

import pl.kliniewski.battleships.ship.Ship;

public class Map
{
    public static final int MAP_SIZE = 10;

    private final MapField[][] fields = new MapField[MAP_SIZE][MAP_SIZE];

    public Map()
    {
        this.fillFields();
    }

    private void fillFields()
    {
        for (MapField[] row : this.fields)
        {
            for (int x = 0; x < row.length; x++)
            {
                row[x] = new MapField();
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
            if (this.getField(currentPosition.getX(), currentPosition.getZ()).getShip() != null)
            {
                return true;
            }
            currentPosition = currentPosition.add(direction.getPosition());
        }
        return false;
    }

    public MapField getField(int x, int z)
    {
        return this.fields[z][x];
    }

    public void addShip(Ship ship)
    {
        MapPosition currentPosition = ship.getStartPosition();
        for (int i = 0; i < ship.getSize(); i++)
        {
            this.fields[currentPosition.getZ()][currentPosition.getX()] = new MapField(ship);
            currentPosition = currentPosition.add(ship.getDirection().getPosition());
        }
    }

    public Ship getShip(int x, int z)
    {
        return this.getField(x, z).getShip();
    }

    public boolean isFinished()
    {
        for (MapField[] row : this.fields)
        {
            for (MapField field : row)
            {
                if (field.getShip() != null && !field.isAlreadyHit())
                {
                    return false;
                }
            }
        }
        return true;
    }

    public MapField[][] getFields()
    {
        return fields;
    }
}
