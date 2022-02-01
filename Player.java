import java.util.Random;

public class Player {

  String symbol = "A";
  String name;
  int x, y;
  boolean loosecondition = false;

  // constructor
  public Player(String name) {
    this.name = name;
  }

  // getters and setters for x and y

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void setX(int xpos) {
    this.x = xpos;
  }

  public void setY(int ypos) {
    this.y = ypos;
  }

  public String getName() {
    return name;
  }

  public String toString() {
    return symbol;
  }

  public void spawnPlayer(Object[][] gameboard, int boardsize, Player a) {
    Random rand = new Random();
    int x = rand.nextInt(boardsize);
    int y = rand.nextInt(boardsize);

    a.setX(x);
    a.setY(y);
    gameboard[x][y] = a;
  }

  // compare objects, så vi ikke går ind i obstacles eller enemies = død

  public void compareToObstacleAndEnemies(Object[][] gameboard, Player a, Wincondition rungame) {
    if (gameboard[a.getX()][a.getY()] instanceof Obstacle) {
      rungame.setWincondition(false);
      System.out.println("You collided with an Obstacle! Game over!");

    }
    if (gameboard[a.getX()][a.getY()] instanceof Enemies) {
      rungame.setWincondition(false);
      System.out.println("You collided with an Enemy! Game over!");
    }
  }

  // bouncy border metode, så vi undgår index out of range exceptions

  public void borders(Object[][] gameboard, int boardsize, int checkborderx, int checkbordery, Player a,
      Wincondition rungame) {
    if (checkborderx > boardsize - 1) {
      int borderposx = a.getX() - 2;
      a.setX(borderposx);
      gameboard[a.getX()][a.getY()] = a;
    }
    if (checkbordery > boardsize - 1) {
      int borderposy = a.getY() - 2;
      a.setY(borderposy);
      gameboard[a.getX()][a.getY()] = a;
    }
    if (checkborderx < 0) {
      int borderposx = a.getX() + 2;
      a.setX(borderposx);
      gameboard[a.getX()][a.getY()] = a;
    }
    if (checkbordery < 0) {
      int borderposy = a.getY() + 2;
      a.setY(borderposy);
      gameboard[a.getX()][a.getY()] = a;
    } else {
      compareToObstacleAndEnemies(gameboard, a, rungame);
      gameboard[a.getX()][a.getY()] = a;
    }

  }

  public void movePlayer(Object[][] gameboard, String userinput, int boardsize, Player a, Wincondition rungame) {
    if (userinput.equals("down")) {
      gameboard[a.getX()][a.getY()] = new Emptyfield();
      int newpos = a.getX() + 1;
      a.setX(newpos);
      int checkborderx = a.getX();
      int checkbordery = a.getY();
      borders(gameboard, boardsize, checkborderx, checkbordery, a, rungame);
      compareToObstacleAndEnemies(gameboard, a, rungame);
    }
    if (userinput.equals("up")) {
      gameboard[a.getX()][a.getY()] = new Emptyfield();
      int newpos = a.getX() - 1;
      a.setX(newpos);
      int checkborderx = a.getX();
      int checkbordery = a.getY();
      borders(gameboard, boardsize, checkborderx, checkbordery, a, rungame);
      compareToObstacleAndEnemies(gameboard, a, rungame);
    }
    if (userinput.equals("left")) {
      gameboard[a.getX()][a.getY()] = new Emptyfield();
      int newpos = a.getY() - 1;
      a.setY(newpos);
      int checkborderx = a.getX();
      int checkbordery = a.getY();
      borders(gameboard, boardsize, checkborderx, checkbordery, a, rungame);
      compareToObstacleAndEnemies(gameboard, a, rungame);
    }
    if (userinput.equals("right")) {
      gameboard[a.getX()][a.getY()] = new Emptyfield();
      int newpos = a.getY() + 1;
      a.setY(newpos);
      int checkborderx = a.getX();
      int checkbordery = a.getY();
      borders(gameboard, boardsize, checkborderx, checkbordery, a, rungame);
      compareToObstacleAndEnemies(gameboard, a, rungame);
    }
    if (userinput.equals("jump")) {
      gameboard[a.getX()][a.getY()] = new Emptyfield();
      Random rand = new Random();
      a.setX(x = rand.nextInt(boardsize));
      a.setX(y = rand.nextInt(boardsize));
      gameboard[a.getX()][a.getY()] = a;
    }
  }
}
