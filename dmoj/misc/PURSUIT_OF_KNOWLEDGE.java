import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PURSUIT_OF_KNOWLEDGE {
  
  static int T;
  
  public static void main (String [] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    String [] t = in.readLine ().split (" ");
    int N = Integer.parseInt (t [0]), M = Integer.parseInt (t [1]);
    T = Integer.parseInt (t [2]);
    
    Graph g = new Graph (N);
    
    for (int m = 0; m < M; m++) {
      t = in.readLine ().split (" ");
      g.addEdge (Integer.parseInt (t [0]), Integer.parseInt (t [1]));
    }
    
    for (int n = 1; n <= N; n++) {
      g.traverse (n);
    }
    
    int Q = Integer.parseInt (in.readLine ());
    int ans;
    
    for (int q = 0; q < Q; q++) {
      t = in.readLine ().split (" ");
      ans = g.query (Integer.parseInt (t [0]), Integer.parseInt (t [1]));
      
      System.out.println (ans == -1 ? "Not enough hallways!" : ans);
    }
  }

	private static class Graph {

		static Map <Integer, Set <Integer>> map = new HashMap <Integer, Set <Integer>> ();
		static int[][] cache;

		public Graph (int N) {
			cache = new int[N][N];
		}

		public void traverse (int S) {
			Queue <Integer> queue = new ArrayDeque <Integer> ();
			int curr;
			queue.offer (S);

			while (!queue.isEmpty ()) {
				curr = queue.poll ();

				if (map.containsKey (curr)) {
					for (Integer i : map.get (curr)) {
						if (cache[curr - 1][i - 1] == 0 || cache[S - 1][i - 1] == 0 || cache[S - 1][i - 1] >= cache[S
								- 1][curr - 1] + T) {
							cache[S - 1][i - 1] = cache[S - 1][curr - 1] + T;
							queue.offer (i);
						}
						cache[curr - 1][i - 1] = T;
					}
				}
			}
		}

		public int query (int S, int E) {
			if (cache[S - 1][E - 1] == 0) {
				return -1;
			}
			else {
				return cache[S - 1][E - 1];
			}
		}

		public void addEdge (int S, int E) {
			if (!map.containsKey (S)) {
				map.put (S, new HashSet <Integer> ());
			}

			map.get (S).add (E);
		}
  }
}