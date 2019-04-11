package pl.kliniewski.battleships.map;

import pl.kliniewski.battleships.ship.Ship;

import java.util.concurrent.ThreadLocalRandom;

public class MapBuilder
{
    private final Map map = new Map();

    public MapBuilder appendRandomBattleship()
    {
        int battleShipSize = 5;

        MapDirection direction = this.randomDirection();
        MapPosition position = this.randomPosition(battleShipSize, direction);
        this.map.addShip(new Ship("Battleship", position, battleShipSize, direction));
        return this;
    }

    private MapDirection randomDirection()
    {
        int index = ThreadLocalRandom.current().nextInt(MapDirection.values().length);
        return MapDirection.values()[index];
    }

    private MapPosition randomPosition(int size, MapDirection direction)
    {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        MapPosition offset = direction.getPosition().multiple(5);
        int minX = Math.max(0, 0 - offset.getX());
        int maxX = Math.min(10, 10 - offset.getX());
        int minZ = Math.max(0, 0 - offset.getZ());
        int maxZ = Math.min(10, 10 - offset.getZ());
        MapPosition position;
        do
        {
            int x = random.nextInt(minX, maxX);
            int z = random.nextInt(minZ, maxZ);

            position = new MapPosition(x, z);
        } while (this.map.collidedOtherShips(position, size, direction));
        return position;
    }

    public MapBuilder appendRandomDestroyer()
    {
        int destroyerSize = 4;

        MapDirection direction = this.randomDirection();
        MapPosition position = this.randomPosition(destroyerSize, direction);
        this.map.addShip(new Ship("Destroyer", position, destroyerSize, direction));
        return this;
    }

    public Map build()
    {
        return this.map;
    }
}
