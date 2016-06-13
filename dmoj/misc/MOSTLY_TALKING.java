import java.io.*;
import java.util.*;

public class MOSTLY_TALKING {
  
  static List <Edge> [] map, reverse;
  
  public static void addEdge (int S, int E, int W) {
    if (map [S] == null) {
      map [S] = new ArrayList <Edge> ();
    }
    
    if (reverse [E] == null) {
      reverse [E] = new ArrayList <Edge> ();
    }
    
    map [S].add (new Edge (E, W));
    reverse [E].add (new Edge (S, W));
  }
  
  public static int [] path (int S, List <Edge> [] graph) {
    int [] cache = new int [graph.length];
    boolean [] visited = new boolean [graph.length];
    Queue <Integer> queue = new ArrayDeque <Integer> ();
    int curr;
    
    queue.offer (S);
    Arrays.fill (cache, Integer.MAX_VALUE);
    cache [S] = 0;
    
    while (!queue.isEmpty ()) {
      curr = queue.poll ();
      
      for (Edge e : graph [curr]) {
        if (cache [e.E] > cache [curr] + e.W) {
          cache [e.E] = cache [curr] + e.W;
          
          if (!visited [e.E] && graph [e.E] != null) {
            queue.offer (e.E);
          }
          
          visited [e.E] = true;
        }
      }
      
      visited [curr] = true;
    }
    
    return cache;
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t = in.readLine ().split (" ");
    
    int N = Integer.parseInt (t [0]), M = Integer.parseInt (t [1]), S, E, W;
    map = new ArrayList [N];
    reverse = new ArrayList [N];
    
    for (int m = 0; m < M; m++) {
      t = in.readLine ().split (" ");
      addEdge (Integer.parseInt (t [0]) - 1, Integer.parseInt (t [1]) - 1, Integer.parseInt (t [2]));
    }
    
    int D = Integer.parseInt (in.readLine ());
    
    int [] start = path (0, map);
    map = null;
    int [] end = path (N - 1, reverse);
    reverse = null;
    int min = start [N - 1];
    
    for (int d = 0; d < D; d++) {
      t = in.readLine ().split (" ");
      S = Integer.parseInt (t [0]) - 1;
      E = Integer.parseInt (t [1]) - 1;
      W = Integer.parseInt (t [2]);
      
      if (start [S] != Integer.MAX_VALUE && end [E] != Integer.MAX_VALUE) {
        min = Math.min (min, start [S] + W + end [E]);
      }
    }
    
    System.out.print (min == Integer.MAX_VALUE ? -1 : min);
  }
}

class Edge {
  int E, W;
  
  public Edge (int E, int W) {
    this.E = E;
    this.W = W;
  }
}