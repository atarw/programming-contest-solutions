import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class VMSS_15_P4_FRANK_AND_ROADS {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t = in.readLine ().split (" ");

		int T = Integer.parseInt (t[0]), N = Integer.parseInt (t[1]), M = Integer.parseInt (t[2]), G = Integer
				.parseInt (t[3]);
		Graph g = new Graph (N);

		for (int a = 0; a < G; a++) {
			g.addBasic (Integer.parseInt (in.readLine ()));
		}

		for (int m = 0; m < M; m++) {
			t = in.readLine ().split (" ");
			g.addEdge (Integer.parseInt (t[0]), Integer.parseInt (t[1]), Integer.parseInt (t[2]));
		}

		System.out.println (g.reachable (0, T));
	}

	private static class Graph {

		static int[] cache;
		static int[][] matrix;
		Set<Integer> set = new HashSet<Integer> ();

		public Graph (int N) {
			matrix = new int[N + 1][N + 1];
			cache = new int[N + 1];
		}

		public int reachable (int S, int T) {
			int count = 0, curr;
			Queue<Integer> queue = new ArrayDeque<Integer> ();
			queue.offer (S);

			while (!queue.isEmpty ()) {
				curr = queue.poll ();

				if (set.remove (curr)) {
					count++;
				}

				for (int i = 0; i < matrix[0].length; i++) {
					if (matrix[curr][i] != 0 && (cache[i] == 0 || cache[i] > cache[curr] + matrix[curr][i])) {
						cache[i] = cache[curr] + matrix[curr][i];

						if (cache[i] < T) {
							queue.offer (i);
						}
					}
				}
			}

			return count;
		}

		public void addBasic (int B) {
			set.add (B);
		}

		public void addEdge (int S, int E, int W) {
			if (matrix[S][E] == 0 || matrix[S][E] > W) {
				matrix[S][E] = W;
			}
		}
	}
}