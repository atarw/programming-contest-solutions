import java.io.*;
import java.util.*;

public class CCC_98_CENSOR {

  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());

    for (int i = 0; i < N; i++) {
      String n = in.readLine ();
      String [] tokens = n.split (" ");
      
      for (int x = 0; x < tokens.length; x++) {
        if (tokens [x].length () == 4) {
          System.out.print ("**** ");
        }
        else {
          System.out.print (tokens [x] + " ");
        }
      }
      System.out.println ();
    }
  }
}