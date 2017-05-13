import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

// atharva washimkar
// May 10, 2017

public class TWO_SAT {

	static List<Integer>[] list, rev;
	static Deque<Integer> q = new ArrayDeque<Integer> ();
	static boolean [] vis;
	static int [] id;
	static int cnt;

	public static void dfs (int u) {
		vis [u] = true;

		for (int v : list [u])
			if (!vis [v])
				dfs (v);

		q.offerFirst (u);
	}

	public static void dfs2 (int u) {
		vis [u] = true;
		id [u] = ++cnt;

		for (int v : rev [u])
			if (!vis [v])
				dfs2 (v);
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		// read input as OR statements, e.g. "A B" -> A || B
		int N = in.iscan (), M = in.iscan ();

		for (int m = 0, a, b, na, nb; m < M; ++m) {
			a = in.iscan () - 1; b = in.iscan () - 1;
			na = a + N; nb = b + N; // each variable has two nodes: itself and the opposite of itself (NOT)
			// A || B means: if NOT A, then B must be true | NOT B, then A must be true
			list [na].add (b); list [nb].add (a);
			rev [b].add (na); rev [a].add (nb);
		}

		// do SCC, which TOPOLOGICALLY SORTS the connected components in order
		vis = new boolean [N];

		for (int n = 0; n < N; ++n)
			if (!vis [n])
				dfs (n);

		vis = new boolean [N];
		id = new int [N];

		for (int n = 0; n < N; ++n)
			if (!vis [n])
				dfs2 (n);

		boolean [] ans = new boolean [N];
		boolean sat = true;

		for (int n = 0; n < N && sat; ++n) {
			if (id [n] == id [n + N])// if the NOT variable and the variable are in the same component, the equation is not satisfiable
				sat = false;

			ans [n] = id [n] < id [n + N]; // n + N is the NOT, so whichever comes first determines whether the variable should be true or false to satisfy the equation
		}

		if (sat)
			for (int n = 0; n < N; ++n)
				out.println ("Variable " + (n + 1) + ": " + ans [n]);
		else
			out.println ("Impossible to satisfy equation.");

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