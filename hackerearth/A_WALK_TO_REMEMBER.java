import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// atharva washimkar
// August 11, 2018

public class A_WALK_TO_REMEMBER implements Runnable {

	static List<Integer>[] list, rev;
	static boolean[] vis;
	static int[] id;
	static Deque<Integer> q = new ArrayDeque<Integer> ();
	static int cnt;

	public static void main (String[] t) {
		new Thread (null, new A_WALK_TO_REMEMBER (), "fuckjustin", 1 << 25).start ();
	}

	public void dfs (int u) {
		vis[u] = true;

		for (int v : list[u])
			if (!vis[v])
				dfs (v);

		q.offerFirst (u);
	}

	public void dfs2 (int u) {
		vis[u] = true;
		id[u] = cnt;

		for (int v : rev[u])
			if (!vis[v])
				dfs2 (v);
	}

	public void run () {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
			PrintWriter out = new PrintWriter (System.out);

			String[] t = in.readLine ().split (" ");
			int N = Integer.parseInt (t[0]), M = Integer.parseInt (t[1]);

			list = new ArrayList[N];
			rev = new ArrayList[N];

			for (int n = 0; n < N; ++n) {
				list[n] = new ArrayList<Integer> ();
				rev[n] = new ArrayList<Integer> ();
			}

			for (int m = 0; m < M; ++m) {
				t = in.readLine ().split (" ");
				int a = Integer.parseInt (t[0]) - 1, b = Integer.parseInt (t[1]) - 1;
				list[a].add (b);
				rev[b].add (a);
			}

			id = new int[N];
			vis = new boolean[N];

			for (int n = 0; n < N; ++n)
				if (!vis[n])
					dfs (n);

			vis = new boolean[N];

			while (!q.isEmpty ()) {
				int u = q.poll ();

				if (!vis[u]) {
					dfs2 (u);
					++cnt;
				}
			}

			int[] sz = new int[N];

			for (int n = 0; n < N; ++n)
				++sz[id[n]];

			for (int n = 0; n < N; ++n)
				out.print ((sz[id[n]] == 1 ? 0 : 1) + " ");

			out.close ();
		}
		catch (IOException e) {}
	}
}