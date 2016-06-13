import java.io.*;

//THANK YOU TIMOTHY FOR EDITORIAL :D

public class CCC_16_P4_COMBINING_RICEBALLS {
  
  static int [][] cache;
  static int [] arr;//prefix sum
  
  public static int f (int x, int y) {
    if (cache [x][y] == 0) {
      if (x >= y) {
        cache [x][y] = 1;
      }
      else {
        int b = y;
        
        for (int a = x; a < y; a++) {
          for (; b > x && arr [a] - arr [x - 1] > arr [y] - arr [b - 1]; b--) {}
          if (arr [a] - arr [x - 1] == arr [y] - arr [b - 1]) {
            cache [x][y] = Math.max (cache [x][y], f (x, a) * f (a + 1, b - 1) * f (b, y));
          }
        }
      }
    }
    
    return cache [x][y];
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ()), best = 0;
    cache = new int [N + 1][N + 1];
    arr = new int [N + 1];
    
    String [] t = in.readLine ().split (" ");
    
    for (int n = 1; n <= N; n++) {
      arr [n] = arr [n - 1] + Integer.parseInt (t [n - 1]);
      best = Math.max (best, arr [n] - arr [n - 1]);
    }
    
    for (int x = 1; x <= N; x++) {
      for (int y = x + 1; y <= N; y++) {
        best = Math.max (best, f (x, y) * (arr [y] - arr [x - 1]));
      }
    }
    
    System.out.print (best);
  }
}