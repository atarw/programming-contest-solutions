import java.io.*;
import java.util.*;

public class CCO_14_P2_KING_GRUFF {
  
  static int [] cache;//the length of the shortest path between A and B using edge E
  static List <Edge> [] list;
  static Edge [] edges;
  //static boolean [] vis;
  static int A, B;
  
  public static int solve (int edge) {
    if (edges [edge].E == B) {
      cache [edge] = Math.min (cache [edge], edges [edge].L);
    }
    else if (cache [edge] == 0) {
      for (int e = 0; e < list [edges [edge].E].size (); e++) {
        cache [edge] = ;
      }
    }
    
    return cache [edge];
  }
  
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    t = in.readLine ().split (" ");
    
    int N = Integer.parseInt (t [0]), M = Integer.parseInt (t [1]); A = Integer.parseInt (t [2]) - 1; B = Integer.parseInt (t [3]) - 1;
    list = new ArrayList [N];
    //vis = new boolean [N];
    cache = new int [M]; //Arrays.fill (cache, Integer.MAX_VALUE);
    edges = new Edge [M];
    
    int s, e, l, c;
    
    for (int m = 0; m < M; m++) {
      t = in.readLine ().split (" ");
      s = Integer.parseInt (t [0]) - 1; e = Integer.parseInt (t [1]) - 1; l = Integer.parseInt (t [2]); c = Integer.parseInt (t [3]);
      
      if (list [s] == null) {
        list [s] = new ArrayList <Edge> ();
      }
      
      edges [m] = new Edge (m, s, e, l, c);
      list [s].add (edges [m]);
    }
  }
}

class Edge {
  int I, S, E, L, C;
  
  public Edge (int I, int S, int E, int L, int C) {
    this.I = I;
    this.S = S;
    this.E = E;
    this.L = L;
    this.C = C;
  }
}