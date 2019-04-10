package pl.kliniewski.battleships.ship;

import pl.kliniewski.battleships.map.MapDirection;
import pl.kliniewski.battleships.map.MapPosition;

public interface Ship
{
    int BATTLESHIP_SIZE = 5;
    int DESTROYER_SIZE = 4;

    String getName();

    MapPosition getStartPosition();

    int getSize();

    MapDirection getDirection();

    int getReceivedShots();

    boolean isSunk();

    void shootShip();
}
