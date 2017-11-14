import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Iterator;
import java.util.TreeSet;

public class PointSET {
  private final TreeSet<Point2D> points;
  
  /**
   * construct an empty set of points.
   */
  public PointSET() {
    points = new TreeSet<Point2D>();
  } 
  
  /**
   * is the set empty.
   */
  public boolean isEmpty() {
    return points.isEmpty();
  }
  
  /**
   * number of points in the set.
   */
  public int size() {
    return points.size();
  }
  
  /**
   * add the point to the set (if it is not already in the set).
   */
  public void insert(Point2D p) {
    if (p == null) {
      throw new IllegalArgumentException("Please enter a valid point");
    }
    points.add(p);
  }
  
  /**
   * does the set contain point p.
   */
  public boolean contains(Point2D p) {
    if (p == null) {
      throw new IllegalArgumentException("Please enter a valid point");
    }
    return points.contains(p);
  }
  
  /**
   * draw all points to standard draw.
   */
  public void draw() {
    Iterator<Point2D> it = points.iterator();
    while (it.hasNext()) {
      Point2D p = it.next();
      StdDraw.point(p.x(), p.y());
    }
  }
  
  /**
   * all points that are inside the rectangle (or on the boundary). 
   */
  public Iterable<Point2D> range(RectHV rect) {
    Queue<Point2D> qu = new Queue<Point2D>();
    Iterator<Point2D> it = points.iterator();
    while (it.hasNext()) {
      Point2D p = it.next();
      if (rect.contains(p)) {
        qu.enqueue(p);
      }
    }
    return qu;
  }
  
  /**
   * a nearest neighbor in the set to point p; null if the set is empty.
   */
  public Point2D nearest(Point2D p) {
    if (p == null) {
      throw new IllegalArgumentException("Please enter a valid point");
    }
    if (points.isEmpty()) {
      return null;
    }
    Point2D nearest = points.first();
    Iterator<Point2D> iter = points.iterator();
    while (iter.hasNext()) {
      Point2D temp = iter.next();
      double dis = temp.distanceSquaredTo(p);
      if (dis < nearest.distanceSquaredTo(p)) {
        nearest = temp;
      }
    }
    return nearest;
  }
  
  /*
  public static void main(String[] args) {
    // initialize the data structures from file
    String filename = args[0];
    In in = new In(filename);
    PointSET brute = new PointSET();
    while (!in.isEmpty()) {
      double x = in.readDouble();
      double y = in.readDouble();
      Point2D p = new Point2D(x, y);
      brute.insert(p);
    }

    double x0 = 0.0, y0 = 0.0;      // initial endpoint of rectangle
    double x1 = 0.0, y1 = 0.0;      // current location of mouse
    boolean isDragging = false;     // is the user dragging a rectangle

    // draw the points
    StdDraw.clear();
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.setPenRadius(0.01);
    brute.draw();
    StdDraw.show();

    // process range search queries
    StdDraw.enableDoubleBuffering();
    while (true) {

      // user starts to drag a rectangle
      if (StdDraw.isMousePressed() && !isDragging) {
        x0 = x1 = StdDraw.mouseX();
        y0 = y1 = StdDraw.mouseY();
        isDragging = true;
      } else if (StdDraw.isMousePressed() && isDragging) {
        x1 = StdDraw.mouseX();
        y1 = StdDraw.mouseY();
      } else if (!StdDraw.isMousePressed() && isDragging) {
        isDragging = false;
      }

      // draw the points
      StdDraw.clear();
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.setPenRadius(0.01);
      brute.draw();

      // draw the rectangle
      RectHV rect = new RectHV(Math.min(x0, x1), Math.min(y0, y1),
                               Math.max(x0, x1), Math.max(y0, y1));
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.setPenRadius();
      rect.draw();

      // draw the range search results for brute-force data structure in red
      StdDraw.setPenRadius(0.03);
      StdDraw.setPenColor(StdDraw.RED);
      for (Point2D p : brute.range(rect)) {
        p.draw();
      }
        
      StdDraw.show();
      StdDraw.pause(20);
    }
  } */
}
