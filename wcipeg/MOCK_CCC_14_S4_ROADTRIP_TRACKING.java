import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*
to check if two different paths exist, find first path, and set all edge weights of path to 1, with other edges as 0.
find second path, and if distance travelled is less, there is a new path
*/

// atharva washimkar
// August 11, 2018

public class MOCK_CCC_14_S4_ROADTRIP_TRACKING {

	static List<Integer>[] list;
	static Edge[] edges;

	public static int djikstra (boolean first) {
		Queue<Integer> q = new ArrayDeque<Integer> ();
		State[] dp = new State[list.length];

		dp[0] = new State (0, 0, -1);

		for (int n = 1; n < dp.length; ++n)
			dp[n] = new State (n, 1 << 20, -1);

		int u = 0;
		q.offer (u);

		while (!q.isEmpty ()) {
			u = q.poll ();

			for (int e : list[u]) {
				if (dp[edges[e].v].dist > dp[u].dist + edges[e].w) {
					dp[edges[e].v] = new State (edges[e].v, dp[u].dist + edges[e].w, e);
					q.offer (edges[e].v);
				}
			}
		}

		u = list.length - 1;
		int length = 0;

		while (dp[u].prev != -1) {
			edges[dp[u].prev].w = edges[dp[u].prev < edges.length / 2 ? dp[u].prev + edges.length / 2 : dp[u].prev].w
					= 1;
			u = edges[dp[u].prev].u;
			++length;
		}

		return first ? length : dp[list.length - 1].dist;
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan (), M = in.iscan ();
		list = new ArrayList[N];
		edges = new Edge[2 * M];

		for (int n = 0; n < N; ++n)
			list[n] = new ArrayList<Integer> ();

		for (int m = 0, a, b; m < M; ++m) {
			a = in.iscan () - 1;
			b = in.iscan () - 1;
			edges[m] = new Edge (a, b, 0);
			edges[m + M] = new Edge (b, a, 0);
			list[a].add (m);
			list[b].add (m + M);
		}

		int first = djikstra (true);
		int second = djikstra (false);

		out.print (first != second ? "Yes" : "No");
		out.close ();
	}

	private static class State {

		int u, dist, prev;

		public State (int u, int dist, int prev) {
			this.u = u;
			this.dist = dist;
			this.prev = prev;
		}
	}

	private static class Edge {

		int u, v, w;

		public Edge (int u, int v, int w) {
			this.u = u;
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