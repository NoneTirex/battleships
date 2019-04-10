package pl.kliniewski.battleships;

import pl.kliniewski.battleships.map.Map;
import pl.kliniewski.battleships.map.MapBuilder;
import pl.kliniewski.battleships.map.field.MapField;
import pl.kliniewski.battleships.map.field.MapShipField;

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
        mapBuilder.appendRandomBattleship();
        mapBuilder.appendRandomDestroyer();
        mapBuilder.appendRandomDestroyer();
        BattleShipsGame shipsGame = new BattleShipsGame(mapBuilder.build());

        shipsGame.display.printIntro();
        try (Scanner scanner = new Scanner(System.in))
        {
            scanner.nextLine();

            shipsGame.display.printMap();

            String line;
            while ((line = scanner.nextLine()) != null)
            {
                shipsGame.executeMove(line);
            }
        }
    }

    public void executeMove(String coordinates)
    {
        if (coordinates.length() != 2)
        {
            System.out.printf("Incorrect move! (your type: %s, example: A1)\n", coordinates);
            return;
        }
        char[] chars = coordinates.toUpperCase().toCharArray();
        int x = chars[0] - 'A';
        int z = chars[1] - '0';

        if (x < 0 || x >= 10)
        {
            System.out.printf("Incorrect move! (incorrect first sentence, your type: %s\n", chars[0]);
            return;
        }
        if (z < 0 || z >= 10)
        {
            System.out.printf("Incorrect move! (incorrect second sentence, your type: %s\n", chars[1]);
            return;
        }
        MapField field = this.map.getField(x, z);
        if (field.isAlreadyHit())
        {
            System.out.println("The field is already shot");
            return;
        }
        field.shootField();

        boolean sunken = field instanceof MapShipField && ((MapShipField) field).getShip().isSunk();
        if (sunken)
        {
            this.sunkenShips++;
        }

        this.display.printMap();
        this.display.printShoot(field);

        if (this.map.isFinished())
        {
            this.display.printVictory();
            System.exit(0);
        }
    }
}
