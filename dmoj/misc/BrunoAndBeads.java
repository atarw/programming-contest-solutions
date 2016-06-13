import java.io.*;

public class BrunoAndBeads {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    in.readLine ();
    String ln = in.readLine ();
    
    int num = 0;
    
    for (int i = 1; i < ln.length () && num < 3; i++) {
      if (ln.charAt (i) != ln.charAt (i - 1)) {
        num++;
      }
    }
    
    System.out.println (num == 3 ? "FIX YOUR BEADS!" : "Organized");
  }
}