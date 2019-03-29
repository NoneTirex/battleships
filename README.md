# Battleships
This game is a simple implementation of the Battleships in Java as Console Application.
The game board is a 10x10 grid. You can hit the squares by entering the coordinates with `A1 - J10` format.
Game ends when all the ships are sunk.

Full instruction: https://www.hasbro.com/common/instruct/Battleship.PDF

## How to build

You require the following to build:
- Latest stable [Oracle JDK 11](http://www.oracle.com/technetwork/java/)
- Latest stable [Apache Maven](http://maven.apache.org/)

Command to build with maven:
```
mvn clean package
```


## Running the tests
To run the tests, call `mvn test`


## Start game

#### In IDE
Create a Java application that main class is `pl.kliniewski.battleships.BattleShipsGame` and working directory is your project root folder.

#### Command Line
```
java -jar target/battleships-1.0-SNAPSHOT.jar
```

## TODO
- [ ] implement SALVO for more experienced players