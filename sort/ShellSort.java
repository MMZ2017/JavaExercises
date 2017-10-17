import java.util.Arrays;


public class ShellSort {
  
  /**
   * Shell sort. h = 3h + 1.
   */
  public static void sort(int[] arr) {
    int n = arr.length;
    int h = 1;
    while (h < n / 3) {
      h = 3 * h + 1;
    }
    while (h >= 1) {
      for (int i = h; i < n; i++) {
        for (int j = i; j >= h; j = j - h) {
          if (arr[j] < arr[j - h]) {
            Util.swap(arr, j, j - h);
          }
        }
      }
      h = h / 3;
    }
  }
  
  /**
   * testing.
   */
  public static void main(String[] args) {
    int[] arr1 = {12,11,10,9,8,7,6,5,4,3,2,1};
    sort(arr1);
    System.out.println(Arrays.toString(arr1));
  }

}
