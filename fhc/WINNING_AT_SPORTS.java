import java.io.*;

public class WINNING_AT_SPORTS {
  
  static int [][] cache;
  
  public static int free (int M, int O) {
    if (cache [M][O] == 0 && !(M == cache.length - 1 && O == cache [0].length - 1)) {
      if (M == cache.length - 1 || O == cache [0].length - 1) {
        cache [M][O] = 1;
      }
      else if (M == 0 || O + 1 == M) {
        cache [M][O] = free (M + 1, O);
      }
      else {
        cache [M][O] = free (M + 1, O) + free (M, O + 1);
      }
    }
    
    return cache [M][O] % 1000000007;
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int T = Integer.parseInt (in.readLine ()), stress, free;
    String [] t;
    
    for (int i = 1; i <= T; i++) {
      t = in.readLine ().split ("-");
      cache = new int [Integer.parseInt (t [0]) + 1][Integer.parseInt (t [1]) + 1];
      
      System.out.println ("Case #" + i + ": " + free (0, 0) + " " + free (cache [0].length - 1, cache [0].length - 2));
    }
  }
}