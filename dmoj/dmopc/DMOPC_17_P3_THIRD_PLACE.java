import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

// atharva washimkar
// August 11, 2018

public class DMOPC_17_P3_THIRD_PLACE {

	static List<Edge>[] list;

	public static long[] bfs (int s) {
		long[] dist = new long[list.length];
		Arrays.fill (dist, 1L << 40);
		dist[s] = 0L;

		Queue<Integer> q = new ArrayDeque<Integer> ();
		q.offer (s);

		while (!q.isEmpty ()) {
			int u = q.poll ();

			for (Edge e : list[u]) {
				if (dist[u] + e.w < dist[e.v]) {
					dist[e.v] = dist[u] + e.w;
					q.offer (e.v);
				}
			}
		}

		return dist;
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan ();
		list = new ArrayList[N];

		for (int n = 0; n < N; ++n)
			list[n] = new ArrayList<Edge> ();

		for (int n = 0; n < N - 1; ++n) {
			int u = in.iscan () - 1, v = in.iscan () - 1;
			long w = in.lscan ();
			list[u].add (new Edge (u, v, w));
			list[v].add (new Edge (v, u, w));
		}

		long[] dist = bfs (0);
		int furthest = 0;

		for (int n = 0; n < N; ++n)
			if (dist[n] > dist[furthest])
				furthest = n;

		// dist has distance from s to all
		int s = furthest;
		dist = bfs (s);

		for (int n = 0; n < N; ++n)
			if (dist[n] > dist[furthest])
				furthest = n;

		// diameter is from s to furthest
		// dist2 has distance from furthest to all
		long[] dist2 = bfs (furthest);

		long ans = 0L;

		// from s to all
		for (int i = 0; i < dist.length; ++i) {
			if (i == furthest)
				continue;

			ans = Math.max (ans, dist[i]);
		}

		// from furthest to all
		for (int i = 0; i < dist2.length; ++i) {
			if (i == s)
				continue;

			ans = Math.max (ans, dist2[i]);
		}

		out.print (ans);
		out.close ();
	}

	private static class Edge {

		int u, v;
		long w;

		public Edge (int u, int v, long w) {
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