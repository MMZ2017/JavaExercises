import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class FastCollinearPoints {
  
  private final int numOfSegments;
  private final LineSegment[] segments;
  
  /**
   * finds all line segments containing 4 or more points.
   */
  public FastCollinearPoints(Point[] points) {   
    if (points == null || points.length == 0) {
      throw new IllegalArgumentException("The input must be an array of points");
    }
    for (Point p: points) {
      if (p == null) {
        throw new IllegalArgumentException("The input must be an array of points");
      }
    }
    for (int i = 0; i < points.length - 1; i++) {
      for (int j = i + 1; j < points.length; j++) {
        if (points[i].compareTo(points[j]) == 0) {
          throw new IllegalArgumentException("There are duplicate points.");
        }
      }
    }
    
    List<Point> maxArr = new ArrayList<Point>(); 
    LineSegment[] seglocal = new LineSegment[points.length];
    int numlocal = 0;      
    for (int i = 0; i < points.length - 3; i++) {  
      Comparator<Point> comp = points[i].slopeOrder();
      Point[] copy = Arrays.copyOfRange(points, i, points.length);
      Arrays.sort(copy, comp);     
      List<Point> pointRecord = new ArrayList<Point>();
      double slope = 0;
      double prevSlope = points[i].slopeTo(copy[0]);
      for (int j = 1; j < copy.length; j++) {
        slope = points[i].slopeTo(copy[j]);
        if (Double.compare(slope, prevSlope) == 0) {
          pointRecord.add(copy[j]);
        } else {          
          if (pointRecord.size() >= 3) {
            pointRecord.add(points[i]);
            Point max = points[i];
            Point min = points[i];
            for (Point p: pointRecord) {
              if (p.compareTo(min) < 0) {
                min = p;
              } else if (p.compareTo(max) > 0) {
                max = p;
              }
            }          
            if (!isDuplicate(maxArr, max)) {
              maxArr.add(max);
              seglocal[numlocal] = new LineSegment(min, max);
              numlocal++;
            }
          }
          prevSlope = slope;
          pointRecord.clear();
          pointRecord.add(copy[j]);
        }    
      }
      if (pointRecord.size() >= 3) {
        pointRecord.add(copy[0]);
        Point max = points[i];
        Point min = points[i];
        for (Point p: pointRecord) {
          if (p.compareTo(min) < 0) {
            min = p;
          } else if (p.compareTo(max) > 0) {
            max = p;
          }
        }          
        if (!isDuplicate(maxArr, max)) {
          maxArr.add(max);
          seglocal[numlocal] = new LineSegment(min, max);
          numlocal++;
        }
      }
      if (pointRecord.size() == copy.length) {
        break;
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
   * Check if the end point of a new line segement is duplicate with an existing end point list.
   */
  private boolean isDuplicate(List<Point> arr, Point p) {
    for (Point a : arr) {
      if (a.compareTo(p) == 0) {
        return true;
      }
    }
    return false;
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
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
    StdDraw.show();
  }  
}
