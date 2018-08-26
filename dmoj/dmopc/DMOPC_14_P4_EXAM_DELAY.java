import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// atharva washimkar
// August 11, 2018

public class DMOPC_14_P4_EXAM_DELAY {

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int V = in.iscan (), E = in.iscan ();
		List<Edge>[] list = new ArrayList[V];

		for (int v = 0; v < V; ++v)
			list[v] = new ArrayList<Edge> ();

		for (int e = 0, u, v, d, s; e < E; ++e) {
			u = in.iscan () - 1;
			v = in.iscan () - 1;
			d = in.iscan ();
			s = in.iscan ();
			list[u].add (new Edge (u, v, d, s));
			list[v].add (new Edge (v, u, d, s));
		}

		State[] dp = new State[V];

		dp[0] = new State (0, 1, null);

		for (int v = 1; v < V; ++v)
			dp[v] = new State (1 << 20, 0, null);

		Queue<Integer> q = new ArrayDeque<Integer> ();
		int curr = 0;
		q.offer (curr);

		while (!q.isEmpty ()) {
			curr = q.poll ();

			for (Edge e : list[curr]) {
				if (dp[e.v].time > dp[curr].time + (60.0 * e.d) / e.s || dp[e.v].time == dp[curr].time + (60.0 * e.d)
						/ e.s && dp[e.v].inter > dp[curr].inter + 1) {
					dp[e.v] = new State (dp[curr].time + (60.0 * e.d) / e.s, dp[curr].inter + 1, e);
					q.offer (e.v);
				}
			}
		}

		int edges = dp[V - 1].inter - 1;
		double time = 0.0;

		curr = V - 1;

		while (dp[curr].prev != null) {
			time += (60.0 * dp[curr].prev.d) / (dp[curr].prev.s * 0.75);
			curr = dp[curr].prev.u;
		}

		out.println (edges);
		out.println (Math.round (time - dp[V - 1].time));
		out.close ();
	}

	private static class State {

		double time;
		int inter;
		Edge prev;

		public State (double time, int inter, Edge prev) {
			this.time = time;
			this.inter = inter;
			this.prev = prev;
		}
	}

	private static class Edge {

		int u, v, d, s;

		public Edge (int u, int v, int d, int s) {
			this.u = u;
			this.v = v;
			this.d = d;
			this.s = s;
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