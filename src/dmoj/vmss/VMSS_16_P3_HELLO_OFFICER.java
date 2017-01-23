import java.io.*;
import java.util.*;

public class VMSS_16_P3_HELLO_OFFICER {
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t = in.readLine ().split (" ");
    int N = Integer.parseInt (t [0]), M = Integer.parseInt (t [1]), B = Integer.parseInt (t [2]), Q = Integer.parseInt (t [3]);
    Graph g = new Graph (N);
    
    for (int m = 0; m < M; m++) {
      t = in.readLine ().split (" ");
      g.addEdge (Integer.parseInt (t [0]), Integer.parseInt (t [1]), Integer.parseInt (t [2]));
    }
    
    g.traverse (B);
    
    /*for (int i : Graph.cache) {
     System.out.print (i + " ");
     }
     
     System.out.println ();*/
    
    for (int q = 0; q < Q; q++) {
      System.out.println (g.query (Integer.parseInt (in.readLine ())));
    }
  }

  private static class Graph {
    static Map <Integer, Set <Edge>> map = new HashMap <Integer, Set <Edge>> ();
    static int [] cache;

    public void traverse (int S) {
      Arrays.fill (cache, -1);
      cache [S - 1] = 0;

      Queue <Integer> queue = new ArrayDeque <Integer> ();
      int curr;
      queue.offer (S);

      while (!queue.isEmpty ()) {
        curr = queue.poll ();

        if (map.containsKey (curr)) {
          for (Edge e : map.get (curr)) {
            if (cache [e.E - 1] == -1 || cache [e.E - 1] > cache [curr - 1] + e.W) {
              cache [e.E - 1] = cache [curr - 1] + e.W;
              queue.offer (e.E);
            }
          }
        }
      }
    }

    public int query (int E) {
      return cache [E - 1];
    }

    public void addEdge (int S, int E, int W) {
      if (!map.containsKey (S)) {
        map.put (S, new HashSet <Edge> ());
      }

      if (!map.containsKey (E)) {
        map.put (E, new HashSet <Edge> ());
      }

      map.get (S).add (new Edge (E, W));
      map.get (E).add (new Edge (S, W));
    }

    public Graph (int N) {
      cache = new int [N];
    }
  }

  private static class Edge {
    int E, W;

    public boolean equals (Object o) {
      Edge e = (Edge) o;

      return E == e.E && W == e.W;
    }

    public int hashCode () {
      return E * 17 + W * 37;
    }

    public Edge (int E, int W) {
      this.E = E;
      this.W = W;
    }
  }
}