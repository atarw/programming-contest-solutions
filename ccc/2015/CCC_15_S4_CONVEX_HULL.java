import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

// atharva washimkar
// August 11, 2018

public class CCC_15_S4_CONVEX_HULL {

	public static void main (String[] tokens) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		tokens = in.readLine ().split (" ");

		int K = Integer.parseInt (tokens[0]), N = Integer.parseInt (tokens[1]), M = Integer.parseInt (tokens[2]);
		List<Edge>[] list = new ArrayList[N];

		int a, b, t, h;

		for (int m = 0; m < M; m++) {
			tokens = in.readLine ().split (" ");
			a = Integer.parseInt (tokens[0]) - 1;
			b = Integer.parseInt (tokens[1]) - 1;
			t = Integer.parseInt (tokens[2]);
			h = Integer.parseInt (tokens[3]);

			if (list[a] == null) {
				list[a] = new ArrayList<Edge> ();
			}

			if (list[b] == null) {
				list[b] = new ArrayList<Edge> ();
			}

			list[a].add (new Edge (a, b, t, h));
			list[b].add (new Edge (b, a, t, h));
		}

		tokens = in.readLine ().split (" ");
		int A = Integer.parseInt (tokens[0]) - 1, B = Integer.parseInt (tokens[1]) - 1;

		Queue<State> queue = new ArrayDeque<State> ();
		int[][] cache = new int[N][K + 1];

		for (int i = 0; i < cache.length; i++) {
			Arrays.fill (cache[i], Integer.MAX_VALUE);
		}

		State curr = new State (A, K);
		Arrays.fill (cache[A], 0);

		int min = Integer.MAX_VALUE;

		queue.offer (curr);

		while (!queue.isEmpty ()) {
			curr = queue.poll ();

			if (curr.C == B) {
				min = Math.min (min, cache[curr.C][curr.H]);
			}
			else if (list[curr.C] != null) {
				for (int e = 0; e < list[curr.C].size (); e++) {
					if (curr.H > list[curr.C].get (e).H && cache[list[curr.C].get (e).B][curr.H - list[curr.C].get (e)
							.H] > cache[curr.C][curr.H] + list[curr.C].get (e).T) {
						cache[list[curr.C].get (e).B][curr.H - list[curr.C].get (e).H] = cache[curr.C][curr.H] +
								list[curr.C].get (e).T;
						queue.offer (new State (list[curr.C].get (e).B, curr.H - list[curr.C].get (e).H));
					}
				}
			}
		}

		System.out.print (min == Integer.MAX_VALUE ? -1 : min);
	}

	private static class State {

		int C, H;

		public State (int C, int H) {
			this.C = C;
			this.H = H;
		}
	}

	private static class Edge {

		int A, B, T, H;

		public Edge (int A, int B, int T, int H) {
			this.A = A;
			this.B = B;
			this.T = T;
			this.H = H;
		}
	}
}