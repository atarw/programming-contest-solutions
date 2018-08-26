import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

// atharva washimkar
// Aug 27, 2017
// given an array of N integers, perform Q range updates and range sum queries

public class SQUARE_ROOT_DECOMPOSITION_3 {

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan ();
		int[] arr = new int[N];

		for (int n = 0; n < N; ++n)
			arr[n] = in.iscan ();

		int rt = (int) Math.ceil (Math.sqrt (N));
		int[] sum = new int[rt];
		int[] inc = new int[rt];
		int[] first = new int[rt];
		int[] last = new int[rt];

		Arrays.fill (first, 1 << 30);
		Arrays.fill (last, -1);

		for (int n = 0; n < N; ++n) {
			sum[n / rt] += arr[n];
			first[n / rt] = Math.min (first[n / rt], n);
			last[n / rt] = Math.max (last[n / rt], n);
		}

		int Q = in.iscan ();

		for (int q = 0, c, l, r, x; q < Q; ++q) {
			c = in.iscan ();
			l = in.iscan () - 1;
			r = in.iscan () - 1;

			if (c == 1) {
				// increment [l,r] by x
				x = in.iscan ();

				int block1 = l / rt, block2 = r / rt;

				// increment sum for all full blocks
				for (int i = block1 + 1; i < block2; ++i)
					inc[i] += x;

				// increment sum for potentially partial first block
				// the min is in case l & r are in the same block
				for (int i = l; i <= Math.min (r, last[block1]); ++i)
					arr[i] += x;

				// increment sum for potentiallly partial last block
				// the max is in case l & r are in the same block
				if (block1 != block2)
					for (int i = Math.max (l, first[block2]); i <= r; ++i)
						arr[i] += x;
			}
			else {
				// get sum of [l,r]
				long ans = 0L;
				int block1 = l / rt, block2 = r / rt;

				// add up sums for all full blocks
				for (int i = block1 + 1; i < block2; ++i)
					ans += sum[i] + inc[i];

				// add up sum for potentially partial first block
				// the min is in case l & r are in the same block
				for (int i = l; i <= Math.min (r, last[block1]); ++i)
					ans += arr[i] + inc[i / rt];

				// add up sum for potentiallly partial last block
				// the max is in case l & r are in the same block
				if (block1 != block2)
					for (int i = Math.max (l, first[block2]); i <= r; ++i)
						ans += arr[i] + inc[i / rt];

				out.println (ans);
			}

			out.flush ();
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