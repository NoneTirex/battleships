package pl.kliniewski.battleships.map.field;

public class MapField
{
    private final int x;
    private final int z;
    private boolean alreadyHit;

    public MapField(int x, int z)
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

    public boolean isAlreadyHit()
    {
        return alreadyHit;
    }

    public void setAlreadyHit(boolean alreadyHit)
    {
        this.alreadyHit = alreadyHit;
    }
}
