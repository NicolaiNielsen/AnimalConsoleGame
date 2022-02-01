import java.util.Random;

public class Obstacle {

  String symbol = "O";
  int id;
  int x, y;

  // constructor
  public Obstacle() {

  }

  public String toString() {
    return symbol;
  }

  public void spawnObstacle(Object[][] gameboard, int boardsize, Obstacle A) {
    Random rand = new Random();
    int x = rand.nextInt(boardsize);
    int y = rand.nextInt(boardsize);
    gameboard[x][y] = A;
  }
}
