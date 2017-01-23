import java.io.*;
import java.util.*;

public class VMSS_15_P3_CHAIN_RULE {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t = in.readLine ().split (" ");
    int N = Integer.parseInt (t [0]), M = Integer.parseInt (t [1]);
    Graph g = new Graph (N);
    
    for (int m = 0; m < M; m++) {
      t = in.readLine ().split (" ");
      g.addEdge (Integer.parseInt (t [0]), Integer.parseInt (t [1]), Integer.parseInt (t [2]));
    }
    
    g.traverse (0, 0);
    g.traverse (1, N - 1);
    
    int max = Integer.MIN_VALUE;
    
    for (int n = 0; n < N; n++) {
      max = Math.max (max, Graph.cache [0][n] + Graph.cache [1][n]);
    }
    
    System.out.println (max);
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

  private static class Graph {

    static int [][] cache;
    static Map <Integer, Set <Edge>> map = new HashMap <Integer, Set <Edge>> ();

    public void traverse (int r, int S) {
      NavigableSet <Integer> queue = new TreeSet <Integer> ();
      int curr;
      queue.add (S);

      while (!queue.isEmpty ()) {
        curr = queue.pollFirst ();

        if (map.containsKey (curr)) {
          for (Edge e : map.get (curr)) {
            if (e.E != S && (cache [r][e.E] == 0 || cache [r][e.E] > cache [r][curr] + e.W)) {
              cache [r][e.E] = cache [r][curr] + e.W;
              queue.add (e.E);
            }
          }
        }
      }
    }

    public void addEdge (int S, int E, int W) {
      if (!map.containsKey (S)) {
        map.put (S, new HashSet <Edge> ());
      }

      if (!map.containsKey (E)) {
        map.put (E, new HashSet <Edge> ());
      }

      map.get (E).add (new Edge (S, W));
      map.get (S).add (new Edge (E, W));
    }

    public Graph (int N) {
      cache = new int [2][N];
    }
  }
}