import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// atharva washimkar
// August 11, 2018

public class ICPC_16_D_LOST_IN_TRANSLATION {

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan (), M = in.iscan ();
		Map<String, Integer> map = new HashMap<String, Integer> ();
		map.put ("English", 0);

		for (int n = 0; n < N; ++n)
			map.put (in.sscan (), map.size ());

		List<Edge>[] list = new ArrayList[N + 1];

		for (int n = 0; n <= N; ++n)
			list[n] = new ArrayList<Edge> ();

		for (int m = 0; m < M; ++m) {
			String u = in.sscan (), v = in.sscan ();
			int c = in.iscan ();

			list[map.get (u)].add (new Edge (map.get (v), c));
			list[map.get (v)].add (new Edge (map.get (u), c));
		}

		State[] dp = new State[N + 1];

		for (int n = 1; n <= N; ++n)
			dp[n] = new State (1 << 20, 1 << 20);

		dp[0] = new State (0, 0);

		int total = 0;
		boolean good = true;

		Queue<Integer> q = new ArrayDeque<Integer> ();
		q.offer (0);

		while (!q.isEmpty ()) {
			int u = q.poll ();

			for (Edge e : list[u]) {
				if (dp[e.v].d > dp[u].d + 1 || dp[e.v].d == dp[u].d + 1 && dp[e.v].c > dp[u].c + e.w) {
					dp[e.v] = new State (dp[u].c + e.w, dp[u].d + 1);
					q.offer (e.v);
				}
			}
		}

		for (int i = 1; i <= N; ++i) {
			if (dp[i].d == 1 << 20) {
				good = false;
				break;
			}

			int min = 1 << 20;

			for (Edge e : list[i])
				if (dp[i].d == dp[e.v].d + 1)
					min = Math.min (min, e.w);

			total += min;
		}

		if (good)
			out.print (total);
		else
			out.print ("Impossible");

		out.close ();
	}

	private static class State {

		int c, d;

		public State (int c, int d) {
			this.c = c;
			this.d = d;
		}
	}

	private static class Edge {

		int v, w;

		public Edge (int v, int w) {
			this.v = v;
			this.w = w;
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