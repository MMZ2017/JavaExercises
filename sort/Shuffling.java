import java.util.Arrays;

public class Shuffling {
  
  /**
   * Knuth Shuffle.
   */
  public static void shuffle(int[] arr) {
    int n = arr.length;
    for (int i = 1; i < n; i++) {
      int r = (int)(Math.random() * i);
      Util.swap(arr, r, i);
    }
  }

  /**
   * Test.
   */
  public static void main(String[] args) {
    int[] arr1 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
    shuffle(arr1);
    System.out.println(Arrays.toString(arr1));
  }
}
