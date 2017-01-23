import java.io.*;

class FAST_IO {
  
  private static class INPUT {
    
    private InputStream stream;
    private byte [] buf = new byte [1024];
    private int curChar, numChars;
    
    public INPUT (InputStream stream) {
      this.stream = stream;
    }
    
    public static int fast_pow (int b, int x) {
      if (x == 0) return 1;
      if (x == 1) return b;
      if (x % 2 == 0) return fast_pow (b * b, x / 2);
      
      return b * fast_pow (b * b, x / 2);
    }
    
    public int cscan () throws IOException {
      //if (numChars == -1) throw new InputMismatchException ();
      
      if (curChar >= numChars) {
        curChar = 0;
        numChars = stream.read (buf);
        
        //if (numChars <= 0) return -1;
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
        //if (c < '0' || c > '9') throw new InputMismatchException ();
        
        res = (res << 1) + (res << 3);
        //res *= 10;
        res += c - '0';
        
        c = cscan ();
      }
      while (!space (c));
      
      return res * sgn;
    }
    
    public String sscan () throws IOException {
      int c = cscan ();
      while (space (c)) c = cscan();
      
      StringBuilder res = new StringBuilder();
      
      do
      {
        res.appendCodePoint (c);
        c = cscan ();
      }
      while (!space (c));
      
      return res.toString ();
    }
    
    public double dscan () throws IOException {
      int c = cscan (), sgn = 1;
      while (space (c)) c = cscan ();
      
      if (c == '-') {
        sgn = -1;
        c = cscan ();
      }
      
      double res = 0;
      
      while (!space (c) && c != '.') {
        if (c == 'e' || c == 'E') return res * fast_pow (10, iscan ()); /*Math.pow (10, iscan ());*/
        //if (c < '0' || c > '9') throw new InputMismatchException ();
        
        //res = (res << 1) + (res << 3);
        res *= 10;
        res += c - '0';
        c = cscan ();
      }
      
      if (c == '.') {
        c = cscan ();
        double m = 1;
        
        while (!space (c)) {
          if (c == 'e' || c == 'E') return res * fast_pow (10, iscan ()); /*Math.pow (10, iscan ());*/
          //if (c < '0' || c > '9') throw new InputMismatchException ();
          
          m /= 10;
          res += (c - '0') * m;
          c = cscan ();
        }
      }
      
      return res * sgn;
    }
    
    public long lscan () throws IOException {
      int c = cscan (), sgn = 1;
      while (space (c)) c = cscan ();
      
      if (c == '-') {
        sgn = -1;
        c = cscan ();
      }
      
      long res = 0;
      
      do {
        //if (c < '0' || c > '9') throw new InputMismatchException();
        
        res = (res << 1) + (res << 3);
        //res *= 10;
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