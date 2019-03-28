package pl.kliniewski.battleships.map.field;

public class MapField
{
    private boolean alreadyHit;

    public boolean isAlreadyHit()
    {
        return alreadyHit;
    }

    public void setAlreadyHit(boolean alreadyHit)
    {
        this.alreadyHit = alreadyHit;
    }

    public void shootField()
    {
        this.alreadyHit = true;
    }

    public char toChar()
    {
        return this.alreadyHit ? '.' : ' ';
    }
}
