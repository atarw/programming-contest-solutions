import java.io.*;
import java.util.*;

public class P4 {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t = in.readLine ().split (" ");
    int N = Integer.parseInt (t [0]);
    int K = Integer.parseInt (t [1]);
    int Q = Integer.parseInt (t [2]);
    
    Map <Integer, TreeSet <Integer>> map = new HashMap <Integer, TreeSet <Integer>> ();
    
    int [] arr = new int [N];
    t = in.readLine ().split (" ");
    
    arr [0] = Integer.parseInt (t [0]);
    int a;
    
    map.put (arr [0], new TreeSet <Integer> ());
    map.get (arr [0]).add (0);
    
    for (int i = 1; i < t.length; i++) {
      a = Integer.parseInt (t [i]);
      arr [i] = arr [i - 1] + Math.abs (a);
      
      if (!map.containsKey (a)) {
        map.put (a, new TreeSet <Integer> ());
      }
      
      map.get (a).add (i);
    }
    
    int A, B, X, Y, sum;
    
    for (int q = 0; q < Q; q++) {
      t = in.readLine ().split (" ");
      A = Integer.parseInt (t [0]);
      B = Integer.parseInt (t [1]);
      X = Integer.parseInt (t [2]);
      Y = Integer.parseInt (t [3]);
      
      sum = arr [Y - 1] - arr [X - 1];
      
      if (K < sum && !map.get (A).subSet (X - 1, true, Y - 1, true).isEmpty () && !map.get (B).subSet (X - 1, true, Y - 1, true).isEmpty ()) {
        System.out.println ("Yes");
      }
      else {
        System.out.println ("No");
      }
    }
  }
}