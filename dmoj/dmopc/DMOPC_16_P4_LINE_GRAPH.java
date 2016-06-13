import java.io.*;
import java.util.*;

public class DMOPC_16_P4_LINE_GRAPH {
  
  static List <Edge> [] list;
  
  public static int prims (int K) {
    return -1;
  }
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t = in.readLine ().split (" ");
    int N = Integer.parseInt (t [0]), K = Integer.parseInt (t [1]), W;
    list = new List [N];
    
    t = in.readLine ().split (" ");
    
    list [0] = new ArrayList ();
    
    for (int n = 0; n < N - 1; n++) {
      list [n].add (new Edge (n + 1, W = Integer.parseInt (t [n])));
      
      
      list [n + 1] = new ArrayList ();
      list [n + 1].add (new Edge (n, W));
    }
    
    System.out.println (prims (K));
  }
}

class Edge {
  int E, W;
  
  public Edge (int E, int W) {
    this.E = E;
    this.W = W;
  }
}