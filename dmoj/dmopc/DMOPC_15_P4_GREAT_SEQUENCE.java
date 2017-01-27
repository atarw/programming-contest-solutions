import java.io.*;
import java.util.*;

public class DMOPC_15_P4_GREAT_SEQUENCE {
  
  static int [] arr;
  static Map <Integer, NavigableSet <Integer>> map = new HashMap <Integer, NavigableSet <Integer>> ();
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t = in.readLine ().split (" ");
    int N = Integer.parseInt (t [0]), K = Integer.parseInt (t [1]), Q = Integer.parseInt (t [2]);
    arr = new int [N + 1];
    
    t = in.readLine ().split (" ");
    
    for (int i = 0; i < t.length; i++) {
      arr [i + 1] = arr [i] + Integer.parseInt (t [i]);
      
      if (!map.containsKey (arr [i + 1] - arr [i])) {
        map.put (arr [i + 1] - arr [i], new TreeSet <Integer> ());
      }
      
      map.get (arr [i + 1] - arr [i]).add (i + 1);
    }
    
    //System.out.println (Arrays.toString (arr));
    
    int A, B, X, Y;
    
    for (int q = 0; q < Q; q++) {
      t = in.readLine ().split (" ");
      A = Integer.parseInt (t [0]);
      B = Integer.parseInt (t [1]);
      X = Integer.parseInt (t [2]);
      Y = Integer.parseInt (t [3]);
      
      if (arr [Y] - arr [X - 1] > K && map.containsKey (A) && map.containsKey (B) && !map.get (A).subSet (X, true, Y, true).isEmpty () && !map.get (B).subSet (X, true, Y, true).isEmpty ()) {
        System.out.println ("Yes");
      }
      else {
        System.out.println ("No");
      }
    }
  }
}