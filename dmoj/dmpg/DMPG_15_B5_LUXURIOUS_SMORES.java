import java.io.*;
//import java.util.*;

public class DMPG_15_B5_LUXURIOUS_SMORES {
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    t = in.readLine ().split (" ");
    int N = Integer.parseInt (t [0]), K = Integer.parseInt (t [1]), L = Integer.parseInt (t [1]);
    
    int [] intense = new int [N], var = new int [N];
    
    for (int n = 0; n < N; n++) {
      intense [n] = Integer.parseInt (in.readLine ());
    }
    
    var [0] = Math.abs (intense [0] - intense [1]);
    var [N - 1] = Math.abs (intense [N - 1] - intense [N - 2]);
    int count = (var [0] <= L) ^ (intense [0] >= K) ? 1 : 0;
    count = (var [N - 1] <= L) ^ (intense [N - 1] >= K) ? count + 1 : count;
    
    for (int v = 1; v < N - 1; v++) {
      var [v] = Math.max (Math.abs (intense [v] - intense [v - 1]), Math.abs (intense [v] - intense [v + 1]));
      
      if ((var [v] <= L) ^ (intense [v] >= K)) {
        count++;
      }
    }
    
    System.out.println (count);
  }
}