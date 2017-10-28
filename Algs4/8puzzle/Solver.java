import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
  
  private boolean solvable;
  private SearchNode goalSearchNode;
    
  /**
   * SearchNode.
   */
  private class SearchNode implements Comparable<SearchNode> {
    private final Board board;
    private final int moves;
    private final SearchNode prevNode;

    public SearchNode(Board b, int m, SearchNode p) {
      board = b;
      moves = m;
      prevNode = p;
    }

    @Override
    public int compareTo(SearchNode that) {
      int thisManhattan = this.board.manhattan() + this.moves;
      int thatManhattan = that.board.manhattan() + that.moves;
      if ((thisManhattan - thatManhattan) == 0) {
        return this.moves - that.moves;
      } else {
        return thisManhattan - thatManhattan;
      }
    }
  }
    
  /**
   * find a solution to the initial board (using the A* algorithm).
   */
  public Solver(Board initial) {
    if (initial == null) {
      throw new IllegalArgumentException("Please enter a valid board");
    }
    SearchNode origSearchNode = new SearchNode(initial, 0, null);
    SearchNode twinSearchNode = new SearchNode(initial.twin(), 0, null);
    MinPQ<SearchNode> origPriorityQueue = new MinPQ<SearchNode>();
    MinPQ<SearchNode> twinPriorityQueue = new MinPQ<SearchNode>();
    while (true) {
      if (origSearchNode.board.isGoal()) {
        solvable = true;
        goalSearchNode = origSearchNode;
        break;
      } else if (twinSearchNode.board.isGoal()) {
        solvable = false;
        break;
      }

      for (Board bo: origSearchNode.board.neighbors()) {
        if (origSearchNode.prevNode == null || !bo.equals(origSearchNode.prevNode.board)) {
          origPriorityQueue.insert(new SearchNode(bo, origSearchNode.moves + 1, origSearchNode));
        } 
      }
      for (Board bo: twinSearchNode.board.neighbors()) {
        if (twinSearchNode.prevNode == null || !bo.equals(twinSearchNode.prevNode.board)) {
          twinPriorityQueue.insert(new SearchNode(bo, twinSearchNode.moves + 1, twinSearchNode));
        }
      }
      origSearchNode = origPriorityQueue.delMin();
      twinSearchNode = twinPriorityQueue.delMin();
    }
  }
    
  /**
   * is the initial board solvable.
   */
  public boolean isSolvable() {
    return solvable;
  }
  
  /**
   * minimum number of moves to solve initial board; -1 if unsolvable.
   */
  public int moves() {
    if (!this.isSolvable()) {
      return -1;
    }
    return goalSearchNode.moves;
  }
  
  /**
   * sequence of boards in a shortest solution; null if unsolvable.
   */
  public Iterable<Board> solution() {    
    if (this.isSolvable()) {
      Stack<Board> stack = new Stack<Board>();
      Board boa = goalSearchNode.board;
      SearchNode sn = goalSearchNode;
      while (true) {
        stack.push(boa); 
        sn = sn.prevNode;
        if (sn != null) {
          boa = sn.board;
        } else {
          break;
        }
      } 
      return stack;
    }
    return null;
  }
  
  /**
   * solve a slider puzzle (given ).
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
    Solver solver = new Solver(initial);
    if (!solver.isSolvable()) {
      StdOut.println("No solution possible");      
    } else {
      StdOut.println("Minimun number of moves = " + solver.moves());
      for (Board board: solver.solution()) {
        StdOut.println(board);
      }
    }
  }
}
