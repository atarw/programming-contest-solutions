import java.io.*;
import java.util.*;

public class P2 {
  
  static int [][] moves;
  
  public static int solve (char [][] maze, P p, P x) {
    if (p.equals (x)) {
      return 0;
    }
    else {
      Queue <P> queue = new LinkedList <P> ();
      List <P> next = new LinkedList <P> ();
      
      queue.offer (p);
      
      while (!queue.isEmpty ()) {
        P curr = queue.poll ();
        
        if (curr.X + 1 < maze.length && curr.X + 1 >= 0 && moves [curr.X + 1][curr.Y] != -1) {
          next.add (new P (curr.X + 1, curr.Y));
        }
        if (curr.X - 1 < maze.length && curr.X - 1 >= 0 && moves [curr.X - 1][curr.Y] != -1) {
          next.add (new P (curr.X - 1, curr.Y));
        }
        if (curr.Y + 1 < maze [0].length && curr.Y + 1 >= 0 && moves [curr.X][curr.Y + 1] != -1) {
          next.add (new P (curr.X, curr.Y + 1));
        }
        if (curr.Y - 1 < maze [0].length && curr.Y - 1 >= 0 && moves [curr.X][curr.Y - 1] != -1) {
          next.add (new P (curr.X, curr.Y - 1));
        }
        
        for (P i : next) {
          
          if (i.equals (x)) {
            moves [x.X][x.Y] = moves [curr.X][curr.Y] + 1;
            break;
          }
          else if (moves [i.X][i.Y] == 0) {
            moves [i.X][i.Y] = moves [curr.X][curr.Y] + 1;
            queue.offer (i);
          }
        }
        next.clear ();
      }
      return moves [x.X][x.Y];
    }
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t = in.readLine ().split (" ");
    int R = Integer.parseInt (t [0]);
    int C = Integer.parseInt (t [1]);
    
    P p = null, x = null, b = null;
    
    char [][] maze = new char [R][C];
    moves = new int [R][C];
    String ln;
    
    for (int r = 0; r < R; r++) {
      ln = in.readLine ();
      for (int c = 0; c < C; c++) {
        maze [r][c] = ln.charAt (c);
        
        if (maze [r][c] == 'P') {
          p = new P (r, c);
          moves [r][c] = -1;
        }
        else if (maze [r][c] == 'X') {
          x = new P (r, c);
        }
        else if (maze [r][c] == 'B') {
          b = new P (r, c);
        }
        else if (maze [r][c] == '#') {
          moves [r][c] = -1;
        }
      }
    }
    
    //System.out.println (p.X + " " + p.Y);
    //System.out.println (b.X + " " + b.Y);
    
    int toBox = solve (maze, p, b);
    
    for (int r = 0; r < R; r++) {
      Arrays.fill (moves [r], 0);
    }
    
    int toX = solve (maze, b, x) + 1;
    
    System.out.println (toX);
    
    System.out.println (toBox + toX);
  }
}

class P {
  int X, Y;
  
  public P (int X, int Y) {
    this.X = X;
    this.Y = Y;
  }
  
  public boolean equals (Object o) {
    P t = (P)(o);
    
    return t.X == X && t.Y == Y;
  }
}