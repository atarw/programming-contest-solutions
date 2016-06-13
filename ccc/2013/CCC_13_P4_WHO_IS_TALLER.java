import java.io.*;
import java.util.*;

public class CCC_13_P4_WHO_IS_TALLER {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t = in.readLine ().split (" ");
    int N = Integer.parseInt (t [0]);
    int M = Integer.parseInt (t [1]);
    
    Graph g = new Graph ();
    
    for (int m = 0; m < M; m++) {
      t = in.readLine ().split (" ");
      g.addEdge (Integer.parseInt (t [1]), Integer.parseInt (t [0]));
    }
    
    t = in.readLine ().split (" ");
    int P = Integer.parseInt (t [0]);
    int Q = Integer.parseInt (t [1]);
    
    if (g.connected (P, Q)) {
      System.out.println ("no");
    }
    else if (g.connected (Q, P)) {
      System.out.println ("yes");
    }
    else {
      System.out.println ("unknown");
    }
  }
}

class Graph {
  
  Map <Integer, List <Integer>> map = new HashMap <Integer, List <Integer>> ();
  
  public void addEdge (Integer taller, Integer shorter) {
    if (!map.containsKey (taller)) {
      map.put (taller, new ArrayList <Integer> ());
    }
    
    map.get (taller).add (shorter);
  }
  
  /*public boolean connected (int p1, int p2) {
   Queue <Integer> queue = new ArrayDeque <Integer> ();
   Set <Integer> visited = new HashSet <Integer> ();
   
   queue.offer (p1);
   int curr;
   
   while (!queue.isEmpty ()) {
   curr = queue.poll ();
   visited.add (curr);
   
   if (curr == p2) {
   return true;
   }
   
   if (map.containsKey (curr)) {
   for (Integer i : map.get (curr)) {
   if (i == p2) {
   return true;
   }
   else if (!visited.contains (i)) {
   queue.add (i);
   }
   }
   }
   }
   
   return false;
   }*/
  
  public boolean connected (int p1, int p2) {
    if (p1 == p2) {
      return true;
    }
    
    if (map.containsKey (p1)) {
      for (Integer i : map.get (p1)) {
        if (connected (i, p2)) {
          return true;
        }
      }
    }
    
    return false;
  }
  
  public Graph () {}
}