import java.io.*;
import java.util.*;

public class P3 {
  
  static Map <Integer, Set <Integer>> map = new HashMap <Integer, Set <Integer>> ();
  static int N, M;
  
  public static boolean cycle () {//WOW THIS FEELS SO WRONG
    for (int a = 1; a <= N; a++) {
      for (Integer b : map.get (a)) {
        if (a == b) continue;
        
        for (Integer c : map.get (b)) {
          if (c == b || c == a) continue;
          for (Integer d : map.get (c)) {
            if (d == b || d == a || d == c) continue;
            for (Integer e : map.get (d)) {
              if (e == b || e == a || e == c || e == d) continue;
              for (Integer f : map.get (e)) {
                if (f == b || f == a || f == c || f == d || f == e) continue;
                for (Integer g : map.get (f)) {
                  if (a == g && g != b && g != c && g != d && g != e && g != f) {
                    return true;
                  }
                }
              }
            }
          }
        }
      }
    }
    
    return false;
  }
  
  public static void addEdge (int S, int E) {
    if (!map.containsKey (S)) {
      map.put (S, new HashSet <Integer> ());
    }
    
    if (!map.containsKey (E)) {
      map.put (E, new HashSet <Integer> ());
    }
    
    map.get (S).add (E);
    map.get (E).add (S);
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t = in.readLine ().split (" ");
    N = Integer.parseInt (t [0]);
    M = Integer.parseInt (t [1]);
    
    for (int m = 0; m < M; m++) {
      t = in.readLine ().split (" ");
      addEdge (Integer.parseInt (t [0]), Integer.parseInt (t [1]));
    }
    
    System.out.println (cycle () ? "YES" : "NO");
  }
}