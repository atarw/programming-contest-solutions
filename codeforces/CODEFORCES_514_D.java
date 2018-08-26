import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.NavigableMap;
import java.util.TreeMap;

// atharva washimkar
// August 11, 2018

public class CODEFORCES_514_D {

	public static void add (NavigableMap<Long, Integer> map, long i) {
		if (!map.containsKey (i))
			map.put (i, 1);
		else
			map.put (i, map.get (i) + 1);
	}

	public static void del (NavigableMap<Long, Integer> map, long i) {
		if (!map.containsKey (i))
			return;

		if (map.get (i) == 1)
			map.remove (i);
		else
			map.put (i, map.get (i) - 1);
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan (), M = in.iscan ();
		long K = in.lscan ();
		long[][] arr = new long[N][M];

		for (int n = 0; n < N; ++n)
			for (int m = 0; m < M; ++m)
				arr[n][m] = in.lscan ();

		NavigableMap<Long, Integer>[] cur = new TreeMap[M];

		for (int m = 0; m < M; ++m)
			cur[m] = new TreeMap<Long, Integer> ();

		int l = 0, r = 0;
		int best = 0, start = 0;

		while (l < N) {
			while (r < N) {
				long sum = 0L;

				for (int m = 0; m < M; ++m)
					sum += Math.max (cur[m].isEmpty () ? -1 : cur[m].lastKey (), arr[r][m]);

				if (sum > K)
					break;

				for (int m = 0; m < M; ++m)
					add (cur[m], arr[r][m]);

				++r;
			}

			if (r - l > best) {
				best = r - l;
				start = l;
			}

			for (int m = 0; m < M; ++m)
				del (cur[m], arr[l][m]);

			++l;

			if (l > r)
				r = l;
		}

		long[] shots = new long[M];

		for (int m = 0; m < M; ++m)
			for (int i = start; i < start + best; ++i)
				shots[m] = Math.max (shots[m], arr[i][m]);

		for (int m = 0; m < M; ++m)
			out.print (shots[m] + " ");

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