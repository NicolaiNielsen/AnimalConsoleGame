import java.util.Random;

public class Enemies {

  String symbol = "E";
  int id;
  int x, y;

  // constructor
  public Enemies(int id) {
    this.id = id;
  }

  public String toString() {
    return symbol;
  }

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

  public void spawnEnemies(Object[][] gameboard, int boardsize, Enemies b) {
    Random rand = new Random();
    int x = rand.nextInt(boardsize);
    int y = rand.nextInt(boardsize);

    b.setX(x);
    b.setY(y);

    gameboard[x][y] = b;
  }

  // method that compares what class the next tile is and then removes enemies or
  // the player or updates the position

  public void compareToObstacleAndPlayer(Object[][] gameboard, Enemies b, Player a, Wincondition run) {

    if (gameboard[b.getX()][b.getY()] instanceof Obstacle) {
      gameboard[b.getX()][b.getY()] = new Obstacle();
      System.out.println("You eliminated an Enemy! Keep going!");
    }
    if (gameboard[b.getX()][b.getY()] instanceof Player) {
      gameboard[b.getX()][b.getY()] = new Emptyfield();
      run.setWincondition(false);
      System.out.println("You collided with an enemy! Game over!");
    }
    if (gameboard[b.getX()][b.getY()] instanceof Enemies) {
      gameboard[b.getX()][b.getY()] = new Emptyfield();

    } else if (gameboard[b.getX()][b.getY()] instanceof Emptyfield) {
      gameboard[b.getX()][b.getY()] = b;
    }

  }

  // method to spawn elements and update position

  public void moveEnemiesY(Object[][] gameboard, Player a, Enemies b, int minusplus, Wincondition run) {
    gameboard[b.getX()][b.getY()] = new Emptyfield();
    b.setY(b.getY() + minusplus);
    compareToObstacleAndPlayer(gameboard, b, a, run);

  }

  public void moveEnemiesX(Object[][] gameboard, Player a, Enemies b, int minusplus, Wincondition run) {
    gameboard[b.getX()][b.getY()] = new Emptyfield();
    b.setX(b.getX() + minusplus);
    compareToObstacleAndPlayer(gameboard, b, a, run);

  }

  public void moveEnemiesXY(Object[][] gameboard, Player a, Enemies b, int minusplus, int plusminus, Wincondition run) {
    gameboard[b.getX()][b.getY()] = new Emptyfield();
    b.setX(b.getX() + minusplus);
    b.setY(b.getY() + plusminus);
    compareToObstacleAndPlayer(gameboard, b, a, run);
  }

  // method that checks current position of Player and Enemies and then moves
  // enemies to correct tile

  public void updatePosition(Object[][] gameboard, int boardsize, Player a, Enemies b, Wincondition run) {
    if (a.getX() == b.getX() && a.getY() > b.getY()) { // case 1: x = x, y > y, +1
      moveEnemiesY(gameboard, a, b, 1, run);
    }
    if (a.getX() == b.getX() && a.getY() < b.getY()) { // case 2: x = x, y < y, -1
      moveEnemiesY(gameboard, a, b, -1, run);
    }
    if (a.getX() < b.getX() && a.getY() == b.getY()) { // case 3: y = y, x < x, +1
      moveEnemiesX(gameboard, a, b, -1, run);
    }
    if (a.getX() > b.getX() && a.getY() == b.getY()) { // case 4: y = y, x > x, -1
      moveEnemiesX(gameboard, a, b, 1, run);
    }
    if (a.getX() > b.getX() && a.getY() > b.getY()) { // case 5: x > x, y > y, +x, +yrigh
      moveEnemiesXY(gameboard, a, b, 1, 1, run);
    }
    if (a.getX() > b.getX() && a.getY() < b.getY()) { // case 6: x > x, y < y, +x,-y
      moveEnemiesXY(gameboard, a, b, 1, -1, run);
    }
    if (a.getX() < b.getX() && a.getY() > b.getY()) { // case 7: x < x, y > y, -x, +y
      moveEnemiesXY(gameboard, a, b, -1, 1, run);
    }
    if (a.getX() < b.getX() && a.getY() < b.getY()) { // case 8: x < x, y < y, -x, -y
      moveEnemiesXY(gameboard, a, b, -1, -1, run);
    }
  }
}
