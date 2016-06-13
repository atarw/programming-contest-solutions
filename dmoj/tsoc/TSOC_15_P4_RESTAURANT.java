import java.io.*;

public class TSOC_15_P4_RESTAURANT {
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int T = Integer.parseInt (in.readLine ());
    
    String [] tokens = in.readLine ().split (" ");
    int BMPX = Integer.parseInt (tokens [0]), BMPY = Integer.parseInt (tokens [1]);
    
    tokens = in.readLine ().split (" ");
    int LX = Integer.parseInt (tokens [0]), LY = Integer.parseInt (tokens [1]);
    
    int Lparity = (LX + LY) % 2, BMPparity = (BMPX + BMPY) % 2;
    int length = Math.max (Math.abs (LY - BMPY), Math.abs (LX - BMPX));
    
    if (Lparity != BMPparity) {
      System.out.println ("Cannot physically get there.");
    }
    else if (length > T) {
      System.out.println ("Cannot get there on time.");
    }
    else {
      System.out.printf ("It takes %d minute(s) to get to (%d, %d).\n", length, LX, LY);
    }
  }
}