import java.io.*;
import java.util.*;

public class CCC_13_P2_BRIDGE_TRANSPORT {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    int W = Integer.parseInt (in.readLine ());
    int N = Integer.parseInt (in.readLine ());
    
    Queue <Integer> queue = new ArrayDeque <Integer> ();
    int [] sums = new int [N + 1];
    sums [0] = 0;
    
    int x;
    
    for (int n = 1; n <= N; n++) {
      x = Integer.parseInt (in.readLine ());
      
      sums [n] = sums [n - 1] + x;
      queue.offer (x);
    }
    
    int curr = 1;
    
    for (int i : sums) {
      System.out.print (i + " ");
    }
    System.out.println ();
    
    while (curr != N) {
      int w = sums [(curr + 3 > N ? N : curr + 3)] - sums [curr - 1];
      
      System.out.println (sums [(curr + 3 > N ? N : curr + 3)] + " " + sums [curr - 1] + " " + w);
      
      if (w > W) {
        for (int c = curr; curr < c + 3 && queue.poll () <= W; curr++) {
          curr++;
        }
        
        break;
      }
      else {
        queue.poll ();
        curr++;
      }
    }
    
    System.out.println (curr - 1);
  }
}