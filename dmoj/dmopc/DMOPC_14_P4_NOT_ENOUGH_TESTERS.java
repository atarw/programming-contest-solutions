import java.io.*;
import java.util.*;

public class DMOPC_14_P4_NOT_ENOUGH_TESTERS {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    Map <Integer, Integer> map = new HashMap <Integer, Integer> ();
    int T = Integer.parseInt (in.readLine ());
    
    for (int z = 0; z < T; z++) {
      String [] t = in.readLine ().split (" ");
      int K = Integer.parseInt (t [0]);
      int A = Integer.parseInt (t [1]), B = Integer.parseInt (t [2]);
      int count = 0;
      int factors = 0;
      int increment = 1;
      
      for (int i = A; i <= B; i++) {  
        if (!map.containsKey (i)) {
          increment = (i % 2 == 0 ? 1 : 2);
          
          for (int x = 1; x <= i; x = x + increment) {
            if (i % x == 0) {
              factors++;
            }
          }
          map.put (i, factors);
          factors = 0;
        }
        
        if (map.get (i) == K) {
          count++;
        }
      }
      
      System.out.println (count);
    }
  }
}