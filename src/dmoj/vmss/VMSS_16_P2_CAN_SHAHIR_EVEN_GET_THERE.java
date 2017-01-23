import java.io.*;
import java.util.*;

public class VMSS_16_P2_CAN_SHAHIR_EVEN_GET_THERE {
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t = in.readLine ().split (" ");
    int N = Integer.parseInt (t [0]), M = Integer.parseInt (t [1]), A = Integer.parseInt (t [2]), B = Integer.parseInt (t [3]);
    Graph g = new Graph ();
    
    for (int m = 0; m < M; m++) {
      t = in.readLine ().split (" ");
      g.addEdge (Integer.parseInt (t [0]), Integer.parseInt (t [1]));
    }
    
    System.out.println (g.possible (A, B) ? "GO SHAHIR!" : "NO SHAHIR!");
  }

  private static class Graph {
    static Map <Integer, Set <Integer>> map = new HashMap <Integer, Set <Integer>> ();

    public void addEdge (int S, int E) {
      if (!map.containsKey (S)) {
        map.put (S, new HashSet <Integer> ());
      }

      if (!map.containsKey (E)) {
        map.put (E, new HashSet <Integer> ());
      }

      map.get (S).add (E);
      map.get (E).add (S);
    }

    public boolean possible (int A, int B) {

      if (A == B) {
        return true;
      }

      Queue <Integer> queue = new ArrayDeque <Integer> ();
      Set <Integer> visited = new HashSet <Integer> ();

      int curr;
      queue.offer (A);

      while (!queue.isEmpty ()) {
        curr = queue.poll ();
        visited.add (curr);

        if (map.containsKey (curr)) {
          for (Integer i : map.get (curr)) {
            if (i == B) {
              return true;
            }

            if (!visited.contains (i)) {
              queue.offer (i);
            }
          }
        }
      }

      return false;
    }
  }
}