import java.io.*;

public class PICK_IT {
  
  static int [] arr;
  static int [][] cache;
  
  public static int best (int l, int r) {
    if (cache [l][r] == 0 && l + 1 < r) {
      for (int i = l + 1; i < r; i++) {
        cache [l][r] = Math.max (cache [l][r], arr [l] + arr [i] + arr [r] + best (l, i) + best (i, r));//choose number at i, and then find best possible combos for all numbers left of i and right of i
      }
    }
    
    return cache [l][r];
  }
  
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N;
    
    while (true) {
      t = in.readLine ().split (" ");
      
      if (t [0].charAt (0) == '0') {
        break;
      }
      
      N = Integer.parseInt (t [0]);
      arr = new int [N];
      cache = new int [N][N];
      
      for (int n = 1; n <= N; n++) {
        arr [n - 1] = Integer.parseInt (t [n]);
      }
      
      System.out.println (best (0, N - 1));
    }
  }
}