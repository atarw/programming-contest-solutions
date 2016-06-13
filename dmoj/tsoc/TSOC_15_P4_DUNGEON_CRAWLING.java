import java.io.*;
import java.util.*;

public class TSOC_15_P4_DUNGEON_CRAWLING {
  
  static Map <Integer, Set <Integer>> map = new HashMap <Integer, Set <Integer>> ();
  static int [] steps, best, indegree, outdegree;
  static int MOD = 10000009;
  
  public static void minCut (int X) {
    Deque <Integer> queue = new ArrayDeque <Integer> ();
    boolean [] visited = new boolean [steps.length];
    
    int curr;
    
    queue.offer (X);
    steps [X]++;
    
    while (!queue.isEmpty ()) {
      curr = queue.poll ();
      visited [curr] = true;
      
      if (map.containsKey (curr)) {
        for (int i : map.get (curr)) {
          
          if (best [i] == 0 || best [i] > best [curr] + 1) {
            best [i] = best [curr] + 1;
          }
          
          if (!visited [i]) {
            queue.offerLast (i);
            visited [i] = true;
          }
          
          steps [i] += (steps [curr] % MOD) % MOD;
        }
      }
    }
  }
  
  public static void addEdge (int S, int E) {
    if (!map.containsKey (S)) {
      map.put (S, new HashSet <Integer> ());
    }
    
    map.get (S).add (E);
  }
  
  public static void main (String [] t) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    t = in.readLine ().split (" ");
    
    int N = Integer.parseInt (t [0]), M = Integer.parseInt (t [1]), S, E;
    steps = new int [N];
    best = new int [N];
    
    indegree = new int [N];
    outdegree = new int [N];
    
    for (int m = 0; m < M; m++) {
      t = in.readLine ().split (" ");
      
      addEdge (S = (Integer.parseInt (t [0])), E = (Integer.parseInt (t [1])));
      indegree [E]++;
      outdegree [S]++;
    }
    
    int total = 0;
    
    for (int n = 0; n < N; n++) {
      if (indegree [n] == 0) {
        minCut (n);
        total += steps [n] % MOD;
      }
    }
    
    System.out.println (total);
  }
}