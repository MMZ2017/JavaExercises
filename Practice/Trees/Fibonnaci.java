
public class Fibonnaci {
  public static int FibonnaciNumber(int n) {
    if (n <= 2) {
      return n - 1; 
    } else {
      return FibonnaciNumber(n - 1) + FibonnaciNumber(n - 2);
    }   
  }
  
  public static void main(String[] args) {
    System.out.println(Fibonnaci.FibonnaciNumber(7));
  }
}
