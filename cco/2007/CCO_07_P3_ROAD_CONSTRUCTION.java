import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

// atharva washimkar
// Aug 03, 2017

public class CCO_07_P3_ROAD_CONSTRUCTION {

	// find bridges (remove bridges to split graph into cycles)
	// compress each cycle into a single node to form a tree
	// count number of leaves

	static List<Edge>[] list;
	static Set<Integer>[] list2;
	static int time, cnt;
	static int[] disc, low, cmp;
	static boolean[] bridges;

	public static void dfs (int u, int v) {
		disc[v] = low[v] = time++;

		for (Edge e : list[v]) {
			if (disc[e.v] == -1) {
				dfs (v, e.v);
				low[v] = Math.min (low[v], low[e.v]);

				if (low[e.v] == disc[e.v])
					bridges[e.i] = true;
			}
			else if (e.v != u)
				low[v] = Math.min (low[v], disc[e.v]);
		}
	}

	public static void dfs2 (int u) {
		cmp[u] = cnt;

		for (Edge e : list[u])
			if (!bridges[e.i] && cmp[e.v] == -1)
				dfs2 (e.v);
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan (), M = in.iscan ();
		list = new ArrayList[N];
		disc = new int[N];
		low = new int[N];
		bridges = new boolean[M];

		for (int n = 0; n < N; ++n)
			list[n] = new ArrayList<Edge> ();

		for (int m = 0; m < M; ++m) {
			int u = in.iscan () - 1, v = in.iscan () - 1;
			list[u].add (new Edge (u, v, m));
			list[v].add (new Edge (v, u, m));
		}

		Arrays.fill (disc, -1);
		Arrays.fill (low, -1);

		for (int n = 0; n < N; ++n)
			if (disc[n] == -1)
				dfs (n, n);

		cmp = new int[N];
		Arrays.fill (cmp, -1);

		for (int n = 0; n < N; ++n) {
			if (cmp[n] == -1) {
				dfs2 (n);
				++cnt;
			}
		}

		int[] ind = new int[cnt];

		for (int n = 0; n < N; ++n) {
			for (Edge e : list[n]) {
				if (cmp[n] != cmp[e.v]) {
					++ind[cmp[n]];
					++ind[cmp[e.v]];
				}
			}
		}

		int leaves = 0;

		for (int i = 0; i < cnt; ++i)
			if (ind[i] == 2)
				++leaves;

		out.print ((leaves + 1) / 2);
		out.close ();
	}

	private static class Edge {

		int u, v, i;

		public Edge (int u, int v, int i) {
			this.u = u; this.v = v; this.i = i;
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