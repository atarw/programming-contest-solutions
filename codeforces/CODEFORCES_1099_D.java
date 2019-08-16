import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

// atharva washimkar
// Jul 07, 2019

public class CODEFORCES_1099_D {

	static int[] p;
	static long[] s;
	static List<Integer>[] list;

	public static boolean check (int u, long maxsum) {
		if (s[u] != -1 && s[u] < maxsum)
			return false;

		maxsum = Math.max (maxsum, s[u]);
		boolean good = true;

		for (int v : list[u])
			good &= check (v, maxsum);

		return good;
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan ();
		p = new int[N];
		p[0] = -1;

		for (int n = 1; n < N; ++n)
			p[n] = in.iscan () - 1;

		list = new ArrayList[N];

		for (int n = 0; n < N; ++n)
			list[n] = new ArrayList<Integer> ();

		for (int n = 1; n < N; ++n)
			list[p[n]].add (n);

		s = new long[N];

		for (int n = 0; n < N; ++n)
			s[n] = in.lscan ();

		if (!check (0, s[0])) {
			out.print (-1);
		}
		else {
			boolean[] leaves = new boolean[N];
			Arrays.fill (leaves, true);

			for (int n = 1; n < N; ++n)
				leaves[p[n]] = false;

			Deque<Integer> q = new ArrayDeque<Integer> ();

			for (int n = 0; n < N; ++n)
				if (leaves[n])
					q.offerFirst (n);

			while (!q.isEmpty ()) {
				int u = q.pollFirst ();

				if (s[u] == -1) {
					long minsum = Long.MAX_VALUE;

					for (int v : list[u])
						minsum = Math.min (minsum, s[v]);

					s[u] = minsum == Long.MAX_VALUE ? 0 : minsum;
				}

				if (p[u] != -1)
					q.offerLast (p[u]);
			}

			long[] a = new long[N];
			a[0] = s[0];

			q.offer (0);

			while (!q.isEmpty ()) {
				int u = q.pollFirst ();

				for (int v : list[u]) {
					if (s[v] != 0)
						a[v] = s[v] - s[u];

					q.offerLast (v);
				}
			}

			long sum = 0L;

			for (int n = 0; n < N; ++n)
				sum += a[n];

			out.print (sum);
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