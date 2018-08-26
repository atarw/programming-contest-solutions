import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// atharva washimkar
// August 11, 2018

public class CODEFORCES_416_C {

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan ();
		Request[] visitors = new Request[N];

		for (int n = 0; n < N; ++n)
			visitors[n] = new Request (in.iscan (), in.iscan (), n);

		int K = in.iscan ();
		Table[] tables = new Table[K];

		for (int k = 0; k < K; ++k)
			tables[k] = new Table (in.iscan (), k);

		Arrays.sort (visitors, Comparator.comparingInt (a -> a.c));
		Arrays.sort (tables, Comparator.comparingInt (a -> a.t));

		int[][] dp = new int[N + 1][K + 1];

		for (int n = N - 1; n >= 0; --n) {
			for (int k = K - 1; k >= 0; --k) {
				dp[n][k] = dp[n + 1][k];

				if (tables[k].t >= visitors[n].c)
					dp[n][k] = Math.max (dp[n][k], visitors[n].p + dp[n + 1][k + 1]);
				else
					dp[n][k] = Math.max (dp[n][k], dp[n][k + 1]);
			}
		}

		List<Integer> chosenRequest = new ArrayList<Integer> (), chosenTable = new ArrayList<Integer> ();
		int curn = 0, curk = 0;

		while (curn < N && curk < K) {
			if (dp[curn][curk] == dp[curn + 1][curk]) {
				++curn;
			}
			else if (dp[curn][curk] == dp[curn][curk + 1]) {
				++curk;
			}
			else {
				chosenRequest.add (visitors[curn].i + 1);
				chosenTable.add (tables[curk].i + 1);

				++curn;
				++curk;
			}
		}

		out.println (chosenRequest.size () + " " + dp[0][0]);

		for (int i = 0; i < chosenRequest.size (); ++i)
			out.println (chosenRequest.get (i) + " " + chosenTable.get (i));

		out.close ();
	}

	private static class Table {

		int t, i;

		public Table (int t, int i) {
			this.t = t;
			this.i = i;
		}
	}

	private static class Request {

		int c, p, i;

		public Request (int c, int p, int i) {
			this.c = c;
			this.p = p;
			this.i = i;
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

			if (numChars == -1)
				return numChars;

			return buf[curChar++];
		}

		public int iscan () throws IOException {
			int c = cscan (), sgn = 1;

			while (space (c))
				c = cscan ();

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

			while (space (c))
				c = cscan ();

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

			while (space (c))
				c = cscan ();

			if (c == '-') {
				sgn = -1;
				c = cscan ();
			}

			double res = 0;

			while (!space (c) && c != '.') {
				if (c == 'e' || c == 'E')
					return res * UTILITIES.fast_pow (10, iscan ());

				res *= 10;
				res += c - '0';
				c = cscan ();
			}

			if (c == '.') {
				c = cscan ();
				double m = 1;

				while (!space (c)) {
					if (c == 'e' || c == 'E')
						return res * UTILITIES.fast_pow (10, iscan ());

					m /= 10;
					res += (c - '0') * m;
					c = cscan ();
				}
			}

			return res * sgn;
		}

		public long lscan () throws IOException {
			int c = cscan (), sgn = 1;

			while (space (c))
				c = cscan ();

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