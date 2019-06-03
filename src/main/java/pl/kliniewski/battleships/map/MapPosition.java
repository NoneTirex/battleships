package pl.kliniewski.battleships.map;

import java.util.Objects;

public class MapPosition
{
    private final int x;
    private final int z;

    public MapPosition(int x, int z)
    {
        this.x = x;
        this.z = z;
    }

    public int getX()
    {
        return x;
    }

    public int getZ()
    {
        return z;
    }

    public MapPosition add(MapPosition position)
    {
        return new MapPosition(this.x + position.getX(), this.z + position.getZ());
    }

    public MapPosition multiple(int multiplier)
    {
        return new MapPosition(this.x * multiplier, this.z * multiplier);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof MapPosition))
        {
            return false;
        }
        MapPosition position = (MapPosition) o;
        return x == position.x && z == position.z;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, z);
    }
}
