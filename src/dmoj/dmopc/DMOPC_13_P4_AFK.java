import java.io.*;
import java.util.*;

public class DMOPC_13_P4_AFK {
  
  static char [][] maze;
  static int [][] steps;
  
  public static int solve (int X, int Y) {
    Queue <P> queue = new LinkedList <P> ();
    P curr;
    
    queue.offer (new P (X, Y));
    
    while (!queue.isEmpty ()) {
      curr = queue.poll ();
      
      if (maze [curr.X][curr.Y] == 'W' || steps [curr.X][curr.Y] >= 60) {
        return steps [curr.X][curr.Y];
      }
      else {
        if (valid (curr.X + 1, curr.Y)) {
          steps [curr.X + 1][curr.Y] = steps [curr.X][curr.Y] + 1;
          queue.offer (new P (curr.X + 1, curr.Y));
        }
        if (valid (curr.X - 1, curr.Y)) {
          steps [curr.X - 1][curr.Y] = steps [curr.X][curr.Y] + 1;
          queue.offer (new P (curr.X - 1, curr.Y));
        }      
        if (valid (curr.X, curr.Y + 1)) {
          steps [curr.X][curr.Y + 1] = steps [curr.X][curr.Y] + 1;
          queue.offer (new P (curr.X, curr.Y + 1));
        }
        if (valid (curr.X, curr.Y - 1)) {
          steps [curr.X][curr.Y - 1] = steps [curr.X][curr.Y] + 1;
          queue.offer (new P (curr.X, curr.Y - 1));
        }
      }
    }
    
    return 60;
  }
  
  public static boolean valid (int r, int c) {
    if (r >= maze.length || r < 0 || c >= maze [0].length || c < 0 || maze [r][c] == 'X' || steps [r][c] != 0) {
      return false;
    }
    return true;
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int T = Integer.parseInt (in.readLine ());
    String [] tokens;
    String row;
    int L, W;
    
    int X = -1, Y = -1;
    
    for (int t = 0; t < T; t++) {
      tokens = in.readLine ().split (" ");
      L = Integer.parseInt (tokens [1]);
      W = Integer.parseInt (tokens [0]);
      X = -1;
      Y = -1;
      maze = new char [L][W];
      steps = new int [L][W];
      
      for (int l = 0; l < L; l++) {
        row = in.readLine ();
        maze [l] = row.toCharArray ();
        
        if (X == -1) {
          int index = row.indexOf ("C");
          
          if (index != -1) {
            X = index;
            Y = l;
          }
        }
      }
      
      int ans = solve (Y, X);
      
      System.out.println (ans < 60 ? ans : "#notworth");
    }
  }

  private static class P {
    int X, Y;

    public P (int X, int Y) {
      this.X = X;
      this.Y = Y;
    }
  }
}