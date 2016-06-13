import java.io.*;

public class DIGITS {
  
  static int N, M;
  static int [] cache;
  
  public static int min (int n) {
    System.out.println (n);
    
    if (cache [n] == 0 && n != M) {
      if (n == 1) {
        cache [n] = 1 + min (n * 2);
      }
      else {
        cache [n] = 1 + Math.min (min (n * 2), min (n - 1));
      }
    }
    
    return cache [n];
  }
  
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    t = in.readLine ().split (" ");
    
    N = Integer.parseInt (t [0]); M = Integer.parseInt (t [1]);
    cache = new int [2 * M + 1];
    
    System.out.println (min (N));
  }
}