import java.io.*;

public class VMSS_GOON {
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] tokens = in.readLine ().split ("-");
    int [] sums = {0, 0, 0};
    
    for (int i = 0; i < tokens.length; i++) {
      for (int x = 0; x < tokens [i].length (); x++) {
        sums [i] += Integer.parseInt (tokens [i].charAt (x) + "");
      }
    }
    
    if (sums [0] == sums [1] && sums [1] == sums [2]) {
      System.out.println ("Goony!");
    }
    else {
      System.out.println ("Pick up the phone!");
    }
  }
}