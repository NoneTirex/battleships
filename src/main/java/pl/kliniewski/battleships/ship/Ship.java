package pl.kliniewski.battleships.ship;

import pl.kliniewski.battleships.map.MapDirection;
import pl.kliniewski.battleships.map.MapPosition;

public interface Ship
{
    String getName();

    MapPosition getStartPosition();

    int getSize();

    MapDirection getDirection();

    int getReceivedShots();

    boolean isShotted();

    void shootShip();
}
