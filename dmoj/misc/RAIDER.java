import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

// atharva washimkar
// May 27, 2017

public class RAIDER {

	static int N, M;
	static int[] v, v2;
	static List<Integer>[] list, rev, cmp;
	static int[][] dp;
	static long[][] dp2;
	static int MOD = 1000000007;

	static int[] id;
	static boolean[] vis;
	static Deque<Integer> stack;
	static int sz;

	public static void dfs (int u) {
		vis[u] = true;

		for (int v : list[u])
			if (!vis[v])
				dfs (v);

		stack.offerFirst (u);
	}

	public static void dfs2 (int u) {
		vis[u] = true;
		id[u] = sz;

		for (int v : rev[u])
			if (!vis[v])
				dfs2 (v);
	}

	public static void solve (int u, int b) {
		if (dp[u][b] != -1)
			return;

		dp[u][b] = 0;
		dp2[u][b] = 0;

		if (cmp[u].isEmpty () || u == id[N - 1]) {
			if (b == 1)
				dp[u][b] = v2[u];

			if (u == id[N - 1])
				dp2[u][b] = 1;
		}
		else {
			if (b == 1) { // can borrow from this one
				for (int v : cmp[u]) {
					solve (v, 0);
					solve (v, 1);
					int op1v = v2[u] + dp[v][0], op2v = dp[v][1];
					long op1w = dp2[v][0], op2w = dp2[v][1];

					if (op1v > dp[u][b]) {
						dp[u][b] = op1v;
						dp2[u][b] = op1w;
					}
					else if (op1v == dp[u][b]) {
						dp2[u][b] += op1w;
						dp2[u][b] %= MOD;
					}

					if (op2v > dp[u][b]) {
						dp[u][b] = op2v;
						dp2[u][b] = op2w;
					}
					else if (op2v == dp[u][b]) {
						dp2[u][b] += op2w;
						dp2[u][b] %= MOD;
					}
				}
			}
			else {
				for (int v : cmp[u]) {
					solve (v, 1);
					int opv = dp[v][1];
					long opw = dp2[v][1];

					if (opv > dp[u][b]) {
						dp[u][b] = opv;
						dp2[u][b] = opw;
					}
					else if (opv == dp[u][b]) {
						dp2[u][b] += opw;
						dp2[u][b] %= MOD;
					}
				}
			}
		}
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		N = in.iscan ();
		M = in.iscan ();

		v = new int[N];
		list = new ArrayList[N];
		rev = new ArrayList[N];

		for (int n = 0; n < N; ++n) {
			list[n] = new ArrayList<Integer> ();
			rev[n] = new ArrayList<Integer> ();
		}

		for (int n = 0; n < N; ++n)
			v[n] = in.iscan ();

		for (int m = 0, a, b; m < M; ++m) {
			a = in.iscan () - 1;
			b = in.iscan () - 1;
			list[a].add (b);
			rev[b].add (a);
		}

		// do scc
		id = new int[N];
		vis = new boolean[N];
		stack = new ArrayDeque<Integer> ();

		for (int n = 0; n < N; ++n)
			if (!vis[n])
				dfs (n);

		vis = new boolean[N];

		while (!stack.isEmpty ()) {
			int u = stack.poll ();

			if (!vis[u]) {
				dfs2 (u);
				++sz;
			}
		}

		// compress graph
		cmp = new ArrayList[sz];
		v2 = new int[sz];

		for (int n = 0; n < cmp.length; ++n)
			cmp[n] = new ArrayList<Integer> ();

		for (int n = 0; n < N; ++n)
			for (int v : list[n])
				if (id[n] != id[v])
					cmp[id[n]].add (id[v]);

		for (int n = 0; n < N; ++n)
			v2[id[n]] += v[n];

		// memory optimization (impossible to pass otherwise)
		list = null;
		rev = null;
		stack = null;
		vis = null;
		v = null;
		System.gc ();

		// do dp
		dp = new int[sz + 1][2];
		dp2 = new long[sz + 1][2];

		for (int[] arr : dp)
			Arrays.fill (arr, -1);

		for (long[] arr : dp2)
			Arrays.fill (arr, -1L);

		solve (id[0], 1);

		out.print (dp[id[0]][1] + " " + dp2[id[0]][1]);
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
			if (this.curChar >= this.numChars) {
				this.curChar = 0;
				this.numChars = this.stream.read (this.buf);
			}

			return this.buf[this.curChar++];
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