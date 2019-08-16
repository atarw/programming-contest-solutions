import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

// atharva washimkar
// Jul 07, 2019

public class CODEFORCES_1099_C {

	static String S;
	static int[][] dp;
	static State[][] nxt;

	public static int solve (int n, int k) {
		if (dp[n][k] != -1)
			return dp[n][k];

		if (n == S.length ())
			return dp[n][k] = k == 0 ? 1 : 0;

		if (n == S.length () - 1 || (S.charAt (n + 1) != '?' && S.charAt (n + 1) != '*')) {
			if (k == 0) {
				return dp[n][k] = 0;
			}
			else {
				nxt[n][k] = new State (n + 1, k - 1);
				return dp[n][k] = solve (n + 1, k - 1);
			}
		}

		if (S.charAt (n + 1) == '?') {
			int op1 = solve (n + 2, k);
			int op2 = k == 0 ? 0 : solve (n + 2, k - 1);
			dp[n][k] = Math.max (op1, op2);

			if (op1 == 1) {
				nxt[n][k] = new State (n + 2, k);
			}
			else if (op2 == 1) {
				nxt[n][k] = new State (n + 2, k - 1);
			}
		}
		else if (S.charAt (n + 1) == '*') {
			dp[n][k] = 0;

			for (int i = 0; i <= k; ++i) {
				int op = solve (n + 2, i);

				if (op == 1) {
					nxt[n][k] = new State (n + 2, i);
					dp[n][k] = op;
					break;
				}
			}
		}

		return dp[n][k];
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		S = in.sscan ();
		int K = in.iscan ();

		dp = new int[S.length () + 1][K + 1];
		nxt = new State[S.length () + 1][K + 1];

		for (int[] arr : dp)
			Arrays.fill (arr, -1);

		String ans = "";

		if (solve (0, K) == 0)
			ans = "Impossible";
		else {
			int curn = 0;
			int curk = K;

			while (nxt[curn][curk] != null) {
				if (nxt[curn][curk].k < curk) {
					for (int i = 0; i < curk - nxt[curn][curk].k; ++i)
						ans += S.charAt (curn);
				}

				State s = nxt[curn][curk];
				curn = s.n;
				curk = s.k;
			}
		}

		out.print (ans);
		out.close ();
	}

	private static class State {

		int n, k;

		public State (int n, int k) {
			this.n = n;
			this.k = k;
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