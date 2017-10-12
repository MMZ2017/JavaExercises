import java.util.HashMap;
import java.util.Map;

public class HashMap {
  public static void main(String[] args) {
    HashMap<Integer, String> map = new HashMap<Integer, String>();
    map.put(5, "Five");
    map.put(6, "Six");
    map.put(7, "Seven");
    map.put(6, "Hello");
    
    String text = map.get(6);
    System.out.println(text);
    
    for(Map.Entry<Integer, String> entry: map.entrySet()) {
      int key = entry.getKey();
      String value = entry.getValue();
      System.out.println(key + ": " + value);
    }
  }
}
