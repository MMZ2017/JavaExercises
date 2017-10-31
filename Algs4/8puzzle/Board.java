import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class Board {
  private final int manhattan;
  private final int hamming;
  private final int dimension;
  private final char[][] origBlock;
  private final String toString;
  private final int emptyRow;
  private final int emptyCol;
  
  
  /**
   * construct a board from an n-by-n array of blocks.
   */
  public Board(int[][] blocks) {
    int ham = 0;
    int di = blocks.length;
    int ma = 0;
    char [][] origB = new char[di][di]; 
    StringBuilder ts = new StringBuilder();
    ts.append(di + "\n");
    int emptyR = 0;
    int emptyC = 0;
    String stringLength = "%" + ((int) (Math.log10(di * di - 1) + 2)) + "d";
    for (int i = 0; i < di; i++) {
      for (int j = 0; j < di; j++) {
        if ((blocks[i][j] - di * i - j - 1) != 0 && blocks[i][j] != 0) {
          ham++;
          int row = (blocks[i][j] - 1) / di;
          int col = (blocks[i][j] - 1) % di;
          ma = ma + Math.abs(row - i) + Math.abs(col - j);
        } else if (blocks[i][j] == 0) {
          emptyR = i;
          emptyC = j;
        }
        origB[i][j] = (char) blocks[i][j];
        ts.append(String.format(stringLength, blocks[i][j]));
      }
      ts.append("\n");
    }     
    manhattan = ma;
    hamming = ham;
    dimension = di;
    emptyRow = emptyR;
    emptyCol = emptyC;
    origBlock = origB;
    toString = ts.toString();
  }
  
  /**
   * Board dimension n.
   */
  public int dimension() {
    return dimension;
  }
  
  /**
   * number of blocks out of place.
   */
  public int hamming() {
    return hamming;
  }
  
  /**
   * sum of Manhattan distances between blocks and goal.
   */
  public int manhattan() {
    return manhattan;
  }
  
  /**
   * is this board the goal board.
   */
  public boolean isGoal() {
    return hamming == 0;
  }
  
  /**
   * a board that is obtained by exchanging any pair of blocks.
   */
  public Board twin() {
    int[][] twinBlock = new int[dimension][dimension]; 
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        twinBlock[i][j] = (int) origBlock[i][j];
      }
    }
    boolean exit = false;
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension - 1; j++) {
        if (twinBlock[i][j] != 0 && twinBlock[i][j + 1] != 0) {
          int temp = twinBlock[i][j];
          twinBlock[i][j] = twinBlock[i][j + 1];
          twinBlock[i][j + 1] = temp;
          exit = true;
          break;
        }
      }
      if (exit) {
        break;
      }
    }  
    Board twin = new Board(twinBlock);
    return twin;
  }
  
  /**
   * does this board equal y.
   */
  public boolean equals(Object y) {
    if (this == y) {
      return true;
    }
    if (y == null) {
      return false;
    }
    if (y.getClass() != this.getClass()) {
      return false;
    }
    if (this.dimension() != ((Board) y).dimension()) {
      return false;
    } else {
      for (int i = 0; i < dimension; i++) {
        for (int j = 0; j < dimension; j++) {
          if (this.origBlock[i][j] != ((Board) y).origBlock[i][j]) {
            return false;
          }
        }
      }
      return true;
    }    
  }
  
  /**
   * swap two numbers.
   */
  private Board swap(int rowA, int colA, int rowB, int colB) {
    int[][] afterSwap = new int[dimension][dimension]; 
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        afterSwap[i][j] = (int) origBlock[i][j];
      }
    }
    int temp = afterSwap[rowA][colA];
    afterSwap[rowA][colA] = afterSwap[rowB][colB];
    afterSwap[rowB][colB] = temp;
    return new Board(afterSwap);
  }
  
  /**
   * all neighboring boards.
   */
  public Iterable<Board> neighbors() {
    Queue<Board> qu = new Queue<Board>();
    if (emptyRow > 0) {
      qu.enqueue(this.swap(emptyRow, emptyCol, emptyRow - 1, emptyCol));
    }
    if (emptyCol > 0) {
      qu.enqueue(this.swap(emptyRow, emptyCol, emptyRow, emptyCol - 1));
    }
    if (emptyRow < dimension - 1) {
      qu.enqueue(this.swap(emptyRow, emptyCol, emptyRow + 1, emptyCol));
    }
    if (emptyCol < dimension - 1) {
      qu.enqueue(this.swap(emptyRow, emptyCol, emptyRow, emptyCol + 1));
    }   
    return qu;
  }
  
  /**
   * string representation of this board (in the output format specified below).
   */
  public String toString() {
    return toString;
  }
  
  /** 
   * unit tests.
   */
  public static void main(String[] args) {
    In in = new In(args[0]);
    int n = in.readInt();
    int[][] blocks = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        blocks[i][j] = in.readInt();
      }
    }
    Board initial = new Board(blocks);
    Board comp = new Board(blocks);
    System.out.println(initial.toString() + "\n");
    System.out.println(initial.equals(comp));
    
    /* System.out.println(initial.manhattan());
    System.out.println(initial.twin());
    System.out.println(initial.neighbors().toString()); */
  }
}
