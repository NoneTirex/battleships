package pl.kliniewski.battleships;

import pl.kliniewski.battleships.map.Map;
import pl.kliniewski.battleships.map.MapBuilder;
import pl.kliniewski.battleships.map.MapField;
import pl.kliniewski.battleships.map.MapPosition;

import java.util.Scanner;

public class BattleShipsGame
{
    private final DisplayEngine display = new DisplayEngine(this);
    private final Map           map;

    private int sunkenShips;

    public BattleShipsGame(Map map)
    {
        this.map = map;
    }

    public Map getMap()
    {
        return map;
    }

    public int getSunkenShips()
    {
        return sunkenShips;
    }

    public static void main(String[] args)
    {
        MapBuilder mapBuilder = new MapBuilder();
        mapBuilder.appendRandomBattleship().appendRandomDestroyer().appendRandomDestroyer();

        BattleShipsGame shipsGame = new BattleShipsGame(mapBuilder.build());

        shipsGame.display.printIntro();
        try (Scanner scanner = new Scanner(System.in))
        {
            scanner.nextLine();

            shipsGame.display.printMap();

            while (scanner.hasNextLine())
            {
                shipsGame.preprocessInput(scanner.nextLine());
            }
        }
    }

    private void preprocessInput(String line)
    {
        MapPosition position = this.parseMove(line);
        if (position == null)
        {
            System.out.printf("Incorrect move! (your type: %s, example: A1)\n", line);
            return;
        }
        this.executeMove(position);
    }

    private void executeMove(MapPosition position)
    {
        MapField field = this.map.getField(position.getX(), position.getZ());
        if (!this.interactField(field))
        {
            System.out.println("The field is already shot");
            return;
        }

        this.display.printMap();
        this.display.printShoot(field);

        if (this.map.isFinished())
        {
            this.display.printVictory();
            System.exit(0);
        }
    }

    MapPosition parseMove(String coordinates)
    {
        if (coordinates.length() != 2)
        {
            return null;
        }
        char[] chars = coordinates.toUpperCase().toCharArray();
        int x = chars[0] - 'A';
        int z = chars[1] - '0';

        if (x < 0 || x >= Map.MAP_SIZE || z < 0 || z >= Map.MAP_SIZE)
        {
            return null;
        }
        return new MapPosition(x, z);
    }

    boolean interactField(MapField field)
    {
        if (field.isAlreadyHit())
        {
            return false;
        }
        field.shootField();

        if (field.getShip() != null && field.getShip().isSunk())
        {
            this.sunkenShips++;
        }
        return true;
    }
}
