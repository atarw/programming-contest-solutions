import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// atharva washimkar
// May 01, 2017

public class SPOJ_LCA {

	static int N;
	static List<Integer>[] list;
	static int[] lvl, ord, occ, tree;
	static boolean[] vis;
	static int time = 0;

	public static void dfs (int u, int d) {
		vis[u] = true;
		lvl[u] = d;
		ord[time++] = u;

		for (int v : list[u]) {
			if (!vis[v]) {
				dfs (v, d + 1);
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

		int T = in.iscan ();

		for (int tt = 1; tt <= T; ++tt) {
			N = in.iscan ();

			list = new ArrayList[N];

			for (int n = 0; n < N; ++n)
				list[n] = new ArrayList<Integer> ();

			for (int n = 0; n < N; ++n) {
				int M = in.iscan ();

				for (int m = 0, v; m < M; ++m) {
					v = in.iscan () - 1;
					list[n].add (v);
					list[v].add (n);
				}
			}

			lvl = new int[N];
			ord = new int[2 * N - 1];
			vis = new boolean[N];
			occ = new int[N];
			tree = new int[8 * N];
			Arrays.fill (occ, 1 << 20);
			time = 0;

			dfs (0, 0);

			for (int n = 0; n < ord.length; ++n)
				occ[ord[n]] = Math.min (occ[ord[n]], n);

			build (0, 0, ord.length - 1);

			int Q = in.iscan ();
			out.println ("Case " + tt + ": ");

			for (int q = 0, u, v; q < Q; ++q) {
				u = in.iscan () - 1;
				v = in.iscan () - 1;
				out.println (ord[query (0, 0, ord.length - 1, Math.min (occ[u], occ[v]), Math.max (occ[u], occ[v]))] + 1);
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