import java.io.*;

public class DMPG_14_S4_SIGNAL_HILL {
  
  static int [] x, y, r;
  static boolean [][] matrix;
  
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    t = in.readLine ().split (" ");
    
    int B = Integer.parseInt (t [0]), Q = Integer.parseInt (t [1]);
    x = new int [B];
    y = new int [B];
    r = new int [B];
    matrix = new boolean [B][B];
    
    for (int b = 0; b < B; b++) {
      t = in.readLine ().split (" ");
      x [b] = Integer.parseInt (t [0]);
      y [b] = Integer.parseInt (t [1]);
      r [b] = Integer.parseInt (t [2]);
    }
    
    for (int b = 0; b < B; b++) {
      for (int b2 = 0; b2 < B; b2++) {
        if ((x [b2] - x [b]) * (x [b2] - x [b]) + (y [b2] - y [b]) * (y [b2] - y [b]) <= r [b] * r [b]) {
          matrix [b][b2] = true;
        }
      }
    }
    
    for (int k = 0; k < B; k++) {
      for (int i = 0; i < B; i++) {
        for (int j = 0; j < B; j++) {
          if (matrix [i][k] && matrix [k][j]) {
            matrix [i][j] = true;
          }
        }
      }
    }
    
    for (int q = 0; q < Q; q++) {
      t = in.readLine ().split (" ");
      System.out.println (matrix [Integer.parseInt (t [0]) - 1][Integer.parseInt (t [1]) - 1] ? "YES" : "NO");
    }
  }
}