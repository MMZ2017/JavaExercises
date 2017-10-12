import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LinkedList {
  public static void main(String[] args) {
    List<Long> arrayList = new ArrayList<Long>();
    List<Long> linkedList = new LinkedList<Long>();
    
    doTimings("LinkedList", linkedList);
    doTimings("ArrayList", arrayList);
  }
  
  private static void doTimings(String type, List<Long> list) {
    //Add items at the end
    long start1 = System.currentTimeMillis();
    for(long i = 0; i<1E5; i++) {
      list.add(i);
    }
    long end1 = System.currentTimeMillis();
    System.out.println("Time taken to add to end: " + (end1 - start1) + " ms for " + type);
    
    //Add items at the beginning
    long start2 = System.currentTimeMillis();
    for(long i = 0; i<1E5; i++) {
      list.add(0, i);
    }
    long end2 = System.currentTimeMillis();
    System.out.println("Time taken to add to front: " + (end2 - start2) + " ms for " + type);
  }
}
