package pl.kliniewski.battleships.ship;

import pl.kliniewski.battleships.map.MapPosition;

public interface Ship
{
    String getName();

    MapPosition getStartPosition();

    int getSize();

    ShipDirection getDirection();
}
