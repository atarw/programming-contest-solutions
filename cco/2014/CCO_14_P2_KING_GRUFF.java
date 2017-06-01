import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class CCO_14_P2_KING_GRUFF {

	public static long[] sp (int u, List<Edge>[] list) {
		long[] dp = new long[list.length];
		Arrays.fill (dp, 1L << 50);
		dp[u] = 0;

		Queue<Integer> q = new ArrayDeque<Integer> ();
		q.offer (u);

		while (!q.isEmpty ()) {
			u = q.poll ();

			for (Edge e : list[u]) {
				if (dp[e.v] > dp[e.u] + e.w) {
					dp[e.v] = dp[e.u] + e.w;
					q.offer (e.v);
				}
			}
		}

		return dp;
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan (), M = in.iscan (), A = in.iscan () - 1, B = in.iscan () - 1;

		List<Edge>[] list = new ArrayList[N], rev = new ArrayList[N];
		Edge[] edges = new Edge[M];

		for (int n = 0; n < N; ++n) {
			list[n] = new ArrayList<Edge> ();
			rev[n] = new ArrayList<Edge> ();
		}

		for (int m = 0; m < M; ++m) {
			edges[m] = new Edge (in.iscan () - 1, in.iscan () - 1, in.iscan (), in.iscan ());
			list[edges[m].u].add (edges[m]);
			rev[edges[m].v].add (new Edge (edges[m].v, edges[m].u, edges[m].w, edges[m].c));
		}

		long[] dp = sp (A, list), dp2 = sp (B, rev);
		Pair[] min_dist_edge = new Pair[M];

		//out.println (Arrays.toString (dp));
		//out.println (Arrays.toString (dp2));

		for (int m = 0; m < M; ++m)
			min_dist_edge[m] = new Pair (dp[edges[m].u] + edges[m].w + dp2[edges[m].v], edges[m].c);

		//for (int m = 0; m < M; ++m)
		//	out.println (min_dist_edge [m].dist + " " + min_dist_edge [m].cost);

		Arrays.sort (min_dist_edge);
		long[] cost = new long[M + 1];

		for (int m = 0; m < M; ++m)
			cost[m + 1] = cost[m] + min_dist_edge[m].cost;

		//out.println (Arrays.toString (cost));

		int Q = in.iscan ();

		for (int q = 0; q < Q; ++q)
			out.println (cost[UTILITIES.upper_bound (min_dist_edge, in.iscan ())]);

		out.close ();
	}

	private static class Pair implements Comparable<Pair> {

		long dist, cost;

		public Pair (long dist, long cost) {
			this.dist = dist;
			this.cost = cost;
		}

		public int compareTo (Pair p) {
			return Long.compare (this.dist, p.dist);
		}
	}

	private static class Edge {

		int u, v, w, c;

		public Edge (int u, int v, int w, int c) {
			this.u = u;
			this.v = v;
			this.w = w;
			this.c = c;
		}
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

		public static int upper_bound (Pair[] arr, long x) {
			int low = 0, high = arr.length, mid = -1;

			while (low < high) {
				mid = (low + high) / 2;

				if (arr[mid].dist > x)
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