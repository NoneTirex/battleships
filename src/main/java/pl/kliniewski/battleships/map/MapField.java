package pl.kliniewski.battleships.map;

import pl.kliniewski.battleships.ship.Ship;

public class MapField
{
    private final Ship ship;

    private boolean alreadyHit;

    public MapField()
    {
        this(null);
    }

    public MapField(Ship ship)
    {
        this.ship = ship;
    }

    public Ship getShip()
    {
        return ship;
    }

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
        if (this.ship != null)
        {
            this.ship.shootShip();
        }
    }

    public char toChar()
    {
        return this.alreadyHit ? (this.ship != null ? 'X' : '.') : ' ';
    }
}
