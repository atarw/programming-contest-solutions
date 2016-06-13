import java.io.*;
import java.util.*;

public class CCC_06_P4_ITS_TOUGH_BEING_A_TEEN {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    Graph g = new Graph ();
    
    g.addEdge (1, 7);
    g.addEdge (1, 4);
    g.addEdge (2, 1);
    g.addEdge (3, 4);
    g.addEdge (3, 5);
    Graph.map.put (6, new HashSet <Integer> ());
    
    int S = -1, E = -1;
    
    while ((S = Integer.parseInt (in.readLine ())) != 0 && (E = Integer.parseInt (in.readLine ())) != 0) {
      g.addEdge (S, E);
    }
    
    g.order ();
  }
}

class Graph {
  
  static NavigableMap <Integer, Set <Integer>> map = new TreeMap <Integer, Set <Integer>> ();
  
  public void addEdge (int S, int E) {
    if (!map.containsKey (S)) {
      map.put (S, new HashSet <Integer> ());
    }
    
    if (!map.containsKey (E)) {
      map.put (E, new HashSet <Integer> ());
    }
    
    map.get (S).add (E);
  }
  
  public int next () {
    boolean edge;
    
    for (int i : map.keySet ()) {
      edge = false;
      
      for (Set <Integer> x : map.values ()) {
        if (x.contains (i)) {
          edge = true;
          break;
        }
      }
      
      if (!edge) {
        return i;
      }
    }
    
    return -1;
  }
  
  public void order () {
    List <Integer> list = new ArrayList <Integer> ();
    
    while (!map.keySet ().isEmpty ()) {
      int curr = next ();
      
      if (curr == -1) {
        System.out.print ("Cannot complete these tasks. Going to bed.");
        return;
      }
      
      map.remove (curr);
      list.add (curr);
    }
    
    for (int i : list) {
      System.out.print (i + " ");
    }
  }
}