import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DMOPC_15_P4_LINE_GRAPH {
  
  static int [] parent, rank;
  
  public static int find (int item) {
    if (parent [item] != item) {
      parent [item] = find (parent [item]);
    }
    
    return parent [item];
  }
  
  public static boolean union (int item1, int item2) {
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
    else {
      return false;
    }
    
    find (item1);
    find (item2);
    
    return true;
  }
  
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    t = in.readLine ().split (" ");
    int N = Integer.parseInt (t [0]), K = Integer.parseInt (t [1]);
    
    t = in.readLine ().split (" ");
    List <Edge> list = new ArrayList <Edge> ();
    
    parent = new int [N];
    rank = new int [N];
    
    for (int n = 0; n < N; n++) {
      parent [n] = n;
      rank [n] = 0;
    }
    
    for (int n = 0; n < N - 1; n++) {
      list.add (new Edge (n, n + 1, Integer.parseInt (t [n])));
      
      if (n + K < N) {
        list.add (new Edge (n, n + K, 0));
      }
    }
    
    Collections.sort (list);
    int total = 0;
    
    for (Edge e : list) {
      if (union (e.S, e.E)) {
        total += e.W;
      }
    }
    
    System.out.print (total);
  }

  private static class Edge implements Comparable <Edge> {

    int S, E, W;

    public Edge (int S, int E, int W) {
      this.S = S;
      this.E = E;
      this.W = W;
    }

    public int compareTo (Edge e) {
      return W - e.W;
    }
  }
}