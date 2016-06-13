import java.io.*;
import java.util.*;

public class JDCC_P4_LUCKY_TICKETS {
  
  static int sum1 = 0, sum2 = 0;
  static int [] half1, half2;
  
  public static void init (String N) {
    sum1 = 0;
    sum2 = 0;
    half1 = new int [N.length () / 2];
    half2 = new int [N.length () / 2];
    
    for (int i = 0; i < N.length () / 2; i++) {
      int digit = Integer.parseInt (N.charAt (i) + "");
      half1 [i] = digit;
      sum1 += digit;
    }
    
    for (int i = N.length () / 2; i < N.length (); i++) {
      int digit = Integer.parseInt (N.charAt (i) + "");
      half2 [i] = digit;
      sum2 += digit;
    }
  }
  
  public static String next (String N) {
    init (N);
    
    
    
    return N;
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int T = Integer.parseInt (in.readLine ());
    String N;
    
    for (int i = 0; i < T; i++) {
      N = in.readLine ();
      System.out.println (next (N));
    }
  }
}