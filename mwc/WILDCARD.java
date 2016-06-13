import java.io.*;
import java.util.*;

public class WILDCARD {
  
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    t = in.readLine ().split (" ");
    
    int N = Integer.parseInt (t [0]), K = Integer.parseInt (t [1]), T = Integer.parseInt (t [2]), W, pairs = 0;
    
    String [] names = {"kushanzaveri", "aurpine"};
    Map <Integer, Integer> map = new HashMap <Integer, Integer> (N);
    
    t = in.readLine ().split (" ");
    
    for (int n = 0; n < N; n++) {
      W = Integer.parseInt (t [n]);
      
      if (map.containsKey (K - W)) {
        if (map.get (K - W) == 1) {
          map.remove (K - W);
        }
        else {
          map.put (K - W, map.get (K - W) - 1);
        }
        
        pairs++;
        T ^= 1;
      }
      else {
        if (!map.containsKey (W)) {
          map.put (W, 1);
        }
        else {
          map.put (W, map.get (W) + 1);
        }
      }
    }
    
    System.out.println (names [T] + " " + (pairs / 2 + pairs % 2));
  }
}