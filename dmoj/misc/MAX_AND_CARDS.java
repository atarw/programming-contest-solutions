import java.io.*;

public class MAX_AND_CARDS {
  
  static int N;
  static long [] fact = {1L, 2L, 6L, 24L, 120L, 720L, 5040L, 40320L, 362880L, 3628800L, 39916800L, 479001600L, 6227020800L, 87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L};
  
  public static void ans (long x) {
    if (x % N == 0) {
      System.out.println (x);
    }
    else {
      System.out.print ((x) + " ");
      ans (x / fact [N]);
    }
  }
  
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    N = Integer.parseInt (in.readLine ());
    int Q = Integer.parseInt (in.readLine ());
    long x = 0;
    
    while (Q --> 0) {
      x = Long.parseLong (in.readLine ());
      ans (x);
    }
  }
}