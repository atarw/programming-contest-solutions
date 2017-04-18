import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

public class CODEFORCES_86_D {

	static int blocks;
	static int[] cnt;
	static long curr_ans;

	public static void add (int v) {
		curr_ans -= (long) cnt[v] * cnt[v] * v;
		++cnt[v];
		curr_ans += (long) cnt[v] * cnt[v] * v;
	}

	public static void remove (int v) {
		curr_ans -= (long) cnt[v] * cnt[v] * v;
		--cnt[v];
		curr_ans += (long) cnt[v] * cnt[v] * v;
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan (), Q = in.iscan ();
		blocks = (int) Math.sqrt (N);

		int[] arr = new int[N];

		for (int n = 0; n < N; ++n)
			arr[n] = in.iscan ();

		Query[] queries = new Query[Q];
		long[] ans = new long[Q];

		for (int q = 0; q < Q; ++q)
			queries[q] = new Query (in.iscan () - 1, in.iscan () - 1, q);

		Arrays.sort (queries);
		cnt = new int[1000001];
		int currL = 0, currR = 0;

		for (int q = 0; q < Q; ++q) {
			// remove old data
			while (currL < queries[q].l) {
				remove (arr[currL]);
				++currL;
			}

			// add current data
			while (currL > queries[q].l) {
				add (arr[currL - 1]);
				--currL;
			}

			while (currR <= queries[q].r) {
				add (arr[currR]);
				++currR;
			}

			// remove old data
			while (currR > 1 + queries[q].r) {
				remove (arr[currR - 1]);
				--currR;
			}

			ans[queries[q].i] = curr_ans;
		}

		for (int q = 0; q < Q; ++q)
			out.println (ans[q]);

		out.close ();
	}

	private static class Query implements Comparable <Query> {

		int l, r, i;

		public Query (int l, int r, int i) {
			this.l = l;
			this.r = r;
			this.i = i;
		}

		public int compareTo (Query q) {
			if (this.l / blocks == q.l / blocks)
				return Integer.compare (this.r, q.r);

			return Integer.compare (this.l / blocks, q.l / blocks);
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