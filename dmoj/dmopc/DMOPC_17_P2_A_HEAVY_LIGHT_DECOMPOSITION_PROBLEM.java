import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// atharva washimkar
// August 11, 2018

public class DMOPC_17_P2_A_HEAVY_LIGHT_DECOMPOSITION_PROBLEM {

	static int[] arr;
	static List<Integer>[] list;
	static int[][] dist;
	static boolean[] vis;

	public static void dfs (int u, int s, int d) {
		vis[u] = true;
		dist[s][u] = d;

		for (int v : list[u])
			if (!vis[v])
				dfs (v, s, d + 1);
	}

	public static List<Integer> bfs (int x, int y) {
		List<Integer> res = new ArrayList<Integer> ();
		Queue<Integer> q = new ArrayDeque<Integer> ();
		q.offer (x);

		while (!q.isEmpty ()) {
			int u = q.poll ();
			res.add (arr[u]);

			for (int v : list[u])
				if (dist[v][y] + 1 == dist[u][y])
					q.offer (v);
		}

		return res;
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan (), Q = in.iscan ();
		arr = new int[N];

		for (int n = 0; n < N; ++n)
			arr[n] = in.iscan ();

		list = new ArrayList[N];

		for (int n = 0; n < N; ++n)
			list[n] = new ArrayList<Integer> ();

		for (int n = 0; n < N - 1; ++n) {
			int a = in.iscan () - 1, b = in.iscan () - 1;
			list[a].add (b);
			list[b].add (a);
		}

		dist = new int[N][N];

		for (int n = 0; n < N; ++n) {
			vis = new boolean[N];
			dfs (n, n, 0);
		}

		for (int q = 0; q < Q; ++q) {
			int cmd = in.iscan (), x = in.iscan () - 1, y = in.iscan () - 1;
			List<Integer> res = bfs (x, y);

			if (cmd == 1) {
				int sum = 0;

				for (int i = 0; i < res.size (); ++i)
					sum += res.get (i);

				double ans = sum;
				ans /= res.size ();

				out.println ((int) (ans + 0.5));
			}
			else if (cmd == 2) {
				Collections.sort (res);
				int mid = res.size () / 2;

				if (res.size () % 2 == 0) {
					double ans = res.get (mid) + res.get (mid - 1);
					ans /= 2.0;
					out.println ((int) (ans + 0.5));
				}
				else {
					out.println (res.get (mid));
				}
			}
			else {
				Map<Integer, Integer> frq = new HashMap<Integer, Integer> ();

				for (int i = 0; i < res.size (); ++i) {
					if (!frq.containsKey (res.get (i))) {
						frq.put (res.get (i), 1);
					}
					else {
						frq.put (res.get (i), frq.get (res.get (i)) + 1);
					}
				}

				int max = 0, ans = 0;

				for (int i : frq.keySet ()) {
					if (frq.get (i) > max || max == frq.get (i) && ans > i) {
						max = frq.get (i);
						ans = i;
					}
				}

				out.println (ans);
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