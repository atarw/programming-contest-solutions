import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

// atharva washimkar
// Sep 01, 2017

public class CODEFORCES_849_B {

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan ();
		long[] x = new long[N], y = new long[N];

		for (int n = 0; n < N; ++n) {
			x[n] = n;
			y[n] = in.lscan ();
		}

		boolean[] done = new boolean[N];
		boolean good = false;

		out:
		for (int n = 1; n < N && !good; ++n) {
			long a = y[n] - y[0];
			long b = x[n] - x[0];
			long gcd = UTILITIES.gcd (a, b);
			a /= gcd;
			b /= gcd;
			done[0] = true;
			done[n] = true;
			int cnt = 0;
			//out.printf ("initial line between points (%d,%d) and (%d,%d), slope is %d/%d\n", x[0], y[0], x[n], y[n], a, b);

			for (int n2 = 1; n2 < N && !good; ++n2) {
				if (n2 == n)
					continue;

				long a2 = y[n2] - y[0];
				long b2 = x[n2] - x[0];
				long gcd2 = UTILITIES.gcd (a2, b2);
				a2 /= gcd2;
				b2 /= gcd2;
				//out.printf ("    line between points (%d,%d) and (%d,%d), slope is %d/%d\n", x[0], y[0], x[n2], y[n2], a2, b2);

				if (a2 == a && b2 == b) {
					done[n2] = true;
				}
				else {
					++cnt;
				}
			}

			//out.println ();

			if (cnt == 0) {
				done = new boolean[N];
				continue;
			}
			else if (cnt == 1) {
				good = true;
				break out;
			}
			else {
				int f = -1, s = -1;
				for (int n2 = 1; n2 < N; ++n2) {
					if (n2 == n)
						continue;

					if (!done[n2]) {
						if (f == -1) {
							f = n2;
						}
						else if (s == -1) {
							s = n2;
						}
					}
				}

				long a2 = y[f] - y[s];
				long b2 = x[f] - x[s];
				long gcd2 = UTILITIES.gcd (a2, b2);
				a2 /= gcd2;
				b2 /= gcd2;

				for (int n2 = 1; n2 < N; ++n2) {
					if (!done[n2] && f != n2 && s != n2) {
						long a3 = y[n2] - y[f];
						long b3 = x[n2] - x[f];
						long gcd3 = UTILITIES.gcd (a3, b3);
						a3 /= gcd3;
						b3 /= gcd3;

						if (a3 != a2 || b3 != b2) {
							done = new boolean[N];
							continue out;
						}
					}
				}

				// no point is on two lines
				for (int n2 = 0; n2 < N; ++n2) {
					if (done[n2]) {
						long a3 = y[n2] - y[f];
						long b3 = x[n2] - x[f];
						long gcd3 = UTILITIES.gcd (a3, b3);
						a3 /= gcd3;
						b3 /= gcd3;

						if (a3 == a2 && b3 == b2) {
							done = new boolean[N];
							continue out;
						}
					}
				}

				if (a == a2 && b == b2) {
					good = true;
					break out;
				}
			}

			done = new boolean[N];
			//out.println ();
		}

		// case where first point is alone
		if (!good) {
			Set<Pair> set = new HashSet<Pair> ();
			Pair sample = null;

			for (int n = 2; n < N; ++n) {
				long a = y[n] - y[1];
				long b = x[n] - x[1];
				long gcd = UTILITIES.gcd (a, b);
				a /= gcd;
				b /= gcd;
				//out.printf ("initial line between points (%d,%d) and (%d,%d), slope is %d/%d\n", x[1], y[1], x[n], y[n], a, b);
				sample = new Pair (a, b);
				set.add (sample);
			}

			if (set.size () == 1) {
				// make sure point 1 doesn't lie here
				long a = y[0] - y[1];
				long b = x[0] - x[1];
				long gcd = UTILITIES.gcd (a, b);
				a /= gcd;
				b /= gcd;

				if (sample.a != a || sample.b != b)
					good = true;
			}
		}

		if (good)
			out.print ("Yes");
		else
			out.print ("No");

		out.close ();
	}

	private static class Pair {

		long a, b;

		public boolean equals (Object o) {
			Pair p = (Pair) o;
			return a == p.a && b == p.b;
		}

		public int hashCode () {
			return Long.hashCode (a) * 17 + Long.hashCode (b) * 31;
		}

		public Pair (long a, long b) {
			this.a = a;
			this.b = b;
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

		public static long gcd (long a, long b) {
			return b == 0L ? a : gcd (b, a % b);
		}

		public static long lcm (int a, int b) {
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