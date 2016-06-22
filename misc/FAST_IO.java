import java.io.*;
import java.util.InputMismatchException;

class FAST_IO_JAVA {
  
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
    
    public double readDouble () throws IOException {
      int c = read (), sgn = 1;
      while (isSpaceChar (c)) c = read ();
      
      if (c == '-') {
        sgn = -1;
        c = read ();
      }
      
      double res = 0;
      
      while (!isSpaceChar (c) && c != '.') {
        if (c == 'e' || c == 'E') return res * Math.pow (10, readInt ());
        if (c < '0' || c > '9') throw new InputMismatchException ();
        
        res *= 10;
        res += c - '0';
        c = read ();
      }
      
      if (c == '.') {
        c = read ();
        double m = 1;
        
        while (!isSpaceChar (c)) {
          if (c == 'e' || c == 'E') return res * Math.pow (10, readInt ());
          if (c < '0' || c > '9') throw new InputMismatchException ();
          
          m /= 10;
          res += (c - '0') * m;
          c = read ();
        }
      }
      
      return res * sgn;
    }
    
    public long readLong () throws IOException {
      int c = read (), sgn = 1;
      while (isSpaceChar (c)) c = read ();
      
      if (c == '-') {
        sgn = -1;
        c = read ();
      }
      
      long res = 0;
      
      do {
        if (c < '0' || c > '9') throw new InputMismatchException();
        
        res *= 10;
        res += c - '0';
        c = read ();
        
      }
      while (!isSpaceChar (c));
      
      return res * sgn;
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
    
    public OUTPUT (Writer writer) {
      this.writer = new PrintWriter (writer);
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
    
    public void flush () {
      writer.flush();
    }
  }
}