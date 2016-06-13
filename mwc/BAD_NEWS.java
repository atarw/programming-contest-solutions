import java.io.*;
import java.util.*;

public class BAD_NEWS {
  
  static char [][] maze;
  static boolean [][] vis;
  static String word;
  
  public static List <P> neighbours (int x, int y) {
    List <P> n = new ArrayList <P> ();
    
    if (x + 1 < maze.length && !vis [x + 1][y]) {
      n.add (new P (x + 1, y));
    }
    
    if (x - 1 >= 0 && !vis [x - 1][y]) {
      n.add (new P (x - 1, y));
    }
    
    if (y + 1 < maze.length && !vis [x][y + 1]) {
      n.add (new P (x, y + 1));
    }
    
    if (y - 1 >= 0 && !vis [x][y - 1]) {
      n.add (new P (x, y - 1));
    }
    
    if (x + 1 < maze.length && y + 1 < maze.length && !vis [x + 1][y + 1]) {
      n.add (new P (x + 1, y + 1));
    }
    
    if (x - 1 >= 0 && y - 1 >= 0 && !vis [x - 1][y - 1]) {
      n.add (new P (x - 1, y - 1));
    }
    
    if (x + 1 < maze.length && y - 1 >= 0 && !vis [x + 1][y - 1]) {
      n.add (new P (x + 1, y - 1));
    }
    
    if (x - 1 >= 0 && y + 1 < maze.length && !vis [x - 1][y + 1]) {
      n.add (new P (x - 1, y + 1));
    }
    
    return n;
  }
  
  public static boolean find (int index, int x, int y) {
    //System.out.println (index);
    
    if (index == word.length ()) {
      return true;
    }
    
    vis [x][y] = true;
    
    List <P> neighbours = neighbours (x, y);
    //System.out.println (neighbours);
    
    for (P p : neighbours) {
      if (maze [p.x][p.y] == word.charAt (index) && find (index + 1, p.x, p.y)) {
        return true;
      }
    }
    
    vis [x][y] = false;
    return false;
  }
  
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    
    //for (int T = 0; T < 10; T++) {
    //  BufferedReader in = new BufferedReader (new FileReader ("mwc15c3p3." + T + ".in"));
    // PrintWriter out = new PrintWriter (new FileWriter ("mwc15c3p3." + T + ".out"));
    t = in.readLine ().split (" ");
    
    int N = Integer.parseInt (t [0]), Q = Integer.parseInt (t [1]);
    boolean f = false;
    maze = new char [N][N];
    
    for (int n = 0; n < N; n++) {
      word = in.readLine ();
      
      for (int n2 = 0; n2 < N; n2 ++) {
        maze [n][n2] = word.charAt (n2 * 2);
      }
    }
    
    // for (char [] arr : maze) {
    //   System.out.println (Arrays.toString (arr));
    // }
    
    for (int q = 0; q < Q; q++) {
      word = in.readLine ();
      vis = new boolean [N][N];
      f = false;
      
      a: for (int x = 0; x < N; x++) {
        for (int y = 0; y < N; y++) {
          if (maze [x][y] == word.charAt (0) && find (1, x, y)) {
            f = true;
            System.out.println ("good puzzle!");
            //out.println ("good puzzle!");
            break a;
          }
        }
      }
      
      if (!f) {
        //out.println ("bad puzzle!");
        System.out.println ("bad puzzle!");
      }
    }
    // in.close ();
    // out.close ();
    //}
  }
}

class P {
  int x, y;
  
  public P (int x, int y) {
    this.x = x;
    this.y = y;
  }
}