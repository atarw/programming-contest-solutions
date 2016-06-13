import java.io.*;
import java.util.*;

public class CCC_16_P3_PHONOMENAL_REVIEWS {
  
  static boolean [] pho, visited;
  static Graph g;
  static int END, DIST, deleted;
  static int [] dist;
  
  public static boolean dfs (int curr) {
    visited [curr] = true;
    
    for (int i = 0; i < g.map [curr].size (); i++) {
      if (deleted <= pho.length && !visited [g.map [curr].get (i)] && dfs (g.map [curr].get (i))) {
        i--;
      }
    }
    
    if (!pho [curr] && g.map [curr].size () == 1) {
      g.map [g.map [curr].get (0)].remove (new Integer (curr));
      g.map [curr].clear ();
      deleted++;
      return true;
    }
    
    return false;
  }
  
  public static void bfs (int S) {
    Queue <Integer> queue = new ArrayDeque <Integer> ();
    visited = new boolean [visited.length];
    dist = new int [dist.length];
    int curr;
    
    queue.offer (S);
    
    while (!queue.isEmpty ()) {
      curr = queue.poll ();
      
      for (int i : g.map [curr]) {
        if (i != S && (dist [i] == 0 || dist [i] > dist [curr] + 1)) {
          dist [i] = dist [curr] + 1;
          queue.offer (i);
        }
      }
    }
    
    int currEnd = 0, maxDist = 0;
    
    for (int i = 0; i < dist.length; i++) {
      if (maxDist < dist [i]) {
        maxDist = dist [i];
        currEnd = i;
      }
    }
    
    END = currEnd;
    DIST = maxDist;
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t = in.readLine ().split (" ");
    int N = Integer.parseInt (t [0]), M = Integer.parseInt (t [1]), randomPho = 0;
    
    g = new Graph (N);
    pho = new boolean [N];
    visited = new boolean [N];
    dist = new int [N];
    
    t = in.readLine ().split (" ");
    
    for (int m = 0; m < M; m++) {
      pho [Integer.parseInt (t [m])] = true;
      randomPho = Integer.parseInt (t [m]);
    }
    
    for (int n = 0; n < N - 1; n++) {
      t = in.readLine ().split (" ");
      g.addEdge (Integer.parseInt (t [0]), Integer.parseInt (t [1]));
    }
    
    dfs (randomPho);
    bfs (randomPho);
    bfs (END);
    
    int diameter = DIST;
    System.out.print (2 * ((N - deleted) - (diameter + 1)) + diameter);
  }
}

class Graph {
  static List <Integer> [] map;
  
  public void addEdge (int S, int E) {
    
    if (map [S] == null) {
      map [S] = new ArrayList <Integer> ();
    }
    
    if (map [E] == null) {
      map [E] = new ArrayList <Integer> ();
    }
    
    map [S].add (E);
    map [E].add (S);
  }
  
  public Graph (int N) {
    map = new ArrayList [N];
  }
}