import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CCC_07_S3_FRIENDS {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		Graph g = new Graph ();
		String[] t;

		for (int n = 0; n < N; n++) {
			t = in.readLine ().split (" ");

			g.addEdge (Integer.parseInt (t[0]), Integer.parseInt (t[1]));
		}

		while (true) {
			t = in.readLine ().split (" ");
			int A = Integer.parseInt (t[0]), B = Integer.parseInt (t[1]);

			if (A == 0 && B == 0) {
				break;
			}

			int r = g.path (A, B);

			System.out.println (r == -1 ? "No" : "Yes " + r);
		}
	}

	private static class Graph {

		Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>> ();
		int[] cache;

		public int path (int A, int B) {
			Queue<Integer> queue = new ArrayDeque<Integer> ();
			cache = new int[10000];
			int curr = A;
			queue.offer (A);

			out:
			while (!queue.isEmpty ()) {
				curr = queue.poll ();

				if (curr == B) {
					break;
				}

				if (!map.containsKey (curr)) {
					break;
				}

				for (Integer i : map.get (curr)) {
					if (cache[i - 1] == 0) {
						queue.offer (i);
						cache[i - 1] = cache[curr - 1] + 1;
					}

					if (i == B) {
						break out;
					}
				}
			}

			return cache[B - 1] - 1;
		}

		public void addEdge (int a, int b) {
			if (!map.containsKey (a)) {
				map.put (a, new HashSet<Integer> ());
			}

			map.get (a).add (b);
		}
	}
}