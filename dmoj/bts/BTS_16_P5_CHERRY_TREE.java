import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BTS_16_P5_CHERRY_TREE {

	static int[] cherries, branches;
	static List<Edge>[] list;

	static boolean[] vis;
	static int cut = 0, C, K;

	public static void solve (int u) {
		vis[u] = true;

		for (Edge e : list[u]) {
			if (!vis[e.e]) {
				solve (e.e);
				branches[u] += e.w + branches[e.e];
				cherries[u] += cherries[e.e];

				if (cherries[e.e] >= C && branches[e.e] + e.w <= K)
					++cut;
			}
		}

		vis[u] = false;
	}

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));

		t = in.readLine ().split (" ");
		int N = Integer.parseInt (t[0]);
		C = Integer.parseInt (t[1]);
		K = Integer.parseInt (t[2]);

		cherries = new int[N];
		branches = new int[N];
		list = new ArrayList[N];

		t = in.readLine ().split (" ");

		for (int n = 0; n < N; ++n)
			cherries[n] = Integer.parseInt (t[n]);

		list[0] = new ArrayList<Edge> ();

		for (int n = 0, a, b, k; n < N - 1; ++n) {
			t = in.readLine ().split (" ");
			a = Integer.parseInt (t[0]) - 1;
			b = Integer.parseInt (t[1]) - 1;
			k = Integer.parseInt (t[2]);

			if (list[a] == null)
				list[a] = new ArrayList<Edge> ();

			if (list[b] == null)
				list[b] = new ArrayList<Edge> ();

			list[a].add (new Edge (a, b, k));
			list[b].add (new Edge (b, a, k));
		}

		vis = new boolean[N];

		solve (0);
		System.out.print (cut);
	}

	private static class Edge {

		int s, e, w;

		public Edge (int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}
}