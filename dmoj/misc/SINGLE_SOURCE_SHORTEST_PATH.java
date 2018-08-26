import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

// atharva washimkar
// August 11, 2018

public class SINGLE_SOURCE_SHORTEST_PATH {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t = in.readLine ().split (" ");
		int N = Integer.parseInt (t[0]), M = Integer.parseInt (t[1]);
		Graph g = new Graph (N);

		for (int m = 0; m < M; m++) {
			t = in.readLine ().split (" ");
			g.addEdge (Integer.parseInt (t[0]), Integer.parseInt (t[1]), Integer.parseInt (t[2]));
		}

		g.traverse (0);

		for (int i : Graph.cache) {
			System.out.println (i);
		}
	}

	private static class Graph {

		static int[][] matrix;
		static int[] cache;

		public Graph (int N) {
			matrix = new int[N][N];
			cache = new int[N];
			Arrays.fill (cache, -1);
			cache[0] = 0;
		}

		public void traverse (int S) {
			Queue<Integer> queue = new ArrayDeque<Integer> ();
			int curr;

			queue.offer (S);

			while (!queue.isEmpty ()) {
				curr = queue.poll ();

				for (int i = 0; i < matrix[0].length; i++) {
					if (matrix[curr][i] != 0 && (cache[i] == -1 || cache[i] > cache[curr] + matrix[curr][i])) {
						cache[i] = cache[curr] + matrix[curr][i];
						queue.offer (i);
					}
				}
			}
		}

		public void addEdge (int S, int E, int W) {
			if (matrix[S - 1][E - 1] == 0 || W < matrix[S - 1][E - 1]) {
				matrix[S - 1][E - 1] = W;
				matrix[E - 1][S - 1] = W;
			}
		}
	}
}