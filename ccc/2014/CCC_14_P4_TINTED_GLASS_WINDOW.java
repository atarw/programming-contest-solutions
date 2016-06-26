import java.io.*;
import java.util.*;

public class CCC_14_P4_TINTED_GLASS_WINDOW {
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    PrintWriter out = new PrintWriter (System.out);
    
    int N = Integer.parseInt (in.readLine ());
    int T = Integer.parseInt (in.readLine ());
    
    Rect [] cmds = new Rect [N];
    NavigableMap <Integer, Integer> xc = new TreeMap <Integer, Integer> ();//uncompressed x to compressed x
    NavigableMap <Integer, Integer> yc = new TreeMap <Integer, Integer> ();//uncompressed y to compressed y
    
    int [] xs = new int [N * 2], ys = new int [N * 2];
    
    for (int n = 0; n < N; ++n) {
      t = in.readLine ().split (" ");
      cmds [n] = new Rect (Integer.parseInt (t [0]), Integer.parseInt (t [1]), Integer.parseInt (t [2]), Integer.parseInt (t [3]), Integer.parseInt (t [4]));
      xs [n] = cmds [n].x1; xs [n + N] = cmds [n].x2;
      ys [n] = cmds [n].y1; ys [n + N] = cmds [n].y2;
    }
    
    Arrays.sort (xs); Arrays.sort (ys);
    
    int [] compx = new int [xs.length], compy = new int [ys.length];
    
    for (int n = 0; n < xs.length; ++n) {
      if (!xc.containsKey (xs [n])) {
        compx [xc.size ()] = xs [n];
        xc.put (xs [n], xc.size ());
      }
      
      if (!yc.containsKey (ys [n])) {
        compy [yc.size ()] = ys [n];
        yc.put (ys [n], yc.size ());
      }
    }
    
    long [][] diff = new long [yc.size ()][xc.size ()];
    
    for (int n = 0; n < N; ++n) {
      for (int i = yc.get (cmds [n].y1); i < yc.get (cmds [n].y2); ++i) {
        diff [i][xc.get (cmds [n].x1)] = diff [i][xc.get (cmds [n].x1)] + cmds [n].t;
        diff [i][xc.get (cmds [n].x2)] = diff [i][xc.get (cmds [n].x2)] - cmds [n].t;
      }
    }
    
    long area = 0;
    
    for (int n = 0; n < yc.size () - 1; ++n) {
      for (int n2 = 0; n2 < xc.size () - 1; ++n2) {
        diff [n][n2 + 1] = diff [n][n2 + 1] + diff [n][n2];
        
        if (diff [n][n2] >= T) {
          area = area + (long) (compx [n2 + 1] - compx [n2]) * (compy [n + 1] - compy [n]);
        }
      }
    }
    
    out.print (area); out.close ();
  }
}

class Rect {
  int x1, y1, x2, y2, t;
  
  public Rect (int x1, int y1, int x2, int y2, int t) {
    this.x1 = x1; this.y1 = y1; this.x2 = x2; this.y2 = y2; this.t = t;
  }
}