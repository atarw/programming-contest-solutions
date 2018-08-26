import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// atharva washimkar
// August 11, 2018

public class RTE_16_S3_SCHOOL_TRAVERSAL {

	static int N;
	static List<Edge>[] list;
	static int[] lvl, ord, occ, tree;
	static long[] depth;
	static boolean[] vis;
	static int time = 0;

	public static void dfs (int u, int d, long d2) {
		vis[u] = true;
		lvl[u] = d;
		depth[u] = d2;
		ord[time++] = u;

		for (Edge e : list[u]) {
			if (!vis[e.v]) {
				dfs (e.v, d + 1, d2 + e.w);
				ord[time++] = u;
			}
		}
	}

	public static void build (int u, int l, int r) {
		if (l == r)
			tree[u] = l;
		else {
			int mid = (l + r) / 2;
			build (u * 2 + 1, l, mid);
			build (u * 2 + 2, mid + 1, r);
			tree[u] = lvl[ord[tree[u * 2 + 1]]] < lvl[ord[tree[u * 2 + 2]]] ?
			          tree[u * 2 + 1] : tree[u * 2 + 2];
		}
	}

	public static int query (int u, int l, int r, int ql, int qr) {
		if (l == ql && r == qr)
			return tree[u];

		int mid = (l + r) / 2;

		if (qr <= mid)
			return query (u * 2 + 1, l, mid, ql, qr);
		else if (ql >= mid + 1)
			return query (u * 2 + 2, mid + 1, r, ql, qr);

		int la = query (u * 2 + 1, l, mid, ql, mid);
		int ra = query (u * 2 + 2, mid + 1, r, mid + 1, qr);

		return lvl[ord[la]] < lvl[ord[ra]] ? la : ra;
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan ();
		list = new ArrayList[N];

		for (int n = 0; n < N; ++n)
			list[n] = new ArrayList<Edge> ();

		for (int n = 0; n < N - 1; ++n) {
			int u = in.iscan (), v = in.iscan (), w = in.iscan ();
			list[u].add (new Edge (v, w)); list[v].add (new Edge (u, w));
		}

		lvl = new int[N];
		ord = new int[2 * N - 1];
		vis = new boolean[N];
		occ = new int[N];
		tree = new int[8 * N];
		depth = new long[N];
		Arrays.fill (occ, 1 << 20);
		time = 0;

		dfs (0, 0, 0L);

		for (int n = 0; n < ord.length; ++n)
			occ[ord[n]] = Math.min (occ[ord[n]], n);

		build (0, 0, ord.length - 1);

		int Q = in.iscan ();

		for (int q = 0; q < Q; ++q) {
			int u = in.iscan (), v = in.iscan ();
			int lca = ord[query (0, 0, ord.length - 1, Math.min (occ[u], occ[v]), Math.max (occ[u], occ[v]))];

			out.println ((depth[u] - depth[lca] + (depth[v] - depth[lca])));
		}

		out.close ();
	}

	private static class Edge {

		int v, w;

		public Edge (int v, int w) {
			this.v = v; this.w = w;
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