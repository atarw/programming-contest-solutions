import java.io.*;
import java.util.*;

public class FREE_TICKET {
  
  static int [][] matrix;
  
  public static int floyd () {
    int min = 0;
    
    for (int k = 0; k < matrix.length; k++) {
      for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix.length; j++) {
          if (matrix [i][j] > matrix [i][k] + matrix [k][j]) {
            matrix [i][j] = matrix [i][k] + matrix [k][j];
          }
        }
      }
    }
    
    for (int n = 0; n < matrix.length; n++) {
      for (int m = n + 1; m < matrix.length; m++) {
        min = Math.max (min, matrix [n][m]);
      }
    }
    
    return min;
  }
  
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    t = in.readLine ().split (" ");
    
    int C = Integer.parseInt (t [0]), F = Integer.parseInt (t [1]), S, E;
    matrix = new int [C][C];
    
    for (int c = 0; c < C; c++) {
      Arrays.fill (matrix [c], 10000000);
      matrix [c][c] = 0;
    }
    
    for (int f = 0; f < F; f++) {
      t = in.readLine ().split (" ");
      S = Integer.parseInt (t [0]) - 1;
      E = Integer.parseInt (t [1]) - 1;
      matrix [S][E] = matrix [E][S] = Integer.parseInt (t [2]);
    }
    
    System.out.println (floyd ());
  }
}