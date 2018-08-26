import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// atharva washimkar
// August 11, 2018

public class ICPC_05_E_RELIABLE_NETS {

	static List<Edge>[] list;
	static int[] low, pre, parent;
	static int bridges, time;
	static int mask;

	public static int find (int u) {
		if (u != parent[u])
			return parent[u] = find (parent[u]);

		return parent[u];
	}

	public static boolean union (int u, int v) {
		if (find (u) == find (v))
			return false;

		parent[find (u)] = find (v);
		return true;
	}

	public static void dfs (int u, int v) {
		pre[v] = time++;
		low[v] = pre[v];

		for (Edge e : list[v]) {
			if (((mask >> e.id) & 1) != 1)
				continue;

			if (pre[e.v] == -1) {
				dfs (v, e.v);
				low[v] = Math.min (low[v], low[e.v]);
				if (low[e.v] == pre[e.v]) {
					//System.out.println(v + "-" + w + " is a bridge");
					++bridges;
				}
			}
			else if (e.v != u)
				low[v] = Math.min (low[v], pre[e.v]);
		}
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int cnt = 1;

		while (true) {
			int N = in.iscan (), M = in.iscan ();

			if (N == 0 && M == 0)
				break;

			list = new ArrayList[N];

			for (int n = 0; n < N; ++n)
				list[n] = new ArrayList<Edge> ();

			Edge[] edges = new Edge[M];

			for (int m = 0, u, v, w; m < M; ++m) {
				u = in.iscan () - 1;
				v = in.iscan () - 1;
				w = in.iscan ();
				edges[m] = new Edge (u, v, w, m);
				list[u].add (new Edge (u, v, w, m));
				list[v].add (new Edge (v, u, w, m));
			}

			int best = 1 << 30;

			for (int m = 0; m < (1 << M); ++m) {
				mask = m;

				int cmp = N;
				parent = new int[N];

				for (int n = 0; n < N; ++n)
					parent[n] = n;

				for (int i = 0; i < M; ++i)
					if (((m >> i) & 1) == 1)
						if (union (edges[i].u, edges[i].v))
							--cmp;

				if (cmp != 1)
					continue;

				low = new int[N];
				pre = new int[N];
				time = bridges = 0;

				Arrays.fill (low, -1);
				Arrays.fill (pre, -1);

				for (int n = 0; n < N; ++n)
					if (pre[n] == -1)
						dfs (n, n);

				if (bridges == 0) {
					int cost = 0;

					for (int i = 0; i < M; ++i)
						if (((m >> i) & 1) == 1)
							cost += edges[i].w;

					best = Math.min (best, cost);
				}
			}

			if (best == 1 << 30)
				out.println ("There is no reliable net possible for test case " + cnt + ".");
			else
				out.println ("The minimal cost for test case " + cnt + " is " + best + ".");

			++cnt;
		}

		out.close ();
	}

	private static class Edge {

		int u, v, w, id;

		public Edge (int u, int v, int w, int id) {
			this.u = u;
			this.v = v;
			this.w = w;
			this.id = id;
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