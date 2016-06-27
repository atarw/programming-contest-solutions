import java.io.*;
import java.util.*;

public class HIDE_AND_SEEK {
  
  static PrintWriter out = new PrintWriter (System.out);
  static int min = Integer.MAX_VALUE;
  static int [][] maze;
  static int [][][] cache;
  
  static int [] x, y;
  
  public static boolean valid (int t, int x, int y, int [][] maze, int [][][] cache, int curr) {
    return x >= 0 && x < maze.length && y >= 0 && y < maze [0].length && maze [x][y] != 'X' && cache [t][x][y] > curr + 1;
  }
  
  public static void permute (List <Integer> path, int K) {
    for (int k = K; k < path.size (); ++k) {
      Collections.swap (path, k, K);
      permute (path, k + 1);
      Collections.swap (path, K, k);
    }
    
    if (K == path.size () - 1) {
      int length = 0;
      int loc = x.length - 1;
      
      for (int i = 0; i < path.size (); ++i) {
        length += cache [loc][x [path.get (i)]][y [path.get (i)]];
        loc = path.get (i);
      }
      
      min = Math.min (length, min);
    }
  }
  
  public static void main (String [] args) throws IOException {
    INPUT in = new INPUT (System.in);
    
    int N = in.iscan (), M = in.iscan (), T = in.iscan ();
    
    maze = new int [N][M];
    
    x = new int [T + 1]; y = new int [T + 1];//hider positions, last is griffy
    int pos = 1;
    
    for (int n = 0; n < N; ++n) {
      for (int m = 0; m < M; ++m) {
        maze [n][m] = in.cscan ();
        
        if (maze [n][m] == 'H') {
          x [pos] = n; y [pos] = m; ++pos;
        }       
        else if (maze [n][m] == 'G') {
          x [0] = n; x [0] = m;
        }
      }
      
      in.cscan ();
    }
    
    Queue <P> queue;
    P curr;
    
    cache = new int [T + 1][N][M];//from point specified by x [T + 1] and y [T + 1], min distance to point [N, M]
    
    for (int i = 0; i < x.length; ++i) {
      for (int n = 0; n < N; ++n) {
        for (int m = 0; m < M; ++m) {
          cache [i][n][m] = Integer.MAX_VALUE;
        }
      }
    }
    
    for (int i = 0; i < x.length; ++i) {
      cache [i][x [i]][y [i]] = 0;
    }
    
    for (int i = 0; i < x.length; ++i) {
      queue = new ArrayDeque <P> ();
      curr = new P (x [i], y [i]);
      queue.offer (curr);
      
      while (!queue.isEmpty ()) {
        curr = queue.poll ();
        
        if (valid (i, curr.x - 1, curr.y, maze, cache, cache [i][curr.x][curr.y])) {
          cache [i][curr.x - 1][curr.y] = cache [i][curr.x][curr.y] + 1;
          queue.offer (new P (curr.x - 1, curr.y));
        }
        
        if (valid (i, curr.x + 1, curr.y, maze, cache, cache [i][curr.x][curr.y])) {
          cache [i][curr.x + 1][curr.y] = cache [i][curr.x][curr.y] + 1;
          queue.offer (new P (curr.x + 1, curr.y));
        }
        
        if (valid (i, curr.x, curr.y - 1, maze, cache, cache [i][curr.x][curr.y])) {
          cache [i][curr.x][curr.y - 1] = cache [i][curr.x][curr.y] + 1;
          queue.offer (new P (curr.x, curr.y - 1));
        }
        
        if (valid (i, curr.x, curr.y + 1, maze, cache, cache [i][curr.x][curr.y])) {
          cache [i][curr.x][curr.y + 1] = cache [i][curr.x][curr.y] + 1;
          queue.offer (new P (curr.x, curr.y + 1));
        }
      }
    }
    
    List <Integer> path = new ArrayList <Integer> ();
    
    for (int i = 0; i < T; ++i) {
      path.add (i);
    }
    
    permute (path, 0);
    
    out.print (min); out.close ();
  }
  
  private static class INPUT {
    
    private InputStream stream;
    private byte [] buf = new byte [1024];
    private int curChar, numChars;
    
    public INPUT (InputStream stream) {
      this.stream = stream;
    }
    
    public int cscan () throws IOException {
      if (curChar >= numChars) {
        curChar = 0;
        numChars = stream.read (buf);
        
        if (numChars <= 0) return -1;
      }
      
      return buf [curChar++];
    }
    
    public int iscan () throws IOException {
      int c = cscan (), sgn = 1;
      while (space (c)) c = cscan ();
      
      if (c == '-') {
        sgn = -1;
        c = cscan ();
      }
      
      int res = 0;
      
      do
      {
        res *= 10;
        res += c - '0';
        
        c = cscan ();
      }
      while (!space (c));
      
      return res * sgn;
    }
    
    public boolean space (int c) {
      return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }
  }
}

class P {
  int x, y;
  
  public P (int x, int y) {
    this.x = x; this.y = y;
  }
}