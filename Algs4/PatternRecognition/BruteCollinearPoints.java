import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class BruteCollinearPoints {
  
  private final int numOfSegments;
  private final LineSegment[] segments;
  
  /**
   * finds all line segments containing 4 points.
   */
  public BruteCollinearPoints(Point[] points) {
    if (points == null || points.length == 0) {
      throw new IllegalArgumentException("The input must be an array of points");
    }
    for (Point p: points) {
      if (p == null) {
        throw new IllegalArgumentException("The input must be an array of points");
      }
    }
    for (int p = 0; p < points.length - 1; p++) {
      for (int q = p + 1; q < points.length; q++) {
        if (points[p].compareTo(points[q]) == 0) {
          throw new IllegalArgumentException("There are duplicate points.");
        }
      }
    }
    
    LineSegment[] seglocal = new LineSegment[points.length];
    int numlocal = 0;
    for (int i = 0; i < points.length - 3; i++) {
      for (int j = i + 1; j < points.length - 2; j++) {
        for (int k = j + 1; k < points.length - 1; k++) {
          for (int m = k + 1; m < points.length; m++) {
            double slope = points[i].slopeTo(points[j]);
            if (Double.compare(slope, points[i].slopeTo(points[k])) == 0 
                && Double.compare(slope, points[i].slopeTo(points[m])) == 0) {
              Point max = points[i];
              Point min = points[i];
              Point[] assist = {points[i], points[j], points[k], points[m]};
              for (Point p: assist) {
                if (p.compareTo(min) < 0) {
                  min = p;
                } else if (p.compareTo(max) > 0) {
                  max = p;
                }
              }
              seglocal[numlocal] = new LineSegment(min, max);
              numlocal++;
            }
          }
        }
      }
    }
    segments = Arrays.copyOf(seglocal, seglocal.length);
    numOfSegments = numlocal;
  }

  /**
   * the number of line segments.
   */
  public int numberOfSegments() {
    return numOfSegments;
  }
  
  /**
   * the line segments.
   */
  public LineSegment[] segments() {
    return Arrays.copyOf(segments, numOfSegments);
  }
  
  /**
   * Testing.
   */
  public static void main(String[] args) {

    // read the n points from a file
    In in = new In(args[0]);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      int x = in.readInt();
      int y = in.readInt();
      points[i] = new Point(x, y);
    }

    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
      p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
    StdDraw.show();
  }
}
