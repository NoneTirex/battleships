package pl.kliniewski.battleships;

import pl.kliniewski.battleships.map.Map;
import pl.kliniewski.battleships.map.MapField;

public class DisplayEngine
{
    public void printIntro()
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

    public void printMap(BattleShipsGame game)
    {
        MapField[][] fields = game.getMap().getFields();
        this.printHeader(game);
        for (int x = 0; x < fields.length; x++)
        {
            MapField[] row = fields[x];
            this.printRow(x + 1, row);
        }
    }

    public void printHeader(BattleShipsGame game)
    {
        System.out.print("  ");
        for (int i = 0; i < Map.MAP_SIZE; i++)
        {
            System.out.print(" | " + (char) ('A' + i));
        }
        System.out.println(" |");
    }

    public void printRow(int index, MapField[] row)
    {
        System.out.print(index);
        if (index < 10)
        {
            System.out.print(" ");
        }
        for (MapField field : row)
        {
            System.out.print(" | " + field.toChar());
        }
        System.out.println(" |");
    }

    public void printVictory()
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
    }

    public void printShot(MapField field)
    {
        field.getShip().ifPresentOrElse(ship ->
        {
            if (ship.isSunk())
            {
                System.out.printf("Hit and sink. %s.\n", ship.getName());
                return;
            }
            System.out.printf("Hit. %s.\n", ship.getName());
        }, () ->
        {
            System.out.println("Miss.");
        });
    }

    public void printFieldAlreadyShot()
    {
        System.out.println("The field is already shot");
    }

    public void printIncorrectMove(String input)
    {
        System.out.printf("Incorrect move! (your type: %s, example: A1)\n", input);
    }
}
