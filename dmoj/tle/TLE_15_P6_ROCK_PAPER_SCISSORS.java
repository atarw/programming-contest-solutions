import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

// atharva washimkar
// August 11, 2018

public class TLE_15_P6_ROCK_PAPER_SCISSORS {

	static int N, M, Q;
	static List<Integer>[] list, rev, cmp;
	static boolean[] vis;
	static Deque<Integer> stack;
	static int cnt = 0;
	static int[] id, sz;
	static int[][] dist;

	public static void dfs (int u) {
		vis[u] = true;

		for (int v : list[u])
			if (!vis[v])
				dfs (v);

		stack.offerFirst (u);
	}

	public static void dfs2 (int u) {
		vis[u] = true;
		id[u] = cnt;

		for (int v : rev[u])
			if (!vis[v])
				dfs2 (v);
	}

	public static int dfs3 (int u, int v) {
		if (dist[u][v] != -(1 << 25))
			return dist[u][v];

		if (u == v)
			return dist[u][v] = 0;

		vis[u] = true;

		for (int vv : cmp[u])
			if (!vis[vv])
				dist[u][v] = Math.max (dist[u][v], sz[u] + dfs3 (vv, v));

		vis[u] = false;
		return dist[u][v];
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		N = in.iscan ();
		M = in.iscan ();
		Q = in.iscan ();

		list = new ArrayList[N];
		rev = new ArrayList[N];

		for (int n = 0; n < N; ++n) {
			list[n] = new ArrayList<Integer> ();
			rev[n] = new ArrayList<Integer> ();
		}

		for (int m = 0, a, b; m < M; ++m) {
			a = in.iscan () - 1;
			b = in.iscan () - 1;
			list[a].add (b);
			rev[b].add (a);
		}

		// do scc and compress graph
		vis = new boolean[N];
		id = new int[N];
		stack = new ArrayDeque<Integer> (N);

		for (int n = 0; n < N; ++n)
			if (!vis[n])
				dfs (n);

		vis = new boolean[N];

		while (!stack.isEmpty ()) {
			int u = stack.pollFirst ();

			if (!vis[u]) {
				dfs2 (u);
				++cnt;
			}
		}

		sz = new int[cnt];
		cmp = new ArrayList[cnt];

		for (int n = 0; n < cnt; ++n)
			cmp[n] = new ArrayList<Integer> ();

		for (int n = 0; n < N; ++n)
			for (int v : list[n])
				if (id[n] != id[v])
					cmp[id[n]].add (id[v]);

		for (int n = 0; n < N; ++n)
			++sz[id[n]];

		// find max distance between every pair of nodes in cmp
		dist = new int[cnt][cnt];

		for (int[] arr : dist)
			Arrays.fill (arr, -(1 << 25));

		vis = new boolean[cnt];

		for (int u = 0; u < cnt; ++u)
			for (int v = 0; v < cnt; ++v)
				dfs3 (u, v);

		// answer queries
		for (int q = 0, a, b; q < Q; ++q) {
			a = in.iscan () - 1;
			b = in.iscan () - 1;

			if (id[a] == id[b] || dist[id[a]][id[b]] <= 0 && dist[id[b]][id[a]] <= 0) {
				out.println ("Indeterminate");
			}
			else {
				if (dist[id[a]][id[b]] <= 0 && dist[id[b]][id[a]] > 0) {
					int tmp = b;
					b = a;
					a = tmp;
				}

				out.println ((a + 1) + " " + (dist[id[a]][id[b]] - sz[id[a]]));
			}
		}

		out.close ();
	}

	private static class INPUT {

		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar, numChars;

		public INPUT (InputStream stream) {
			this.stream = stream;
		}

		public INPUT (String file) throws IOException {
			this.stream = new FileInputStream (file);
		}

		public int cscan () throws IOException {
			if (curChar >= numChars) {
				curChar = 0;
				numChars = stream.read (buf);
			}

			return buf[curChar++];
		}

		public int iscan () throws IOException {
			int c = cscan (), sgn = 1;
			while (space (c)) c = cscan ();

			if (c == '-') {
				sgn = -1;
				c = cscan ();
			}

			int res = 0;

			do {
				res = (res << 1) + (res << 3);
				res += c - '0';
				c = cscan ();
			}
			while (!space (c));

			return res * sgn;
		}

		public String sscan () throws IOException {
			int c = cscan ();
			while (space (c)) c = cscan ();

			StringBuilder res = new StringBuilder ();

			do {
				res.appendCodePoint (c);
				c = cscan ();
			}
			while (!space (c));

			return res.toString ();
		}

		public double dscan () throws IOException {
			int c = cscan (), sgn = 1;
			while (space (c)) c = cscan ();

			if (c == '-') {
				sgn = -1;
				c = cscan ();
			}

			double res = 0;

			while (!space (c) && c != '.') {
				if (c == 'e' || c == 'E') return res * UTILITIES.fast_pow (10, iscan ());
				res *= 10;
				res += c - '0';
				c = cscan ();
			}

			if (c == '.') {
				c = cscan ();
				double m = 1;

				while (!space (c)) {
					if (c == 'e' || c == 'E') return res * UTILITIES.fast_pow (10, iscan ());

					m /= 10;
					res += (c - '0') * m;
					c = cscan ();
				}
			}

			return res * sgn;
		}

		public long lscan () throws IOException {
			int c = cscan (), sgn = 1;
			while (space (c)) c = cscan ();

			if (c == '-') {
				sgn = -1;
				c = cscan ();
			}

			long res = 0;

			do {
				res = (res << 1) + (res << 3);
				res += c - '0';
				c = cscan ();

			}
			while (!space (c));

			return res * sgn;
		}

		public boolean space (int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
	}

	public static class UTILITIES {

		static final double EPS = 10e-6;

		public static int lower_bound (int[] arr, int x) {
			int low = 0, high = arr.length, mid = -1;

			while (low < high) {
				mid = (low + high) / 2;

				if (arr[mid] >= x)
					high = mid;
				else
					low = mid + 1;
			}

			return low;
		}

		public static int upper_bound (int[] arr, int x) {
			int low = 0, high = arr.length, mid = -1;

			while (low < high) {
				mid = (low + high) / 2;

				if (arr[mid] > x)
					high = mid;
				else
					low = mid + 1;
			}

			return low;
		}

		public static int gcd (int a, int b) {
			return b == 0 ? a : gcd (b, a % b);
		}

		public static int lcm (int a, int b) {
			return a * b / gcd (a, b);
		}

		public static int fast_pow_mod (int b, int x, int mod) {
			if (x == 0) return 1;
			if (x == 1) return b;
			if (x % 2 == 0) return fast_pow_mod (b * b % mod, x / 2, mod) % mod;

			return b * fast_pow_mod (b * b % mod, x / 2, mod) % mod;
		}

		public static int fast_pow (int b, int x) {
			if (x == 0) return 1;
			if (x == 1) return b;
			if (x % 2 == 0) return fast_pow (b * b, x / 2);

			return b * fast_pow (b * b, x / 2);
		}

		public static long choose (long n, long k) {
			k = Math.min (k, n - k);
			long val = 1;

			for (int i = 0; i < k; ++i)
				val = val * (n - i) / (i + 1);

			return val;
		}

		public static long permute (int n, int k) {
			if (n < k) return 0;
			long val = 1;

			for (int i = 0; i < k; ++i)
				val = (val * (n - i));

			return val;
		}
	}
}