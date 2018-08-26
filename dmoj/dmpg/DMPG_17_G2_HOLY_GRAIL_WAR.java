import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

// atharva washimkar
// August 11, 2018

public class DMPG_17_G2_HOLY_GRAIL_WAR {

	static long[] arr;
	static Node[] tree;

	public static Node merge (Node l, Node r) {
		long sum = l.sum + r.sum;
		long pref = Math.max (l.pref, l.sum + r.pref);
		long suf = Math.max (r.suf, r.sum + l.suf);
		long best = Math.max (Math.max (l.best, r.best), l.suf + r.pref);
		return new Node (sum, best, pref, suf);
	}

	public static void build (int i, int l, int r) {
		if (l == r) {
			tree[i] = new Node (arr[l], arr[l], arr[l], arr[l]);
		}
		else {
			int mid = (l + r) / 2;
			build (i * 2 + 1, l, mid);
			build (i * 2 + 2, mid + 1, r);
			tree[i] = merge (tree[i * 2 + 1], tree[i * 2 + 2]);
		}
	}

	public static void update (int i, int l, int r, int arri, long v) {
		if (l == r) {
			arr[arri] = v;
			tree[i] = new Node (v, v, v, v);
		}
		else {
			int mid = (l + r) / 2;

			if (arri <= mid)
				update (i * 2 + 1, l, mid, arri, v);
			else
				update (i * 2 + 2, mid + 1, r, arri, v);

			tree[i] = merge (tree[i * 2 + 1], tree[i * 2 + 2]);
		}
	}

	public static Node query (int i, int l, int r, int ql, int qr) {
		if (l == ql && r == qr)
			return tree[i];

		int mid = (l + r) / 2;

		if (qr <= mid)
			return query (i * 2 + 1, l, mid, ql, qr);
		else if (mid < ql)
			return query (i * 2 + 2, mid + 1, r, ql, qr);

		Node ll = query (i * 2 + 1, l, mid, ql, mid);
		Node rr = query (i * 2 + 2, mid + 1, r, mid + 1, qr);

		return merge (ll, rr);
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan (), Q = in.iscan ();
		arr = new long[N];
		tree = new Node[4 * N];

		for (int n = 0; n < N; ++n)
			arr[n] = in.iscan ();

		build (0, 0, N - 1);

		for (int q = 0; q < Q; ++q)
			if (in.sscan ().equals ("S"))
				update (0, 0, N - 1, in.iscan () - 1, in.lscan ());
			else
				out.println (query (0, 0, N - 1, in.iscan () - 1, in.iscan () - 1).best);

		out.close ();
	}

	private static class Node {

		long sum, best, pref, suf;

		public Node (long sum, long best, long pref, long suf) {
			this.sum = sum;
			this.best = best;
			this.pref = pref;
			this.suf = suf;
		}

		public String toString () {
			return sum + " " + best + " " + pref + " " + suf;
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