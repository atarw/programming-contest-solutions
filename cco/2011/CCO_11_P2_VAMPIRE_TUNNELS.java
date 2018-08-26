import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class CCO_11_P2_VAMPIRE_TUNNELS {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));

		int S = Integer.parseInt (in.readLine ());

		t = in.readLine ().split (" ");
		int N = Integer.parseInt (t[0]), E = Integer.parseInt (t[1]);

		List<Edge>[] list = new ArrayList[N];

		for (int e = 0, start, end, dist, under; e < E; ++e) {
			t = in.readLine ().split (" ");
			start = Integer.parseInt (t[0]);
			end = Integer.parseInt (t[1]);
			dist = Integer.parseInt (t[2]);
			under = Integer.parseInt (t[3]);

			if (list[start] == null)
				list[start] = new ArrayList<Edge> ();

			if (list[end] == null)
				list[end] = new ArrayList<Edge> ();

			list[start].add (new Edge (start, end, dist, under));
			list[end].add (new Edge (end, start, dist, under));
		}

		int[][] cache = new int[N][S + 1];

		for (int n = 0; n < N; ++n) {
			Arrays.fill (cache[n], Integer.MAX_VALUE);
		}

		int min = Integer.MAX_VALUE;

		State curr = new State (0, 0);
		cache[0][0] = 0;
		Queue<State> q = new ArrayDeque<State> ();
		q.offer (curr);

		while (!q.isEmpty ()) {
			curr = q.poll ();

			if (curr.curr == N - 1)
				min = Math.min (min, cache[curr.curr][curr.sun]);
			else if (list[curr.curr] != null) {
				for (Edge e : list[curr.curr]) {
					if (curr.sun + e.D * e.U <= S && cache[curr.curr][curr.sun] + e.D < cache[e.E][curr.sun + e.D * e
							.U]) {
						cache[e.E][curr.sun + e.D * e.U] = cache[curr.curr][curr.sun] + e.D;

						if (cache[e.E][curr.sun + e.D * e.U] < min) {
							q.offer (new State (e.E, curr.sun + e.D * e.U));
						}
					}
				}
			}
		}

		System.out.print (min == Integer.MAX_VALUE ? -1 : min);
	}

	private static class Edge {

		int S, E, D, U;

		public Edge (int S, int E, int D, int U) {
			this.S = S;
			this.E = E;
			this.D = D;
			this.U = U;
		}
	}

	private static class State {

		int curr, sun;

		public State (int curr, int sun) {
			this.curr = curr;
			this.sun = sun;
		}
	}
}