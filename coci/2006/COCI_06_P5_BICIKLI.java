import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// atharva washimkar
// August 11, 2018

public class COCI_06_P5_BICIKLI {

	static List<Integer>[] list;
	static boolean[] stack, vis, used;
	static long[] dp;
	static boolean cycle = false;
	static long MOD = 1000000000;
	static boolean exceed = false;

	public static boolean check (int u) {
		if (u == 1 || used[u])
			return used[u] = true;

		vis[u] = true;

		for (int v : list[u])
			if (!vis[v])
				used[u] |= check (v);

		return used[u];
	}

	public static void cycle (int u) {
		if (cycle) return;
		stack[u] = vis[u] = true;

		for (int v : list[u]) {
			if (used[v])
				if (!vis[v])
					cycle (v);
				else if (stack[v])
					cycle = true;

			if (cycle) break;
		}

		stack[u] = false;
	}

	public static long dfs (int u) {
		if (dp[u] != -1L)
			return dp[u];

		if (u == 1)
			return dp[u] = 1L;

		dp[u] = 0L;

		for (int v : list[u]) {
			dp[u] += dfs (v);

			if (dp[u] >= MOD)
				exceed = true;

			dp[u] %= MOD;
		}

		return dp[u];
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan (), M = in.iscan ();
		list = new ArrayList[N];
		vis = new boolean[N];
		stack = new boolean[N];
		used = new boolean[N];

		for (int n = 0; n < N; ++n)
			list[n] = new ArrayList<Integer> ();

		for (int m = 0; m < M; ++m)
			list[in.iscan () - 1].add (in.iscan () - 1);

		for (int n = 0; n < N; ++n)
			if (!vis[n])
				check (n);

		Arrays.fill (vis, false);
		cycle (0);

		if (cycle)
			out.print ("inf");
		else {
			dp = new long[N];
			Arrays.fill (dp, -1L);
			long ans = dfs (0) % MOD;

			if (exceed) {
				int digits = (int) Math.abs (Math.ceil (Math.log10 (ans)));
				for (int i = digits; i < 9; ++i)
					out.print (0);

				out.print (ans);
			}
			else {
				out.print (ans);
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