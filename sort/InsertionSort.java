import java.util.Arrays;

public class InsertionSort {
  /**
   *  Uses Insertion Sort.
   */
  public static void sort(int[] arr) {
    if (arr == null || arr.length == 0) {
      throw new IllegalArgumentException("Please make sure that array is not empty");
    }
    for (int i = 0; i < arr.length; i++) {
      for (int j = i; j > 0; j--) {  
        if (arr[j] < arr[j - 1]) {
          Util.swap(arr, j, j - 1);;
        }
      }
    }
  }
  
  /**
   * testing the sort() method.
   */
  public static void main(String[] args) {
    int[] arr1 = new int[] {5,4,3,2,1};
    sort(arr1);
    System.out.println(Arrays.toString(arr1));
    
    int[] arr2 = new int[] {4,3,5,6,1,9,28};
    sort(arr2);
    System.out.println(Arrays.toString(arr2));
    
    int[] arr3 = new int[] {4,3,-11,6,1,9,-2};
    sort(arr3);
    System.out.println(Arrays.toString(arr3));
    
    int[] arr4 = new int[] {};
    sort(arr4);
    System.out.println(Arrays.toString(arr4));
  }
}
