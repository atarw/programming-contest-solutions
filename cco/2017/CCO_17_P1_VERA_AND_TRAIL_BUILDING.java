import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

// atharva washimkar
// May 16, 2017

public class CCO_17_P1_VERA_AND_TRAIL_BUILDING {

	public static Graph make_cycle (int K, int inc) {
		Graph cycle = new Graph (getV (K), getV (K));

		for (int k = 0; k < cycle.E; ++k) {
			cycle.edges[k][0] = inc + k + 1;
			cycle.edges[k][1] = inc + k + 2;
		}

		cycle.edges[cycle.E - 1][0] = inc + cycle.V;
		cycle.edges[cycle.E - 1][1] = inc + 1;

		return cycle;
	}

	public static int getV (int K) {
		return (int) ((1 + Math.sqrt (1 + 8 * K)) / 2);
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int K = in.iscan ();
		NavigableSet<Integer> cyc_sz = new TreeSet<Integer> ();

		for (int cnt = 1, i = 1; cnt <= K; ++i, cnt += i)
			cyc_sz.add (cnt);

		int inc = 0, sum = 0;
		List<Graph> cycles = new ArrayList<Graph> ();

		while (K > 0) {
			int sz = cyc_sz.floor (K);
			cycles.add (make_cycle (sz, inc));
			inc += getV (sz);
			K -= sz;
			sum += getV (sz);
		}

		Graph ans = new Graph (sum, sum + cycles.size () - 1);
		List<Integer> random = new ArrayList<Integer> ();

		inc = 0;

		for (Graph g : cycles) {
			for (int e = 0; e < g.E; ++e)
				ans.edges[e + inc] = g.edges[e];

			random.add (g.edges[0][0]);
			inc += g.E;
		}

		for (int i = 0; i < random.size () - 1; ++i)
			ans.edges[i + inc] = new int[]{random.get (i), random.get (i + 1)};

		out.print (ans);
		out.close ();
	}

	private static class Graph {

		int V, E;
		int[][] edges;

		public Graph (int V, int E) {
			this.V = V;
			this.E = E;
			this.edges = new int[E][2];
		}

		public String toString () {
			StringBuilder sb = new StringBuilder ();

			sb.append (V);
			sb.append (" ");
			sb.append (E);
			sb.append ("\n");

			for (int e = 0; e < E; ++e) {
				sb.append (edges[e][0]);
				sb.append (" ");
				sb.append (edges[e][1]);
				sb.append ("\n");
			}

			return sb.toString ();
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