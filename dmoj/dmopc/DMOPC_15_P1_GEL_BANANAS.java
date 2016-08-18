import java.io.*;
import java.util.*;

public class DMOPC_15_P1_GEL_BANANAS {
  
  public static long gcd (long a, long b) {
    if (b == 0) return a;
    return gcd (b, a % b);
  }
 
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    long A = Long.parseLong (in.readLine ()), B = Long.parseLong (in.readLine ());
    long X = Long.parseLong (in.readLine ());
    long lcm = A * B / gcd (A, B);
    
    System.out.println ((X - 1) / lcm + 1);
  }
}