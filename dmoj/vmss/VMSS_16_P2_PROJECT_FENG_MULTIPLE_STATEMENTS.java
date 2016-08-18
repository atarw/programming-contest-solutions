import java.io.*;
import java.util.*;

public class VMSS_16_P2_PROJECT_FENG_MULTIPLE_STATEMENTS {
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));    
    int N = Integer.parseInt (in.readLine ());
    NavigableMap <Integer, Integer> map = new TreeMap <Integer, Integer> (Collections.reverseOrder ());
    
    int c = 0;
    
    for (int n = 0; n < N; n++) {
      int A = Integer.parseInt (in.readLine ());
      
      if (A != 0) {
        if (!map.containsKey (A)) {
          map.put (A, 0);
        }
        
        map.put (A, map.get (A) + 1);
      }
    }
    
    for (Integer i : map.keySet ()) {
      if (i <= map.get (i)) {
        c = i;
        break;
      }
    }
    
    if (c == 0) {
      System.out.println ("Paradox!");
    }
    else {
      System.out.println (c);
    }
  }
}