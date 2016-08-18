import java.io.*;
import java.util.*;

public class CCC_12_S5_MOUSE_JOURNEY {
  
  static int R, C;
  static boolean [][] cat;
  static int [][] ways;
  
  public static int ways (int x, int y) {
    
    if (x == R - 1 && y == C - 1) {
      return 1;
    }
    else if (ways [x][y] == 0) {
      int total = 0;
      
      if (x + 1 < cat.length && !cat [x + 1][y]) {
        total += ways (x + 1, y);
      }
      if (y + 1 < cat [x].length && !cat [x][y + 1]) {
        total += ways (x, y + 1);
      }
      
      ways [x][y] = total;
    }
    
    return ways [x][y];
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t = in.readLine ().split (" ");
    
    R = Integer.parseInt (t [0]);
    C = Integer.parseInt (t [1]);
    
    cat = new boolean [R][C];
    ways = new int [R][C];
    
    int K = Integer.parseInt (in.readLine ());
    
    for (int i = 0; i < K; i++) {
      t = in.readLine ().split (" ");
      cat [Integer.parseInt (t [0]) - 1][Integer.parseInt (t [1]) - 1] = true;
    }
    
    System.out.println (ways (0, 0));
  }
}