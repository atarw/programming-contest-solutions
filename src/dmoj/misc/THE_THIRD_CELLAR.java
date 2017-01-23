import java.io.*;

public class THE_THIRD_CELLAR {
  
  static boolean [] sieve = new boolean [1000000];
  static int [] arr = new int [1000000];
  
  public static void pre () {
    for (int i = 2; i < sieve.length; i++) {
      if (!sieve [i]) {
        arr [i] = arr [i - 1] + 1;
        
        for (int y = 1, z = i * y; z < sieve.length; y++, z = i * y) {
          sieve [z] = true;
        }
      }
      else {
        arr [i] = arr [i - 1];
      }
    }
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    String [] t;
    
    pre ();
    
    for (int n = 0; n < N; n++) {
      t = in.readLine ().split (" ");
      System.out.println (Math.abs (arr [Integer.parseInt (t [0]) - 1] - arr [Integer.parseInt (t [1]) - 1]));
    }
  }
}