import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class haybales2 {

	static long[] arr, lazy;
	static Node[] tree;

	public static long sum (int i, int l, int r) {
		return tree[i].sum + (r - l + 1) * lazy[i];
	}

	public static long min (int i) {
		return tree[i].min + lazy[i];
	}

	public static void merge (int i, int l, int r) {
		int mid = (l + r) / 2;
		tree[i] = new Node (sum (i * 2 + 1, l, mid) + sum (i * 2 + 2, mid + 1, r),
		                    Math.min (min (i * 2 + 1), min (i * 2 + 2)));
	}

	public static void push (int i, int l, int r) {
		tree[i] = new Node (tree[i].sum + (r - l + 1) * lazy[i], tree[i].min + lazy[i]);

		if (l != r) {
			lazy[i * 2 + 1] += lazy[i];
			lazy[i * 2 + 2] += lazy[i];
		}

		lazy[i] = 0;
	}

	public static void build (int i, int l, int r) {
		if (l == r) {
			tree[i] = new Node (arr[l], arr[l]);
		}
		else {
			int mid = (l + r) / 2;

			build (i * 2 + 1, l, mid);
			build (i * 2 + 2, mid + 1, r);
			merge (i, l, r);
		}
	}

	public static void update (int i, int l, int r, int ql, int qr, int v) {
		//System.err.println (i + " " + l + " " + r + " " + ql + " " + qr + " " + v + " ");
		//System.err.println ("node: " + tree[i].sum + " " + tree[i].max + " " + lazy[i]);
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
		//System.err.println ("new node: " + tree[i].sum + " " + tree[i].max + " " + lazy[i]);
	}

	public static Node query (int i, int l, int r, int ql, int qr) {
		//System.err.println ("q: " + i + " " + l + " " + r + " " + ql + " " + qr);
		//System.err.println ("qnode: " + tree[i].sum + " " + tree[i].max + " " + lazy[i]);

		if (l == ql && r == qr) {
			push (i, l, r);
			return tree[i];
		}
		else {
			int mid = (l + r) / 2;
			push (i, l, r);

			Node ans;

			if (qr <= mid)
				ans = query (i * 2 + 1, l, mid, ql, qr);
			else if (ql > mid)
				ans = query (i * 2 + 2, mid + 1, r, ql, qr);
			else {
				Node lq = query (i * 2 + 1, l, mid, ql, mid);
				Node rq = query (i * 2 + 2, mid + 1, r, mid + 1, qr);
				ans = new Node (lq.sum + rq.sum + (mid - l + 1) * lazy[i * 2 + 1] + (r - mid) * lazy[i * 2 + 2],
				                Math.min (lq.min + lazy[i * 2 + 1], rq.min + lazy[i * 2 + 2]));
			}

			merge (i, l, r);
			return ans;
		}
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan (), Q = in.iscan ();
		arr = new long[N];
		tree = new Node[4 * N];
		lazy = new long[4 * N];

		for (int n = 0; n < N; ++n)
			arr[n] = in.iscan ();

		build (0, 0, N - 1);

		for (int q = 0, c, a, b; q < Q; ++q) {
			c = in.cscan ();
			a = in.iscan () - 1;
			b = in.iscan () - 1;

			if (c == 'M') // query min
				out.println (query (0, 0, N - 1, a, b).min);
			else if (c == 'P') // increment range
				update (0, 0, N - 1, a, b, in.iscan ());
			else // query sum
				out.println (query (0, 0, N - 1, a, b).sum);
		}

		out.close ();
	}

	private static class Node {

		long sum, min;

		public Node (long sum, long min) {
			this.sum = sum;
			this.min = min;
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

		// read one character
		public int cscan () throws IOException {
			//if (numChars == -1) throw new InputMismatchException ();

			if (curChar >= numChars) {
				curChar = 0;
				numChars = stream.read (buf);

				//if (numChars <= 0) return -1;
			}

			return buf[curChar++];
		}

		// read an int
		public int iscan () throws IOException {
			int c = cscan (), sgn = 1;
			while (space (c)) c = cscan ();

			if (c == '-') {
				sgn = -1;
				c = cscan ();
			}

			int res = 0;

			do {
				//if (c < '0' || c > '9') throw new InputMismatchException ();

				res = (res << 1) + (res << 3);
				//res *= 10;
				res += c - '0';

				c = cscan ();
			}
			while (!space (c));

			return res * sgn;
		}

		// read a string
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

		// read a double
		public double dscan () throws IOException {
			int c = cscan (), sgn = 1;
			while (space (c)) c = cscan ();

			if (c == '-') {
				sgn = -1;
				c = cscan ();
			}

			double res = 0;

			while (!space (c) && c != '.') {
				if (c == 'e' || c == 'E') return res * UTILITIES.fast_pow (10, iscan ()); /*Math.pow (10, iscan ());*/
				//if (c < '0' || c > '9') throw new InputMismatchException ();

				//res = (res << 1) + (res << 3);
				res *= 10;
				res += c - '0';
				c = cscan ();
			}

			if (c == '.') {
				c = cscan ();
				double m = 1;

				while (!space (c)) {
					if (c == 'e' || c == 'E') return res * UTILITIES.fast_pow (10, iscan ()); /*Math.pow (10, iscan ()
					);*/
					//if (c < '0' || c > '9') throw new InputMismatchException ();

					m /= 10;
					res += (c - '0') * m;
					c = cscan ();
				}
			}

			return res * sgn;
		}

		// read a long
		public long lscan () throws IOException {
			int c = cscan (), sgn = 1;
			while (space (c)) c = cscan ();

			if (c == '-') {
				sgn = -1;
				c = cscan ();
			}

			long res = 0;

			do {
				//if (c < '0' || c > '9') throw new InputMismatchException();

				res = (res << 1) + (res << 3);
				//res *= 10;
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
