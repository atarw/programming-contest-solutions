import java.io.*;
import java.util.*;

public class P2 {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    String [] stuff = new String [N];
    
    for (int n = 0; n < N; n++) {
      stuff [n] = in.readLine ();
    }
    
    for (int n = 1; n <= N; n++) {
      System.out.println ("On the " + n + (n % 10 == 1 && n % 100 != 11 ? "st" : n % 10 == 2 && n % 100 != 12 ? "nd" : n % 10 == 3 && n % 100 != 13 ? "rd" : "th") + " day of Christmas my true love sent to me:");
      
      for (int m = n; m >= 1; m--) {
        System.out.println ((m == 1 && m != n ? "and " : "") + m + " " + stuff [m - 1]);
      }
      System.out.println ();
    }
  }
}