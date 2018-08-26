import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

// atharva washimkar
// August 11, 2018

public class CODEFORCES_225_C {

	static int[][][] dp;
	static char[][] arr;
	static int[] cost1, cost2;
	static int N, M, X, Y;

	public static int solve (int m, int z, int last) {
		if (dp[m][z][last] != -1)
			return dp[m][z][last];

		if (m == M) {
			if (z < X || z > Y)
				return dp[m][z][last] = 1 << 25;
			else
				return dp[m][z][last] = 0;
		}

		if (z < X) {
			return dp[m][z][last] = (last == 0 ? cost1[m] : cost2[m]) + solve (m + 1, z + 1, last);
		}
		else if (z == Y) {
			return dp[m][z][last] = (last == 0 ? cost2[m] : cost1[m]) + solve (m + 1, 1, last ^ 1);
		}
		else {
			return dp[m][z][last] = Math.min ((last == 0 ? cost1[m] : cost2[m]) + solve (m + 1, z + 1, last),
			                                  (last == 0 ? cost2[m] : cost1[m]) + solve (m + 1, 1, last ^ 1));
		}
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		N = in.iscan ();
		M = in.iscan ();
		X = in.iscan ();
		Y = in.iscan ();
		arr = new char[N][M];

		for (int n = 0; n < N; ++n)
			arr[n] = in.sscan ().toCharArray ();

		dp = new int[M + 1][Y + 1][2];

		for (int m = 0; m <= M; ++m)
			for (int z = 0; z <= Y; ++z)
				for (int l = 0; l <= 1; ++l)
					dp[m][z][l] = -1;

		cost1 = new int[M];
		cost2 = new int[M];

		for (int n = 0; n < N; ++n)
			for (int m = 0; m < M; ++m)
				if (arr[n][m] == '#')
					++cost1[m];

		for (int m = 0; m < M; ++m)
			cost2[m] = N - cost1[m];

		out.print (Math.min (solve (0, 0, 0), solve (0, 0, 1)));
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