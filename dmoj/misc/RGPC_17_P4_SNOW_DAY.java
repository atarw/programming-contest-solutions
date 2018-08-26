import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

// atharva washimkar
// August 11, 2018

public class RGPC_17_P4_SNOW_DAY {

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan (), M = in.iscan ();
		List<Edge>[] list = new ArrayList[N];
		int[] indegree = new int[N];

		for (int n = 0; n < N; ++n)
			list[n] = new ArrayList<Edge> ();

		for (int m = 0, a, b, d; m < M; ++m) {
			a = in.iscan () - 1;
			b = in.iscan () - 1;
			d = in.iscan ();
			list[a].add (new Edge (b, d));
			++indegree[b];
		}

		// perform PARTIAL topological sort starting from node 0
		Queue<Integer> q = new ArrayDeque<Integer> ();
		Deque<Integer> ord = new ArrayDeque<Integer> ();

		int u;
		q.offer (0);

		while (!q.isEmpty ()) {
			u = q.poll ();
			ord.offerLast (u);

			for (Edge e : list[u])
				if (--indegree[e.v] == 0)
					q.offer (e.v);
		}

		if (ord.size () != N) { // topological sort not completed; cycle exists
			out.print (-1);
		}
		else {
			State[] dp = new State[N];
			dp[0] = new State (1, 0L);

			for (int n = 1; n < N; ++n)
				dp[n] = new State (-1, -1L);

			while (!ord.isEmpty ()) {
				u = ord.poll ();

				for (Edge e : list[u])
					if (dp[e.v].dist < dp[u].dist + (long) e.w || dp[e.v].dist == dp[u].dist + (long) e.w && dp[e.v]
							.nodes < dp[u].nodes + 1)
						dp[e.v] = new State (dp[u].nodes + 1, dp[u].dist + (long) e.w);
			}

			if (dp[N - 1].nodes == -1) // not reachable from start
				out.print (-1);
			else
				out.print (dp[N - 1].dist + " " + dp[N - 1].nodes);
		}

		out.close ();
	}

	private static class State {

		int nodes;
		long dist;

		public State (int nodes, long dist) {
			this.nodes = nodes;
			this.dist = dist;
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