package pl.kliniewski.battleships;

import pl.kliniewski.battleships.map.Map;
import pl.kliniewski.battleships.map.MapBuilder;
import pl.kliniewski.battleships.map.MapField;
import pl.kliniewski.battleships.map.MapPosition;

import java.util.Scanner;

public class BattleShipsGame
{
    private final DisplayEngine display;
    private final Map           map;

    public BattleShipsGame(DisplayEngine display, Map map)
    {
        this.display = display;
        this.map = map;
    }

    public Map getMap()
    {
        return map;
    }

    public static void main(String[] args)
    {
        MapBuilder mapBuilder = new MapBuilder();
        mapBuilder.appendRandomBattleship().appendRandomDestroyer().appendRandomDestroyer();

        BattleShipsGame shipsGame = new BattleShipsGame(new DisplayEngine(), mapBuilder.build());
        shipsGame.display.printIntro();

        try (Scanner scanner = new Scanner(System.in))
        {
            scanner.nextLine();

            shipsGame.display.printMap(shipsGame);
            while (!shipsGame.isFinished() && scanner.hasNextLine())
            {
                shipsGame.preprocessInput(scanner.nextLine());
            }
        }
    }

    public boolean isFinished()
    {
        return this.map.isFinished();
    }

    void preprocessInput(String line)
    {
        MapPosition position = this.parseMove(line);
        if (position == null)
        {
            this.display.printIncorrectMove(line);
            return;
        }
        this.executeMove(position);
    }

    MapPosition parseMove(String coordinates)
    {
        if (coordinates.length() < 2)
        {
            return null;
        }
        int x = coordinates.toUpperCase().toCharArray()[0] - 'A';
        int z;
        try
        {
            z = Integer.parseInt(coordinates.substring(1)) - 1;
        }
        catch (NumberFormatException ignored)
        {
            return null;
        }
        if (x < 0 || x >= Map.MAP_SIZE || z < 0 || z >= Map.MAP_SIZE)
        {
            return null;
        }
        return new MapPosition(x, z);
    }

    void executeMove(MapPosition position)
    {
        MapField field = this.map.getField(position.getX(), position.getZ());
        ShotResult result = field.shootField();
        if (result == ShotResult.DUPLICATE)
        {
            this.display.printFieldAlreadyShot();
            return;
        }

        this.display.printMap(this);
        this.display.printShot(field);

        if (this.isFinished())
        {
            this.display.printVictory();
        }
    }

    Integer parseInt(String input)
    {
        try
        {
            return Integer.parseInt(input);
        }
        catch (NumberFormatException ignored)
        {
        }
        return null;
    }
}
