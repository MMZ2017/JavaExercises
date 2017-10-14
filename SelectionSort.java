import java.util.Arrays;

public class SelectionSort {
  /**
   * Uses Selection Sort.
   */
  public static void sort(int[] arr) {
    if (arr == null || arr.length == 0) {
      throw new IllegalArgumentException("Please make sure that array is not empty");
    }
    for (int i = 0; i < arr.length; i++) {
      int min = arr[i];
      int index = 0;
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[j] < min) {
          min = arr[j];
          index = j;
        }
      }
      if (min != arr[i]) {
        int temp = arr[i];
        arr[i] = min;
        arr[index] = temp; 
      }
    }
  }
  
  /**
   * Testing.
   */
  public static void main(String[] args) {
    int[] arr1 = new int[] {5,3,2,4,1};
    sort(arr1); 
    System.out.println(Arrays.toString(arr1));
    
    int[] arr2 = new int[] {5,4,3,2,1};
    sort(arr2);
    System.out.println(Arrays.toString(arr2));
    
    int[] arr3 = new int[] {1,-11,5};
    sort(arr3);
    System.out.println(Arrays.toString(arr3));
  } 
}
