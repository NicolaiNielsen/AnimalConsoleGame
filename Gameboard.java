import java.util.Random;

// Der er en edge case, hvor obstacles og animals spawner ovenpå hinanden, så bare genstart spillet.
// Det tager for lang tid at genere 3 unikke, tilfældige x,y koordinator (funktionen tager for lang tid at lave)

public class Gameboard {

  int boardsize;
  Object[][] gameboard;

  // constructor

  Gameboard(int boardsize) {
    this.boardsize = boardsize;
  }

  public int getBoardsize() {
    return boardsize;
  }

  public void init(Object[][] gameboard) {
    for (int i = 0; i < boardsize; i++) {
      for (int j = 0; j < boardsize; j++) {
        gameboard[i][j] = new Emptyfield();
      }
    }
  }

  public void printBoard(Object[][] gameboard) {
    for (int i = 0; i < boardsize; i++) {
      for (int j = 0; j < boardsize; j++) {
        System.out.print(gameboard[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }
}
