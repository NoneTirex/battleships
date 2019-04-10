package pl.kliniewski.battleships.map;

import pl.kliniewski.battleships.ship.BattleShip;
import pl.kliniewski.battleships.ship.DestroyerShip;
import pl.kliniewski.battleships.ship.Ship;

import java.util.concurrent.ThreadLocalRandom;

public class MapBuilder
{
    private final Map map = new Map();

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

    private MapDirection randomDirection()
    {
        int index = ThreadLocalRandom.current().nextInt(MapDirection.values().length);
        return MapDirection.values()[index];
    }

    public MapBuilder appendRandomBattleship()
    {
        MapDirection direction = this.randomDirection();
        this.map.addShip(new BattleShip(this.randomPosition(Ship.BATTLESHIP_SIZE, direction), direction));
        return this;
    }

    public MapBuilder appendRandomDestroyer()
    {
        MapDirection direction = this.randomDirection();
        this.map.addShip(new DestroyerShip(this.randomPosition(Ship.DESTROYER_SIZE, direction), direction));
        return this;
    }

    public Map build()
    {
        return this.map;
    }
}
