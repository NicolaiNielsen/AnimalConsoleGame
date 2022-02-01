import java.util.Scanner;

public class Game {

  Scanner scanner = new Scanner(System.in);
  int boardsize = 10;
  boolean gameend = true;
  boolean setwin = true;;

  // declare 2d array of object as gameboard
  Object gameboard[][] = new Object[boardsize][boardsize];

  // create objects
  Player a = new Player("Playername");
  Obstacle ob1 = new Obstacle();
  Obstacle ob2 = new Obstacle();
  Obstacle ob3 = new Obstacle();
  Enemies e = new Enemies(1);
  Enemies r = new Enemies(2);
  Gameboard game = new Gameboard(boardsize);
  Wincondition rungame = new Wincondition(true);

  Game() {
    // spawn player, obstacles and enemies
    game.init(gameboard);
    a.spawnPlayer(gameboard, boardsize, a);
    ob1.spawnObstacle(gameboard, boardsize, ob1);
    ob2.spawnObstacle(gameboard, boardsize, ob2);
    ob3.spawnObstacle(gameboard, boardsize, ob2);
    e.spawnEnemies(gameboard, boardsize, e);
    r.spawnEnemies(gameboard, boardsize, r);
    startGame();
  }

  public void startGame() {
    System.out.println("Move the player A by typing up, left, right or down in the console");
    System.out
        .println("If the board doesnt contain 3 obstacles, 1 player or 2 enemies on launch, feel free to restart");

    game.printBoard(gameboard);
    Scanner in = new Scanner(System.in);

    while (rungame.getWincondition()) {

      String userinput = in.nextLine();
      a.movePlayer(gameboard, userinput, boardsize, a, rungame);

      checkwinconditionEnemies(gameboard, e, r);

      if (continueUpdate(gameboard, e)) {
        e.updatePosition(gameboard, boardsize, a, e, rungame);
      }

      if (continueUpdate(gameboard, r)) {
        r.updatePosition(gameboard, boardsize, a, r, rungame);
      }
      game.printBoard(gameboard);
    }
  }

  public boolean continueUpdate(Object[][] gameboard, Enemies e) {

    boolean continueUpdate = true;
    int counter = 0;

    for (int i = 0; i < boardsize; i++) {
      for (int j = 0; j < boardsize; j++) {
        if (gameboard[i][j] == e) {
          counter++;
        }
      }
    }

    if (counter == 1) {
      continueUpdate = true;
    } else if (counter == 0) {
      continueUpdate = false;
    }
    return continueUpdate;
  }

  public boolean checkwinconditionEnemies(Object[][] gameboard, Enemies e, Enemies r) {

    int counter = 2;
    boolean wincondition;

    for (int i = 0; i < boardsize; i++) {
      for (int j = 0; j < boardsize; j++) {
        if (gameboard[i][j] == e) {
          counter--;
        }
        if (gameboard[i][j] == r) {
          counter--;
        }
      }
    }

    if (counter == 2) {
      System.out.println("You won!");
      wincondition = true;
      rungame.setWincondition(false);
    } else {
      wincondition = false;
    }

    return wincondition;
  }
}
