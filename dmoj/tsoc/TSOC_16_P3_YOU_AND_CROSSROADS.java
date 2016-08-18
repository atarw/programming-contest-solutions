import java.io.*;
import java.util.*;

public class TSOC_16_P3_YOU_AND_CROSSROADS {
  
  public static int get (int sx, int ex, int sy, int ey, boolean [][] maze) {
    int s = 0;
    
    for (int i = sx; i < ex; i++) {
      for (int j = sy; j < ey; j++) {
        if (maze [i][j]) s++;
      }
    }
    
    return s;
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    boolean [][] maze = new boolean [2 * N][2 * N];
    
    String ln;
    
    for (int n = 0; n < 2 * N; n++) {
      ln = in.readLine ();
      
      for (int x = 0; x < 2 * N; x++) {
        if (ln.charAt (x) == 'd') {
          maze [n][x] = false;
        }
        else {
          maze [n][x] = true;
        }
      }
    }
    
    int bq = 0;
    int max = 0;
    
    int g = get (0, N, N, 2 * N, maze);
    
    if (g > max) {
      max = g;
      bq = 1;
    }
    else if (g == max) {
      bq = 1;
    }
    
    g = get (0, N, 0, N, maze);
    
    if (g > max) {
      max = g;
      bq = 2;
    }
    else if (g == max) {
      bq = Math.min (bq, 2);
    }
    
    g = get (N, 2 * N, 0, N, maze);
    
    if (g > max) {
      max = g;
      bq = 3;
    }
    else if (g == max) {
      bq = Math.min (bq, 3);
    }
    
    g = get (N, 2 * N, N, 2 * N, maze);
    
    if (g > max) {
      max = g;
      bq = 4;
    }
    else if (g == max) {
      bq = Math.min (bq, 4);
    }
    
    System.out.println (bq);
  }
}