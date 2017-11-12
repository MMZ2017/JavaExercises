import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
  private KdNode root;
  private int size;
  
  /**
   * construct an empty set of points.
   */
  public KdTree() {
    root = null;
    size = 0;
  } 
  
  /**
   * private class KdNode.
   */
  private static class KdNode {
    private final Point2D p; // the point
    private final RectHV rect;  // the axis-aligned rectangle corresponding to the node
    private KdNode lb;  // the left/bottom subtree
    private KdNode rt;  // the right/top subtree
    private final boolean isVert;
    
    private KdNode(Point2D p, double x0, double y0, double x1, double y1, boolean isVert) {
      this.p = p;
      this.isVert = isVert;
      this.rect = new RectHV(x0, y0, x1, y1);
    }
  }
  
  /**
   * is the set empty.
   */
  public boolean isEmpty() {
    return size == 0;
  }
  
  /**
   * number of points in the set.
   */
  public int size() {
    return size;
  }
  
  /**
   * add the point to the set (if it is not already in the set).
   */
  public void insert(Point2D p) {
    if (p == null) {
      throw new IllegalArgumentException("Please enter a valid point");
    }
    root = insertHelper(root, p, 0, 0, 1, 1, true);
  }
  
  /**
   * insert helper function.
   */
  private KdNode insertHelper(
      KdNode current, Point2D p, double x0, double y0, double x1, double y1, boolean isVert) { 
    if (current == null) {
      size++;
      return new KdNode(p, x0, y0, x1, y1, isVert);      
    } else if (Double.compare(p.x(), current.p.x()) == 0 && Double.compare(p.y(), current.p.y()) == 0) { 
      return current;
    } else if (current.isVert) {
      if (p.x() < current.p.x()) {
        current.lb = insertHelper(current.lb, p, x0, y0, current.p.x(), y1, !isVert);
      } else {
        current.rt = insertHelper(current.rt, p, current.p.x(), y0, x1, y1, !isVert);
      }
    } else {
      if (p.y() < current.p.y()) {
        current.lb = insertHelper(current.lb, p, x0, y0, x1, current.p.y(), !isVert);
      } else {
        current.rt = insertHelper(current.rt, p, x0, current.p.y(), x1, y1, !isVert);
      }     
    }
    return current;
  }
  
  /**
   * does the set contain point p.
   */
  public boolean contains(Point2D p) {
    if (p == null) {
      throw new IllegalArgumentException("Please enter a valid point");
    }
    return containsHelper(root, p);
  }
  
  /**
   * contains helper.
   */
  private boolean containsHelper(KdNode current, Point2D p) {  
    if (current == null) {
      return false;
    } else if (current.p.compareTo(p) == 0) {
      return true;
    } else {
      if (current.isVert) {
        if (p.x() < current.p.x() && current.lb != null) {         
          return containsHelper(current.lb, p);             
        } else if (current.rt != null) {
          return containsHelper(current.rt, p);    
        }
      } else {
        if (p.y() < current.p.y() && current.lb != null) {
          return containsHelper(current.lb, p);
        } else if (current.rt != null) {
          return containsHelper(current.rt, p);          
        }
      }
      return false;
    }
  }
  
  /**
   * draw all points to standard draw.
   */
  public void draw() {
    drawHelper(root, true);
  }
  
  /**
   * draw helper.
   */
  private void drawHelper(KdNode k, boolean isVert) {
    if (k == null) {
      return;
    }
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.setPenRadius(0.01);
    k.p.draw();
    if (isVert) {
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.setPenRadius();
      StdDraw.line(k.p.x(), k.rect.ymin(), k.p.x(), k.rect.ymax());
    } else {
      StdDraw.setPenColor(StdDraw.BLUE);
      StdDraw.setPenRadius();
      StdDraw.line(k.rect.xmin(), k.p.y(), k.rect.xmax(), k.p.y());
    }
    drawHelper(k.lb, !isVert);
    drawHelper(k.rt, !isVert);
  }
  
  /**
   * all points that are inside the rectangle (or on the boundary). 
   */
  public Iterable<Point2D> range(RectHV rect) {
    Queue<Point2D> qu = new Queue<Point2D>();
    rangeHelper(root, rect, qu);
    return qu;
  }
  
  /**
   * range helper.
   */
  private void rangeHelper(KdNode current, RectHV rect, Queue<Point2D> qu) {
    if (current == null) {
      return;
    }
    if (rect.contains(current.p)) {
      qu.enqueue(current.p);
    }
    if (current.lb != null && rect.intersects(current.lb.rect)) {
      rangeHelper(current.lb, rect, qu);
    }
    if (current.rt != null && rect.intersects(current.rt.rect)) {
      rangeHelper(current.rt, rect, qu);
    }
  }
  
  /**
   * a nearest neighbor in the set to point p; null if the set is empty.
   */
  public Point2D nearest(Point2D p) {
    if (p == null) {
      throw new IllegalArgumentException("Please enter a valid point");
    }
    if (root == null) {
      return null;
    }
    return nearestHelper(root, root, p).p;
  }
  
  /**
   * nearest helper method.
   */
  private KdNode nearestHelper(KdNode current, KdNode closest, Point2D p) {  
    if (current == null) {
      return closest;
    }   
    double dis = current.p.distanceSquaredTo(p);   
    if (dis < closest.p.distanceSquaredTo(p)) {
      closest = current;
    } 
    if (current.isVert) {
      if (p.x() < current.p.x()) {
        if (current.lb != null && current.lb.rect.distanceSquaredTo(p) < closest.p.distanceSquaredTo(p)) {
          closest = nearestHelper(current.lb, closest, p);
        }
        if (current.rt != null && current.rt.rect.distanceSquaredTo(p) < closest.p.distanceSquaredTo(p)) {
          closest = nearestHelper(current.rt, closest, p);
        }   
      } else {
        if (current.rt != null && current.rt.rect.distanceSquaredTo(p) < closest.p.distanceSquaredTo(p)) {
          closest = nearestHelper(current.rt, closest, p);
        }
        if (current.lb != null && current.lb.rect.distanceSquaredTo(p) < closest.p.distanceSquaredTo(p)) {
          closest = nearestHelper(current.lb, closest, p);
        }       
      }
    } else {
      if (p.y() < current.p.y()) {
        if (current.lb != null && current.lb.rect.distanceSquaredTo(p) < closest.p.distanceSquaredTo(p)) {
          closest = nearestHelper(current.lb, closest, p);
        }
        if (current.rt != null && current.rt.rect.distanceSquaredTo(p) < closest.p.distanceSquaredTo(p)) {
          closest = nearestHelper(current.rt, closest, p);
        } 
      } else {
        if (current.rt != null && current.rt.rect.distanceSquaredTo(p) < closest.p.distanceSquaredTo(p)) {
          closest = nearestHelper(current.rt, closest, p);
        }
        if (current.lb != null && current.lb.rect.distanceSquaredTo(p) < closest.p.distanceSquaredTo(p)) {
          closest = nearestHelper(current.lb, closest, p);
        } 
      }
    }
    return closest;
  }
  
  /**
   * unit testing of the methods.
   */
  public static void main(String[] args) {
    KdTree kt = new KdTree();
    Point2D point1 = new Point2D(0.5, 0.5);
    Point2D point2 = new Point2D(0.25, 0.25);
    Point2D point3 = new Point2D(0.9, 0.3);
    kt.insert(point1);
    kt.insert(point2);
    System.out.println(kt.size());
    System.out.println(kt.root);
    System.out.println(kt.root.lb);
    System.out.println(kt.contains(point2));
    System.out.println(kt.contains(point3));
  }
}
