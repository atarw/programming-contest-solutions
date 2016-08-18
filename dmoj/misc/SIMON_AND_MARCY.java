import java.io.*;

public class SIMON_AND_MARCY {
  
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    t = in.readLine ().split (" ");
    int C = Integer.parseInt (t [0]), M = Integer.parseInt (t [1]);
    
    int [] profit = new int [C];
    int [] weight = new int [C];
    int [] cache = new int [M + 1];
    
    for (int c = 0; c < C; c++) {
      t = in.readLine ().split (" ");
      profit [c] = Integer.parseInt (t [0]);
      weight [c] = Integer.parseInt (t [1]);
    }
    
    for (int c = 1; c <= C; c++) {
      for (int m = M; m >= weight [c - 1]; m--) {
        cache [m] = Math.max (cache [m], profit [c - 1] + cache [m - weight [c - 1]]);
      }
    }
    
    System.out.println (cache [M]);
  }
}