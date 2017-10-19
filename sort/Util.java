
public class Util {
  /**
   * swap two elements in an array.
   */
  public static void swap(int[] arr, int a, int b) {
    int temp = arr[b];
    arr[b] = arr[a];
    arr[a] = temp;
  }

  public static boolean less(int a, int b) {
    return a < b;
  }
  
  /**
   * Check if an array is sorted.
   */
  public static boolean isSorted(int[] arr, int lo, int hi) {
    for (int i = lo; i < hi; i++) {
      if (less(arr[i], arr[i - 1])) {
        return false;
      }
    }
    return true;
  }  
}
