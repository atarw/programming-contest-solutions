import java.io.*;
import java.util.*;

public class DMOPC_16_P1_GEL_BANANAS {
  
  public static int gcd (int a, int b) {
    if (b == 0) return a;
    return gcd (b, a % b);
  }
  
  public static long lcm (int a, int b) {
    long lcm = a * (b / gcd (a, b));
    return lcm;
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int A = Integer.parseInt (in.readLine ()), B = Integer.parseInt (in.readLine ());
    long X = Long.parseLong (in.readLine ());
    
    System.out.print ((X - 1) / lcm (A, B) + 1);
  }
}