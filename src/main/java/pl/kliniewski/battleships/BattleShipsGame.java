package pl.kliniewski.battleships;

import pl.kliniewski.battleships.map.Map;
import pl.kliniewski.battleships.map.MapBuilder;
import pl.kliniewski.battleships.map.field.MapField;
import pl.kliniewski.battleships.map.field.MapShipField;
import pl.kliniewski.battleships.ship.Ship;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class BattleShipsGame
{
    private final Map map;

    private int sunkenShips;

    public BattleShipsGame(Map map)
    {
        this.map = map;
    }

    public static void main(String[] args)
    {
        MapBuilder mapBuilder = new MapBuilder();
        mapBuilder.appendRandomBattleship();
        mapBuilder.appendRandomDestroyer();
        mapBuilder.appendRandomDestroyer();
        BattleShipsGame shipsGame = new BattleShipsGame(mapBuilder.build());

        shipsGame.printIntro();
        try (Scanner scanner = new Scanner(System.in))
        {
            scanner.nextLine();

            shipsGame.printMap();

            String line;
            while ((line = scanner.nextLine()) != null)
            {
                shipsGame.executeMove(line);
            }
        }
    }

    private void printIntro()
    {
        System.out.println("");
        System.out.println("   ___       __  __  __        __   _        ");
        System.out.println("  / _ )___ _/ /_/ /_/ /__ ___ / /  (_)__  ___");
        System.out.println(" / _  / _ `/ __/ __/ / -_|_-</ _ \\/ / _ \\(_-<");
        System.out.println("/____/\\_,_/\\__/\\__/_/\\__/___/_//_/_/ .__/___/");
        System.out.println("             by Szymon Kliniewski /_/        ");
        System.out.println("");

        System.out.println("Legend:");
        System.out.println("  | . | - shows already shot fields");
        System.out.println("  | X | - shows already shot fields, which are ships");
        System.out.println("  |   | - shows fields, which are hidden");
        System.out.println("");

        System.out.println("Rules:");
        System.out.println("  1. The player enters or selects coordinates “A5”,");
        System.out.println("       where “A” is the column and “5” is the row");
        System.out.println("  2. You won when all enemy ships are sunk");
        System.out.println("  x. Type <ENTER> to start game");
        System.out.println("");
    }

    public void printMap()
    {
        MapField[][] fields = this.map.getFields();
        this.printHeader();
        for (int x = 0; x < fields.length; x++)
        {
            MapField[] row = fields[x];
            this.printRow(x, row);
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

        this.printMap();

        if (field instanceof MapShipField)
        {
            Ship ship = ((MapShipField) field).getShip();
            if (sunken)
            {
                System.out.printf("Hit and sink. %s.\n", ship.getName());
            }
            else
            {
                System.out.printf("Hit. %s.\n", ship.getName());
            }
        }
        else
        {
            System.out.println("Miss.");
        }

        if (this.map.isFinished())
        {
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println(" __     __          __          __         ");
            System.out.println(" \\ \\   / /          \\ \\        / /         ");
            System.out.println("  \\ \\_/ /__  _   _   \\ \\  /\\  / /__  _ __  ");
            System.out.println("   \\   / _ \\| | | |   \\ \\/  \\/ / _ \\| '_ \\ ");
            System.out.println("    | | (_) | |_| |    \\  /\\  / (_) | | | |");
            System.out.println("    |_|\\___/ \\__,_|     \\/  \\/ \\___/|_| |_|");
            System.out.println("                                           ");
            System.out.println("                                           ");
            System.exit(0);
        }
    }

    private void printHeader()
    {
        System.out.printf("Sunken ships: %d\n", this.sunkenShips);
        System.out.print(" ");
        for (int i = 0; i < 10; i++)
        {
            System.out.print(" | " + (char) ('A' + i));
        }
        System.out.println(" |");
    }

    private void printRow(int index, MapField[] row)
    {
        System.out.print((char) ('0' + index));
        for (int z = 0; z < row.length; z++)
        {
            MapField field = row[z];
            System.out.print(" | " + field.toChar());
        }
        System.out.println(" |");
    }
}
