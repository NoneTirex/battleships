package pl.kliniewski.battleships.map;

import pl.kliniewski.battleships.ShotResult;
import pl.kliniewski.battleships.ship.Ship;

import java.util.Optional;

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

    public Optional<Ship> getShip()
    {
        return Optional.ofNullable(this.ship);
    }

    public boolean isAlreadyHit()
    {
        return alreadyHit;
    }

    public void setAlreadyHit(boolean alreadyHit)
    {
        this.alreadyHit = alreadyHit;
    }

    public ShotResult shootField()
    {
        if (alreadyHit)
        {
            return ShotResult.DUPLICATE;
        }
        alreadyHit = true;

        return this.getShip().map(Ship::shootShip).orElse(ShotResult.SHOT);
    }

    public char toChar()
    {
        return this.alreadyHit ? this.getShip().map(s -> 'X').orElse('.') : ' ';
    }
}
