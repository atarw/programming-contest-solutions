import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

// atharva washimkar
// August 11, 2018

public class BIOLOGY {

	static boolean[][] vis;
	static int[] cnt;
	static int A, B;

	public static boolean foak () {
		for (int a = 0; a < A; ++a) {
			int cnt = 0;

			for (int b = 0; b < B; ++b)
				if (vis[a][b])
					++cnt;

			if (cnt == 4)
				return true;
		}

		return false;
	}

	public static boolean fh () {
		int[] occ = new int[A];

		for (int a = 0; a < A; ++a)
			for (int b = 0; b < B; ++b)
				if (vis[a][b])
					++occ[a];

		Arrays.sort (occ);

		return occ[A - 1] == 3 && occ[A - 2] == 2;
	}

	public static boolean flush () {
		for (int b = 0; b < B; ++b) {
			int cnt = 0;

			for (int a = 0; a < A; ++a)
				if (vis[a][b])
					++cnt;

			if (cnt == 5)
				return true;
		}

		return false;
	}

	public static boolean straight () {
		boolean[] occ = new boolean[A];

		for (int a = 0; a < A; ++a)
			for (int b = 0; b < B; ++b)
				if (vis[a][b])
					occ[a] = true;

		int run = -1;

		for (int a = 0; a < A; ++a) {
			if (occ[a]) {
				if (run == -1)
					run = 1;
				else if (occ[a - 1])
					++run;
				else
					return false;
			}
		}

		return run == 5;
	}

	public static boolean toak () {
		for (int a = 0; a < A; ++a) {
			int cnt = 0;

			for (int b = 0; b < B; ++b)
				if (vis[a][b])
					++cnt;

			if (cnt == 3)
				return true;
		}

		return false;
	}

	public static boolean tp () {
		boolean first = false;

		for (int a = 0; a < A; ++a) {
			int cnt = 0;

			for (int b = 0; b < B; ++b)
				if (vis[a][b])
					++cnt;

			if (cnt == 2)
				if (first)
					return true;
				else
					first = true;
		}

		return false;
	}

	public static boolean op () {
		for (int a = 0; a < A; ++a) {
			int cnt = 0;

			for (int b = 0; b < B; ++b)
				if (vis[a][b])
					++cnt;

			if (cnt == 2)
				return true;
		}

		return false;
	}

	public static void solve (int n) {
		if (n == 3) {
			int cc = 0;

			for (int a = 0; a < A; ++a)
				for (int b = 0; b < B; ++b)
					if (vis[a][b])
						++cc;

			if (straight () && flush ())
				++cnt[0];
			else if (foak ())
				++cnt[1];
			else if (fh ())
				++cnt[2];
			else if (flush ())
				++cnt[3];
			else if (straight ())
				++cnt[4];
			else if (toak ())
				++cnt[5];
			else if (tp ())
				++cnt[6];
			else if (op ())
				++cnt[7];
			else
				++cnt[8];
		}
		else {
			for (int a = 0; a < A; ++a) {
				for (int b = 0; b < B; ++b) {
					if (!vis[a][b]) {
						vis[a][b] = true;
						solve (n + 1);
						vis[a][b] = false;
					}
				}
			}
		}
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		A = in.iscan ();
		B = in.iscan ();
		int a1 = in.iscan (), b1 = in.iscan ();
		int a2 = in.iscan (), b2 = in.iscan ();

		vis = new boolean[A][B];
		vis[a1][b1] = vis[a2][b2] = true;
		cnt = new int[9];

		solve (0);

		for (int i = 0; i < 9; ++i)
			out.print ((cnt[i] / 6) + (i == 8 ? "\n" : " "));

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