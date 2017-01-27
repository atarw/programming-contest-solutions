import java.io.*;
import java.util.*;

public class CCC_13_J2_ROTATING_LETTERS {

  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String n = in.readLine ();
    boolean f = true;
    
    for (int i = 0; i < n.length (); i++) {
      if (!(n.charAt (i) == 'I' || n.charAt (i) == 'O' || n.charAt (i) == 'S' || n.charAt (i) == 'H' || n.charAt (i) == 'Z' || n.charAt (i) == 'X' || n.charAt (i) == 'N')) {
        System.out.println ("NO");
        f = false;
        break;
      }
    }
    
    if (f) {
      System.out.println ("YES");
    }
  }
}