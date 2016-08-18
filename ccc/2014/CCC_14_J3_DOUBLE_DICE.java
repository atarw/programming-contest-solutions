import java.io.*;

public class CCC_14_J3_DOUBLE_DICE {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int N = Integer.parseInt (in.readLine ());
    int s1 = 100, s2 = 100;
    
    for (int i = 0; i < N; i++) {
      String [] tokens = in.readLine ().split (" ");
      int A = Integer.parseInt (tokens [0]);
      int D = Integer.parseInt (tokens [1]);
      
      if (A > D) {
        s2 -= A;
      }
      else if (A < D) {
        s1 -= D;
      }
    }
    
    System.out.println (s1 + "\n" + s2);
  }
}