package pl.kliniewski.battleships.map;

import pl.kliniewski.battleships.ship.Ship;

import java.util.Random;

public class MapBuilder
{
    private final Map    map    = new Map();
    private       Random random = new Random();

    public MapBuilder appendRandomBattleship()
    {
        int battleShipSize = 5;
        return this.appendRandomShip("Battleship", battleShipSize);
    }

    MapBuilder appendRandomShip(String name, int size)
    {
        MapDirection direction = this.randomDirection();
        MapPosition position = this.randomPosition(size, direction);
        this.map.addShip(new Ship(name, position, size, direction));
        return this;
    }

    MapDirection randomDirection()
    {
        int index = this.random.nextInt(MapDirection.values().length);
        return MapDirection.values()[index];
    }

    MapPosition randomPosition(int size, MapDirection direction)
    {
        MapPosition offset = direction.getPosition().multiple(size);
        int minX = Math.max(0, 0 - offset.getX());
        int maxX = Math.min(Map.MAP_SIZE, Map.MAP_SIZE - offset.getX());
        int minZ = Math.max(0, 0 - offset.getZ());
        int maxZ = Math.min(Map.MAP_SIZE, Map.MAP_SIZE - offset.getZ());
        MapPosition position;
        do
        {
            int x = this.nextInt(minX, maxX);
            int z = this.nextInt(minZ, maxZ);

            position = new MapPosition(x, z);
        } while (this.map.collidedOtherShips(position, size, direction));
        return position;
    }

    int nextInt(int origin, int bound)
    {
        return this.random.nextInt(bound - origin) + origin;
    }

    public MapBuilder appendRandomDestroyer()
    {
        int destroyerSize = 4;
        return this.appendRandomShip("Destroyer", destroyerSize);
    }

    public Map build()
    {
        return this.map;
    }

    void setRandom(Random random)
    {
        this.random = random;
    }
}
