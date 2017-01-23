import java.io.*;
import java.util.*;

public class CCO_15_P1_CARS_ON_ICE {
  
  static char [][] maze;
  static int [][] lot;
  
  public static void main (String [] t) throws IOException {
    INPUT inp = new INPUT (System.in);
    
    int N = inp.readInt (), M = inp.readInt (), C = 0;
    String ln;
    
    maze = new char [N][M]; lot = new int [N][M];
    
    for (int n = 0; n < N; n++) {
      ln = inp.readString ();
      
      for (int m = 0; m < M; m++) {
        maze [n][m] = ln.charAt (m);
        
        if (maze [n][m] != '.') {
          lot [n][m] = C;
          C++;
        }
      }
    }
    
    int [] in = new int [C], to = new int [C], ord = new int [C], xc = new int [C], yc = new int [C];
    int b = 0;
    
    for (int n = 0; n < N; n++) {
      for (int m = 0; m < M; m++) {
        if (maze [n][m] != '.') {
          xc [b] = n; yc [b] = m;
          b++;
        }
        
        if (maze [n][m] == 'N') {
          for (int x = n - 1; x >= 0; x--) {
            if (maze [x][m] != '.') {
              to [lot [n][m]] = lot [x][m];
              in [lot [x][m]]++;
              break;
            }
          }
        }
        else if (maze [n][m] == 'S') {
          for (int x = n + 1; x < N; x++) {
            if (maze [x][m] != '.') {
              to [lot [n][m]] = lot [x][m];
              in [lot [x][m]]++;
              break;
            }
          }
        }
        else if (maze [n][m] == 'E') {
          for (int x = m + 1; x < M; x++) {
            if (maze [n][x] != '.') {
              to [lot [n][m]] = lot [n][x];
              in [lot [n][x]]++;
              break;
            }
          }
        }
        else if (maze [n][m] == 'W') {
          for (int x = m - 1; x >= 0; x--) {
            if (maze [n][x] != '.') {
              to [lot [n][m]] = lot [n][x];
              in [lot [n][x]]++;
              break;
            }
          }
        }
      }
    }
    
    Deque <Integer> queue = new ArrayDeque <Integer> (ord.length);
    
    for (int c = 0; c < ord.length; c++) {
      if (in [c] == 0) {
        queue.offer (c);
      }
    }
    
    int curr, a = 0;
    
    while (!queue.isEmpty ()) {
      curr = queue.poll ();
      in [curr]--;
      in [to [curr]]--;
      
      ord [a] = curr; a++;
      
      if (in [to [curr]] == 0) {
        queue.offerLast (to [curr]);
      }
    }
    
    OUTPUT out = new OUTPUT (System.out);
    
    for (int i = ord.length - 1; i >= 0; i--) {
      out.println ("(" + xc [ord [i]] + "," + yc [ord [i]] + ")");
    }
    
    out.close ();
  }
  
  private static class INPUT {
    
    private InputStream stream;
    private byte [] buf = new byte [1024];
    private int curChar, numChars;
    
    public INPUT (InputStream stream) {
      this.stream = stream;
    }
    
    public int read () throws IOException {
      if (numChars == -1) throw new InputMismatchException ();
      
      if (curChar >= numChars) {
        curChar = 0;
        numChars = stream.read (buf);
        
        if (numChars <= 0) return -1;
      }
      
      return buf [curChar++];
    }
    
    public int readInt () throws IOException {
      int c = read (), sgn = 1;
      while (isSpaceChar (c)) c = read ();
      
      if (c == '-') {
        sgn = -1;
        c = read ();
      }
      
      int res = 0;
      
      do
      {
        if (c < '0' || c > '9') throw new InputMismatchException ();
        res *= 10;
        res += c - '0';
        
        c = read ();
      }
      while (!isSpaceChar (c));
      
      return res * sgn;
    }
    
    public String readString () throws IOException {
      int c = read ();
      while (isSpaceChar (c)) c = read();
      
      StringBuilder res = new StringBuilder();
      
      do
      {
        res.appendCodePoint (c);
        c = read ();
      }
      while (!isSpaceChar (c));
      
      return res.toString ();
    }
    
    public boolean isSpaceChar (int c) {
      return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }
  }
  
  private static class OUTPUT {
    
    private final PrintWriter writer;
    
    public OUTPUT (OutputStream outputStream) {
      writer = new PrintWriter (new BufferedWriter (new OutputStreamWriter (outputStream)));
    }
    
    public void print (Object...objects) {
      for (int i = 0; i < objects.length; ++i) {
        if (i != 0) writer.print (' ');
        writer.print (objects [i]);
      }
    }
    
    public void println (Object...objects) {
      print (objects);
      writer.println ();
    }
    
    public void close () {
      writer.close ();
    }
  }
}