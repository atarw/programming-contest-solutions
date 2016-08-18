import java.io.*;
import java.util.*;

public class CCC_08_S3_MAZE {
  
  static char [][] maze;
  static int [][] steps;
  
  public static int solve (int x, int y) {
    Queue <P> queue = new LinkedList <P> ();
    queue.offer (new P (x, y));
    P curr;
    
    while (!queue.isEmpty ()) {
      curr = queue.poll ();
      
      if (curr.X == maze.length - 1 && curr.Y == maze [0].length - 1) {
        steps [curr.X][curr.Y]++;
        return steps [curr.X][curr.Y];
      }
      else {
        if (valid (curr.X, curr.Y, curr.X + 1, curr.Y)) {
          steps [curr.X + 1][curr.Y] = steps [curr.X][curr.Y] + 1;
          queue.offer (new P (curr.X + 1, curr.Y));
        }
        if (valid (curr.X, curr.Y, curr.X, curr.Y + 1)) {
          steps [curr.X][curr.Y + 1] = steps [curr.X][curr.Y] + 1;
          queue.offer (new P (curr.X, curr.Y + 1));
        }
        if (valid (curr.X, curr.Y, curr.X - 1, curr.Y)) {
          steps [curr.X - 1][curr.Y] = steps [curr.X][curr.Y] + 1;
          queue.offer (new P (curr.X - 1, curr.Y));
        }
        if (valid (curr.X, curr.Y, curr.X, curr.Y - 1)) {
          steps [curr.X][curr.Y - 1] = steps [curr.X][curr.Y] + 1;
          queue.offer (new P (curr.X, curr.Y - 1));
        }
      }
    }
    return -1;
  }
  
  public static boolean valid (int x, int y, int r, int c) {
    if (r >= maze.length || r < 0 || c >= maze [0].length || c < 0 || maze [r][c] == '*' || steps [r][c] != 0) {
      return false;
    }
    else if (maze [x][y] == '-') {
      return x == r && (y + 1 == c || y - 1 == c);
    }
    else if (maze [x][y] == '|') {
      return y == c && (x - 1 == r || x + 1 == r);
    }
    return true;
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int T = Integer.parseInt (in.readLine ());
    int R, C;
    
    for (int t = 0; t < T; t++) {
      R = Integer.parseInt (in.readLine ());
      C = Integer.parseInt (in.readLine ());
      maze = new char [R][C];
      steps = new int [R][C];
      
      for (int r = 0; r < R; r++) {
        maze [r] = in.readLine ().toCharArray ();
      }
      
      if (maze [R - 1][C - 1] != '*') {
        System.out.println (solve (0, 0));
      }
      else {
        System.out.println (-1);
      }
    }
  }
}

class P {
  int X, Y;
  
  public P (int X, int Y) {
    this.X = X;
    this.Y = Y;
  }
}