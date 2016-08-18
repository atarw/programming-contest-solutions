import java.io.*;
import java.util.*;

public class CCC_15_S3_GATES {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    NavigableSet <Integer> open = new TreeSet <Integer> ();
    
    int G = Integer.parseInt (in.readLine ());
    int P = Integer.parseInt (in.readLine ());
    
    for (int g = 1; g <= G; g++) {
      open.add (g);
    }
    
    int v, total = 0;
    Integer next;
    
    for (int p = 0; p < P; p++) {
      v = Integer.parseInt (in.readLine ());
      
      next = open.floor (v);
      
      if (next == null) {
        break;
      }
      else {
        open.remove (next);
        total++;
      }
    }
    
    System.out.print (total);
  }
}