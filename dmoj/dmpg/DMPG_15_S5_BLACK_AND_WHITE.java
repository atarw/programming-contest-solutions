import java.io.*;
import java.util.*;

//switches to uncompressed coordinates (which has better performance apparently) when N < 1000

public class DMPG_15_S5_BLACK_AND_WHITE {
  public static void main (String [] t) throws IOException {
    INPUT in = new INPUT (System.in);
    PrintWriter out = new PrintWriter (System.out);
    
    int N = in.iscan (), M = in.iscan (), x, y, w, h, count = 0;
    
    if (N > 1000) {//compress coordinates solution
      Rect [] cmds = new Rect [M];
      int [] xs = new int [M * 2], ys = new int [M * 2];//uncompressed input coordinates
      
      for (int m = 0; m < M; ++m) {
        x = in.iscan (); y = in.iscan (); w = in.iscan (); h = in.iscan ();
        
        cmds [m] = new Rect (x, y, w, h);
        
        xs [m] = x; xs [m + M] = x + w;
        ys [m] = y; ys [m + M] = y + h;
      }
      
      Arrays.sort (xs); Arrays.sort (ys);
      
      NavigableMap <Integer, Integer> xc = new TreeMap <Integer, Integer> ();//compressed x coordinates (uncompressed to compressed)
      NavigableMap <Integer, Integer> yc = new TreeMap <Integer, Integer> ();//compressed y coordinates (uncompressed to compressed)
      
      int [] compx = new int [xs.length], compy = new int [ys.length];
      
      //compressing coordinates
      for (int m = 0; m < xs.length; ++m) {
        if (!xc.containsKey (xs [m])) {
          compx [xc.size ()] = xs [m];
          xc.put (xs [m], xc.size ());
        }
        
        if (!yc.containsKey (ys [m])) {
          compy [yc.size ()] = ys [m];
          yc.put (ys [m], yc.size ());
        }
      }
      
      int [][] diff = new int [yc.size ()][xc.size ()];//even = black
      
      //create difference array with compressed coordinates
      for (int m = 0; m < M; ++m) {
        for (int i = yc.get (cmds [m].y1); i < yc.get (cmds [m].y2); ++i) {
          ++diff [i][xc.get (cmds [m].x1)];
          --diff [i][xc.get (cmds [m].x2)];
        }
      }
      
      //expand difference array
      for (int n = 0; n < yc.size () - 1; ++n) {
        for (int n2 = 0; n2 < xc.size () - 1; ++n2) {
          diff [n][n2 + 1] += diff [n][n2];
          
          if (Math.abs (diff [n][n2]) % 2 == 1) {
            count += (compx [n2 + 1] - compx [n2]) * (compy [n + 1] - compy [n]);
          }
        }
      }
    }
    else {//non compressed coordinates solution (why??)
      int [][] diff = new int [N + 1][N + 1];//even number = black
      
      for (int m = 0; m < M; ++m) {
        x = in.iscan (); y = in.iscan (); w = in.iscan (); h = in.iscan ();
        
        for (int i = y; i < y + h; ++i) {
          ++diff [i][x];
          --diff [i][x + w];
        }
      }
      
      int curr = 0;
      
      for (int n = 0; n < N; ++n) {
        curr = 0;
        
        for (int n2 = 0; n2 < N; ++n2) {
          curr += diff [n][n2];
          
          if (Math.abs (curr) % 2 == 1) {
            ++count;
          }
        }
      }
    }
    
    out.print (count); out.close ();
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

class Rect {
  int x1, x2, y1, y2;
  
  public Rect (int x, int y, int w, int h) {
    this.x1 = x; this.y1 = y; this.x2 = x + w; this.y2 = y + h;
  }
}