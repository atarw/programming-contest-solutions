import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

// atharva washimkar
// Jul 18, 2017

public class MOCK_CCO_17_P2_PENETRATING_POWER {

	static long[] tree, lazy;

	public static long max (int i) {
		return tree[i] + lazy[i];
	}

	public static void merge (int i, int l, int r) {
		int mid = (l + r) / 2;
		tree[i] = Math.max (max (i * 2 + 1), max (i * 2 + 2));
	}

	public static void push (int i, int l, int r) {
		tree[i] = tree[i] + lazy[i];

		if (l != r) {
			lazy[i * 2 + 1] += lazy[i];
			lazy[i * 2 + 2] += lazy[i];
		}

		lazy[i] = 0;
	}

	public static void update (int i, int l, int r, int ql, int qr, int v) {
		if (l == ql && r == qr) {
			lazy[i] += v;
			push (i, l, r);
		}
		else {
			int mid = (l + r) / 2;
			push (i, l, r);

			if (qr <= mid)
				update (i * 2 + 1, l, mid, ql, qr, v);
			else if (ql > mid)
				update (i * 2 + 2, mid + 1, r, ql, qr, v);
			else {
				update (i * 2 + 1, l, mid, ql, mid, v);
				update (i * 2 + 2, mid + 1, r, mid + 1, qr, v);
			}

			merge (i, l, r);
		}
	}

	public static long query (int i, int l, int r, int ql, int qr) {
		if (l == ql && r == qr) {
			push (i, l, r);
			return tree[i];
		}
		else {
			int mid = (l + r) / 2;
			push (i, l, r);

			long ans;

			if (qr <= mid)
				ans = query (i * 2 + 1, l, mid, ql, qr);
			else if (ql > mid)
				ans = query (i * 2 + 2, mid + 1, r, ql, qr);
			else {
				long lq = query (i * 2 + 1, l, mid, ql, mid);
				long rq = query (i * 2 + 2, mid + 1, r, mid + 1, qr);
				ans = Math.max (lq + lazy[i * 2 + 1], rq + lazy[i * 2 + 2]);
			}

			merge (i, l, r);
			return ans;
		}
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan (), K = in.iscan (), Q = in.iscan ();
		tree = new long[4 * N];
		lazy = new long[4 * N];

		for (int q = 0, c, x, y; q < Q; ++q) {
			c = in.iscan ();
			x = in.iscan ();
			y = in.iscan ();

			if (c == 0)
				update (0, 0, N - 1, Math.max (0, x - K + 1), x, y);
			else
				out.println (query (0, 0, N - 1, Math.min (N - 1, x), Math.min (N - 1, y)));
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