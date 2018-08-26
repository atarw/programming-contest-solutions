import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

// atharva washimkar
// August 11, 2018

public class CODEFORCES_242_E {

	static int[] arr;
	static long[][] sum;
	static boolean[][] flip;
	static boolean[][] lazy;

	public static long sum (int i, int j, int l, int r) {
		if (lazy[i][j])
			return (r - l + 1) - sum[i][j];

		return sum[i][j];
	}

	public static boolean flip (int i, int j) {
		return !lazy[i][j] && flip[i][j];
	}

	public static void merge (int i, int j, int l, int r) {
		int mid = (l + r) / 2;
		sum[i][j] = sum (i, j * 2 + 1, l, mid) + sum (i, j * 2 + 2, mid + 1, r);
		flip[i][j] = flip (i, j * 2 + 1) & flip (i, j * 2 + 2);
	}

	public static void push (int i, int j, int l, int r) {
		sum[i][j] = sum (i, j, l, r);
		flip[i][j] = flip (i, j);

		if (l != r && lazy[i][j]) {
			lazy[i][j * 2 + 1] = !lazy[i][j * 2 + 1];
			lazy[i][j * 2 + 2] = !lazy[i][j * 2 + 2];
		}

		lazy[i][j] = false;
	}

	public static void build (int i, int j, int l, int r) {
		if (l == r)
			sum[i][j] = (arr[l] >> i) & 1;
		else {
			int mid = (l + r) / 2;
			build (i, j * 2 + 1, l, mid);
			build (i, j * 2 + 2, mid + 1, r);
			merge (i, j, l, r);
		}
	}

	public static void update (int i, int j, int l, int r, int ql, int qr) {
		if (ql > r || qr < l) {
			return;
		}
		if (l == ql && r == qr) {
			lazy[i][j] = !lazy[i][j];
			push (i, j, l, r);
		}
		else {
			int mid = (l + r) / 2;
			push (i, j, l, r);

			update (i, j * 2 + 1, l, mid, ql, Math.min (mid, qr));
			update (i, j * 2 + 2, mid + 1, r, Math.max (mid + 1, ql), qr);

			merge (i, j, l, r);
		}
	}

	public static long query (int i, int j, int l, int r, int ql, int qr) {
		if (ql > r || qr < l) {
			return 0L;
		}
		if (l == ql && r == qr) {
			push (i, j, l, r);
			return sum[i][j];
		}
		else {
			int mid = (l + r) / 2;
			push (i, j, l, r);

			return query (i, j * 2 + 1, l, mid, ql, Math.min (mid, qr)) +
					       query (i, j * 2 + 2, mid + 1, r, Math.max (mid + 1, ql), qr);
		}
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan ();
		arr = new int[N];
		sum = new long[20][4 * N];
		flip = new boolean[20][4 * N];
		lazy = new boolean[20][4 * N];

		for (int n = 0; n < N; ++n)
			arr[n] = in.iscan ();

		for (int i = 0; i < sum.length; ++i)
			build (i, 0, 0, N - 1);

		int Q = in.iscan ();

		for (int q = 0; q < Q; ++q) {
			int tt = in.iscan (), l = in.iscan () - 1, r = in.iscan () - 1;

			if (tt == 1) {
				long ans = 0L;

				for (int i = 0; i < sum.length; ++i)
					ans += (1L << i) * query (i, 0, 0, N - 1, l, r);

				out.println (ans);
			}
			else {
				int x = in.iscan ();

				for (int i = 0; i < sum.length; ++i)
					if (((x >> i) & 1) == 1)
						update (i, 0, 0, N - 1, l, r);
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