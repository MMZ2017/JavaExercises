import java.util.Arrays;

public class MergeSort {
  
  /**
   * Merge two sorted parts of an array.
   */
  public static void merge(int[] arr, int[] aux, int lo, int mid, int hi) {
    assert Util.isSorted(arr, lo, mid);
    assert Util.isSorted(arr, mid + 1, hi);
    for (int k = lo; k <= hi; k++) {
      aux[k] = arr[k];
    }
    int i = lo;
    int j = mid + 1;
    for (int k = lo; k <= hi; k++) {
      if (i > mid) {
        arr[k] = aux[j++];
      } else if (j > hi) {
        arr[k] = aux[i++];
      } else if (Util.less(aux[i], aux[j])) {
        arr[k] = aux[i++];
      } else {
        arr[k] = aux[j++];
      }
    }
    assert Util.isSorted(arr, lo, hi);
  }
  
  /**
   * the sort method divides the array into smaller parts until each part only contains one item, 
   * and then call the merge method.
   */
  private static void sort(int[] arr, int[] aux, int lo, int hi) {
    if (hi == lo) {
      return;
    } else {
      int mid = (hi + lo) / 2;
      sort(arr, lo, mid);
      sort(arr, mid + 1, hi);    
      merge(arr, aux, lo, mid, hi);     
    }
  }
  
  
  public static void sort(int[] arr, int lo, int hi) {  
    int[] aux = new int[arr.length];
    sort(arr, aux, lo, hi);
  }
  
  
  /**
   * Testing.
   */
  public static void main(String[] args) {
    int[] arr1 = {4,5,6,7,8,0,1,2,3,4};
    sort(arr1, 0, arr1.length - 1);
    System.out.println(Arrays.toString(arr1));
  }
}
