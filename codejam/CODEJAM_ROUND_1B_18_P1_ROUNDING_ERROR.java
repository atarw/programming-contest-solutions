import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NavigableSet;
import java.util.TreeSet;

// atharva washimkar
// August 11, 2018

public class CODEJAM_ROUND_1B_18_P1_ROUNDING_ERROR {

	public static int round (double x) {
		return (int) (x + 0.5);
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int T = in.iscan ();

		for (int tt = 1; tt <= T; ++tt) {
			int N = in.iscan (), L = in.iscan ();
			int[] arr = new int[N];
			Arrays.fill (arr, -1);

			for (int l = 0; l < L; ++l)
				arr[l] = in.iscan ();

			double x = 100.0 / N;
			int roundx = round (x);

			int tot = 0;

			for (int l = 0; l < L; ++l)
				tot += arr[l];

			int ans = 0;

			/*
			1
			7 2
			1 2
			* */

			if (100 % N == 0) {
				// stay the same, doesn't matter what the extra people choose
				ans = 100;
			}
			else if (roundx < x) {
				// round down, people should choose some existing option
				// distribute remaining people based on whatever is going to round up first

				NavigableSet<Pair> set = new TreeSet<Pair> ();

				for (int l = 0; l < L; ++l) {
					double tmp = arr[l] * 100.0 / N;

					if (round (tmp) < tmp)
						set.add (new Pair (l, tmp - round (tmp)));
				}

				int left = N - tot;
				int j = L;

				while (left > 0) {
					if (set.isEmpty ()) {
						// create new entry
						arr[j] = 1;
						++j;
						--left;

						double tmp = arr[j - 1] * 100.0 / N;

						if (round (tmp) < tmp)
							set.add (new Pair (j - 1, tmp - round (tmp)));
					}
					else {
						Pair nxt = set.pollFirst ();
						++arr[nxt.i];
						--left;

						double tmp = arr[nxt.i] * 100.0 / N;

						if (round (tmp) < tmp)
							set.add (new Pair (nxt.i, tmp - round (tmp)));
					}
				}

				for (int l = 0; l < N && arr[l] != -1; ++l)
					ans += round (arr[l] * 100.0 / N);
			}
			else if (roundx > x) {
				// round up, people should all choose new options
				for (int l = 0; l < L; ++l)
					ans += round (arr[l] * 100.0 / N);

				ans += round (100.0 / N) * (N - tot);
			}
			else {
				throw new RuntimeException ("wew");
			}

			out.printf ("Case #%d: %d\n", tt, ans);
		}

		out.close ();
	}

	private static class Pair implements Comparable<Pair> {

		int i;
		double d;

		public int compareTo (Pair p) {
			if (Double.compare (p.d, d) != 0)
				return Double.compare (p.d, d);

			return Integer.compare (i, p.i);
		}

		public Pair (int i, double d) {
			this.i = i;
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