import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CCC_03_S5_TRUCKING_TROUBLES {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t = in.readLine ().split (" ");

		int R = Integer.parseInt (t[1]), D = Integer.parseInt (t[2]);
		int[] destination = new int[D];
		Graph g = new Graph ();

		for (int r = 0; r < R; r++) {
			t = in.readLine ().split (" ");
			g.addEdge (Integer.parseInt (t[0]) - 1, Integer.parseInt (t[1]) - 1, Integer.parseInt (t[2]));
		}

		for (int d = 0; d < D; d++) {
			destination[d] = Integer.parseInt (in.readLine ()) - 1;
		}

		t = null;

		Arrays.sort (destination);
		System.out.print (g.prims (destination));

		in.close ();
	}

	private static class Graph {

		Map<Integer, Set<Edge>> map = new HashMap<Integer, Set<Edge>> ();

		public void addEdge (int S, int E, int W) {
			if (!map.containsKey (S)) {
				map.put (S, new HashSet<Edge> ());
			}

			if (!map.containsKey (E)) {
				map.put (E, new HashSet<Edge> ());
			}

			map.get (S).add (new Edge (E, W));
			map.get (E).add (new Edge (S, W));
		}

		public int prims (int[] destination) {
			int curr = 0, overallMin = Integer.MAX_VALUE;
			Queue<Integer> queue = new ArrayDeque<Integer> ();
			int[] cache = new int[map.size ()];

			queue.offer (curr);

			while (!queue.isEmpty ()) {
				curr = queue.poll ();

				for (Edge e : map.get (curr)) {
					if (e.W > cache[e.E]) {
						cache[e.E] = e.W;
						queue.offer (e.E);
					}
				}
			}

			for (int i = 0; i < cache.length; i++) {
				if (Arrays.binarySearch (destination, i) != -1) {
					overallMin = Math.min (overallMin, cache[i]);
				}
			}

			return overallMin;
		}
	}

	private static class Edge {

		int E, W;

		public Edge (int E, int W) {
			this.E = E;
			this.W = W;
		}

		public int hashCode () {
			return E * 17 + W * 37;
		}

		public boolean equals (Object o) {
			Edge e = (Edge) o;

			return E == e.E && W == e.W;
		}
	}
}