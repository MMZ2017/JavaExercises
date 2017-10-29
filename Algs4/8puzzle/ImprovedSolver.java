import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class InprovedSolver {
  
  private final boolean solvable;
  private SearchNode goalSearchNode;
  private final Stack<Board> stack = new Stack<Board>();
    
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
      int gap = this.board.manhattan() + this.moves - that.board.manhattan() - that.moves;
      return gap == 0 ? this.board.manhattan() - that.board.manhattan() : gap;
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
    if (origSearchNode.board.isGoal()) {
      goalSearchNode = origSearchNode;
    } else if (twinSearchNode.board.isGoal()) {
      goalSearchNode = twinSearchNode;
    } else {
      MinPQ<SearchNode> priorityQueue = new MinPQ<SearchNode>();
      priorityQueue.insert(origSearchNode);
      priorityQueue.insert(twinSearchNode); 
      boolean exit = false;
      while (!exit) {
        SearchNode currentSearchNode = priorityQueue.delMin();
        for (Board bo: currentSearchNode.board.neighbors()) {
          if (currentSearchNode.prevNode == null || !bo.equals(currentSearchNode.prevNode.board)) {
            if (bo.isGoal()) {
              goalSearchNode = new SearchNode(bo, currentSearchNode.moves + 1, currentSearchNode);
              exit = true;
              break;
            }
            priorityQueue.insert(
                new SearchNode(bo, currentSearchNode.moves + 1, currentSearchNode));
          } 
        }
      }
    }
    SearchNode sn = goalSearchNode;    
    while (sn != null) {
      stack.push(sn.board);
      sn = sn.prevNode;
    } 
    solvable = stack.peek().equals(initial);
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
    return solvable ? goalSearchNode.moves : -1;
  }
  
  /**
   * sequence of boards in a shortest solution; null if unsolvable.
   */
  public Iterable<Board> solution() {    
    return solvable ? stack : null;
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
