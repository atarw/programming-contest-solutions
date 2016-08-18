import java.io.*;

public class CODEJAM_ROUND_1A_16_P1_THE_LAST_WORD {
  
  public static void out (String ln) {
    StringBuilder b = new StringBuilder (ln.length ());
    char first = ln.charAt (0), last = ln.charAt (0);
    b.append (first);
    
    for (int i = 1; i < ln.length (); i++) {
      if (ln.charAt (i) >= first) {
        b.insert (0, ln.charAt (i));
        first = ln.charAt (i);
      }
      else {
        b.append (ln.charAt (i));
        last = ln.charAt (i);
      }
    }
    
    System.out.println (b);
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int T = Integer.parseInt (in.readLine ());
    
    for (int t = 1; t <= T; t++) {
      System.out.print ("Case #" + t + ": ");
      out (in.readLine ());
    }
  }
}