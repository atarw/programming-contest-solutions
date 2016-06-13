import java.io.*;
import java.util.*;

public class MEC_16_P4_CIRCUITRY {
  
  static Edge [] edges;
  static List <Edge> [] list;
  static final String [] ans = {"useful", "so so", "not useful"};
  static int [] status;
  static boolean [] visited;
  
  public static boolean connected (int curr, int dest, int maxWeight) {
    visited [curr] = true;
    
    if (curr == dest) {
      visited [curr] = false;
      return true;
    }
    
    if (list [curr] != null) {
      for (Edge e : list [curr]) {
        if (!visited [e.E] && e.W < maxWeight) {
          if (connected (e.E, dest, maxWeight)) {
            visited [curr] = false;
            return true;
          }
        }
      }
    }
    
    visited [curr] = false;
    return false;
  }
  
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    t = in.readLine ().split (" ");
    
    int N = Integer.parseInt (t [0]), M = Integer.parseInt (t [1]);
    edges = new Edge [M];
    status = new int [N];
    visited = new boolean [N];
    list = new ArrayList [N];
    
    for (int m = 0; m < M; m++) {
      t = in.readLine ().split (" ");
      edges [m] = new Edge (Integer.parseInt (t [0]) - 1, Integer.parseInt (t [1]) - 1, Integer.parseInt (t [2]));
      
      if (list [edges [m].S] == null) {
        list [edges [m].S] = new ArrayList <Edge> ();
      }
      
      if (list [edges [m].E] == null) {
        list [edges [m].E] = new ArrayList <Edge> ();
      }
      
      list [edges [m].S].add (edges [m]);
      list [edges [m].E].add (new Edge (edges [m].E, edges [m].S, edges [m].W));
    }
    
    Arrays.sort (edges);
    Arrays.fill (status, 1);
    DISJOINT_SET set = new DISJOINT_SET (N);
    
    for (int m = 0; m < M; m++) {
      if (connected (edges [m].S, edges [m].E, edges [m].W)) {//not useful
        status [m] = 2;
      }
      else if () {//useful
        
      }
    }
    
    for (int s = 0; s < status.length; s++) {
      System.out.println (ans [status [s]]);
    }
  }
}

class Edge implements Comparable <Edge> {
  int S, E, W;
  
  public int compareTo (Edge e) {
    return W - e.W;
  }
  
  public Edge (int S, int E, int W) {
    this.S = S;
    this.E = E;
    this.W = W;
  }
}

class DISJOINT_SET {
  
  //parent stores root of tree containing item x, rank stores depth of trees containing item x
  int [] parent, rank;
  
  public void union (int item1, int item2) {
    int root1 = find (item1), root2 = find (item2);
    
    if (root1 != root2) {
      if (rank [root1] < rank [root2]) {
        parent [root1] = root2;
      }
      else if (rank [root1] > rank [root2]) {
        parent [root2] = root1;
      }
      else {
        parent [root2] = root1;
        rank [root1]++;
      }
    }
    
    find (item1);
    find (item2);
  }
  
  public int find (int item) {
    if (parent [item] != item) {
      parent [item] = find (parent [item]);
    }
    
    return parent [item];
  }
  
  public DISJOINT_SET (int N) {
    parent = new int [N];
    rank = new int [N];
    
    for (int n = 0; n < N; n++) {
      parent [n] = n;
      rank [n] = 0;
    }
  }
}