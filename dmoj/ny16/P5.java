import java.io.*;
import java.util.*;

public class P5 {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t = in.readLine ().split (" ");
    int N = Integer.parseInt (t [0]);
    int K = Integer.parseInt (t [1]);
    
    t = in.readLine ().split (" ");
    
    int [] B = new int [N];
    
    for (int n = 0; n < N; n++) {
      B [n] = Integer.parseInt (t [n]);
    }
    
    int Q = Integer.parseInt (in.readLine ());
    
    for (int q = 0; q < Q; q++) {
      t = in.readLine ().split (" ");
    }
  }
}