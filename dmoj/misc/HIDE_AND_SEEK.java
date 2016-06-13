import java.io.*;
import java.util.*;

public class HIDE_AND_SEEK {
  
  static char [][] maze;
  static int min = 100000;
  
  public static boolean legal (int x, int y) {
    return x >= 0 && x < maze.length && y >= 0 && y < maze [0].length && maze [x][y] != 'X';
  }
  
  public static int solve (int x, int y, int t, int moves) {
    if (t == 0) {
      return moves;
    }
    
    char tmp;
    
    if (legal (x + 1, y)) {
      tmp = maze [x][y];
      maze [x][y] = 'X';
      min = Math.min (min, solve (x + 1, y, maze [x + 1][y] == 'H' ? t - 1 : t, moves + 1));
      maze [x][y] = tmp;
    }
    
    if (legal (x - 1, y)) {
      tmp = maze [x][y];
      maze [x][y] = 'X';
      min = Math.min (min, solve (x - 1, y, maze [x - 1][y] == 'H' ? t - 1 : t, moves + 1));
      maze [x][y] = tmp;
    }
    
    if (legal (x, y + 1)) {
      tmp = maze [x][y];
      maze [x][y] = 'X';
      min = Math.min (min, solve (x, y + 1, maze [x][y + 1] == 'H' ? t - 1 : t, moves + 1));
      maze [x][y] = tmp;
    }
    
    if (legal (x, y - 1)) {
      tmp = maze [x][y];
      maze [x][y] = 'X';
      min = Math.min (min, solve (x, y - 1, maze [x][y - 1] == 'H' ? t - 1 : t, moves + 1));
      maze [x][y] = tmp;
    }
    
    return min;
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String ln;
    String [] t = in.readLine ().split (" ");
    int N = Integer.parseInt (t [0]), M = Integer.parseInt (t [1]), T = Integer.parseInt (t [2]);
    maze = new char [N][M];
    int xs = 0, ys = 0;
    
    for (int n = 0; n < N; n++) {
      ln = in.readLine ();
      
      for (int i = 0; i < M; i++) {
        maze [n][i] = ln.charAt (i);
        
        if (ln.charAt (i) == 'G') {
          xs = n;
          ys = i;
        }
      }
    }
    
    System.out.println (solve (xs, ys, T, 0));
  }
}