package pl.kliniewski.battleships.map;

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
}
