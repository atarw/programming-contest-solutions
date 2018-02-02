import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.TreeSet;

// atharva washimkar
// Jan 31, 2018

// if this is the intended sol wtf
public class MOCK_CCC_18_S4_A_GRAPH_PROBLEM {

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan (), M = in.iscan (), K = in.iscan ();
		int S = in.iscan () - 1, T = in.iscan () - 1;

		List<Edge>[] list = new ArrayList[N];
		NavigableSet<Long> set = new TreeSet<Long> ();

		for (int n = 0; n < N; ++n)
			list[n] = new ArrayList<Edge> ();

		for (int m = 0; m < M; ++m) {
			int u = in.iscan () - 1, v = in.iscan () - 1;
			long c = in.lscan (), d = in.lscan ();
			list[u].add (new Edge (u, v, c, d));
			set.add (c); set.add (d);
		}

		List<State> ranges = new ArrayList<State> ();

		while (true) {
			State[] dp = new State[N];
			dp[S] = new State (1L, K, null);

			Queue<Integer> q = new ArrayDeque<Integer> ();
			q.offer (S);

			while (!q.isEmpty ()) {
				int u = q.poll ();

				for (Edge e : list[u]) {
					if (e.c == 0L)
						continue;

					if (dp[u].r < e.c || dp[u].l > e.d)
						continue;

					long newl = Math.max (dp[u].l, e.c);
					long newr = Math.min (dp[u].r, e.d);

					if (dp[e.v] == null || newl < dp[e.v].l || newl == dp[e.v].l && newr > dp[e.v].r) {
						dp[e.v] = new State (newl, newr, e);
						q.offer (e.v);
					}
				}
			}

			if (dp[T] == null) {
				if (set.isEmpty ())
					break;

				// "raise" lower bound if possible and run dp again (sketchy af but it's correct)
				long lowest = set.pollFirst ();
				long nxtlowest = set.isEmpty () ? -1 : set.first ();

				for (int n = 0; n < N; ++n) {
					for (Edge e : list[n]) {
						if (e.c == lowest) {
							if (set.isEmpty ())
								++e.c;
							else
								e.c = nxtlowest;

							if (e.c > e.d)
								e.c = 0L;
						}
					}
				}

				continue;
			}

			for (int u = T; u != S; u = dp[u].last.u) {
				dp[u].last.c = dp[T].r + 1L;

				if (dp[u].last.c > dp[u].last.d)
					dp[u].last.c = 0L;
			}

			ranges.add (dp[T]);
		}

		ranges.sort ((r1, r2) -> {
			if (r1.l == r2.l)
				return Long.compare (r1.r, r2.r);

			return Long.compare (r1.l, r2.l);
		});

		//for (State s : ranges)
		//	out.printf ("(%d, %d)\n", s.l, s.r);

		long ans = 0L;
		long l = -1L, r = -1L;

		for (State s : ranges) {
			if (l == -1L && r == -1L) {
				l = s.l;
				r = s.r;
			}
			else if (s.l <= r) {
				r = Math.max (r, s.r);
			}
			else {
				ans += (r - l) + 1L;
				l = s.l;
				r = s.r;
			}
		}

		if (l != -1L && r != -1L)
			ans += (r - l) + 1L;

		out.print (ans);
		out.close ();
	}

	private static class State {

		long l, r;
		Edge last;

		public State (long l, long r, Edge last) {
			this.l = l;
			this.r = r;
			this.last = last;
		}
	}

	private static class Edge {

		int u, v;
		long c, d;

		public Edge (int u, int v, long c, long d) {
			this.u = u;
			this.v = v;
			this.c = c;
			this.d = d;
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