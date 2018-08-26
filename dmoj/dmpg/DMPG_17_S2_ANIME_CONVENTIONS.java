import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

// atharva washimkar
// August 11, 2018

public class DMPG_17_S2_ANIME_CONVENTIONS {

	static int[] parent;

	public static int find (int u) {
		if (parent[u] != u)
			return parent[u] = find (parent[u]);
		return parent[u];
	}

	public static void union (int u, int v) {
		int r1 = find (u), r2 = find (v);
		parent[r1] = r2;
	}

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		t = in.readLine ().split (" ");
		int N = Integer.parseInt (t[0]), Q = Integer.parseInt (t[1]);
		parent = new int[N];

		for (int n = 0; n < N; ++n)
			parent[n] = n;

		for (int q = 0; q < Q; ++q) {
			t = in.readLine ().split (" ");
			int x = Integer.parseInt (t[1]) - 1, y = Integer.parseInt (t[2]) - 1;

			if (t[0].equals ("A")) {
				union (x, y);
			}
			else {
				out.println (find (x) == find (y) ? "Y" : "N");
			}
		}

		out.close ();
	}
}