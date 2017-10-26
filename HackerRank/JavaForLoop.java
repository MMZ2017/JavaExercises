import java.io.*;
import java.util.*;

public class JavaForLoop{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for (int i = 1; i< 11; i++){
            int T = N * i;
            System.out.printf("%d x %d = %d%n", N, i, T);
        }
    }
}
