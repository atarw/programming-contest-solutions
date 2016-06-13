import java.io.*;

public class CCC_07_P5_BOWLING_FOR_NUMBERS {
  
  static int [] pins;
  static int [] prefix;
  static int [][] cache;
  static int W;
  
  public static int best (int n, int k) {
    if (cache [n][k] == 0 && !(k == 0 || n == 0)) {
      if (n - W < 0) {
        cache [n][k] = prefix [n] - prefix [0];
      }
      else {
        cache [n][k] = Math.max (prefix [n] - prefix [n - W] + best (n - W, k - 1), best (n - 1, k));
      }
    }
    
    return cache [n][k];
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int T = Integer.parseInt (in.readLine ()), N, K;
    String [] t;
    
    for (int i = 0; i < T; i++) {
      t = in.readLine ().split (" ");
      N = Integer.parseInt (t [0]);
      K = Integer.parseInt (t [1]);
      W = Integer.parseInt (t [2]);
      
      pins = new int [N];
      prefix = new int [N + 1];
      cache = new int [N + 1][K + 1];
      
      for (int n = 0; n < N; n++) {
        pins [n] = Integer.parseInt (in.readLine ());
        prefix [n + 1] = pins [n] + prefix [n];
      }
      
      System.out.println (best (N, K));
    }
  }
}