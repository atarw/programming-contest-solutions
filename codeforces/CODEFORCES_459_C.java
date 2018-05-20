import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

// atharva washimkar
// May 19, 2018

public class CODEFORCES_459_C {

	static int N, K, D;
	static int done;

	public static void dfs (Node cur, int d) {
		if (done >= N || d == D - 1) {
			++done;
		}
		else {
			for (int k = 0; k < K && done < N; ++k) {
				Node nxt = new Node (k);
				cur.list.add (nxt);
				dfs (nxt, d + 1);
			}
		}
	}

	public static void printdfs (Node cur, int d, int[] seating) {
		seating[d] = cur.v + 1;
		int i = 0;

		for (; i < cur.list.size (); ++i) {
			if (!cur.list.get (i).done) {
				printdfs (cur.list.get (i), d + 1, seating);
				break;
			}
		}

		cur.done = true;

		for (int j = 0; j < cur.list.size (); ++j)
			cur.done &= cur.list.get (j).done;
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		N = in.iscan ();
		K = Math.min (in.iscan (), N);
		D = in.iscan ();

		Node[] nodes = new Node[K];

		for (int k = 0; k < K; ++k) {
			nodes[k] = new Node (k);
			dfs (nodes[k], 0);
		}

		if (done < N) {
			out.print (-1);
		}
		else {
			int[][] seating = new int[N][D];

			for (int n = 0, k = 0; n < N && k < K; ++n) {
				printdfs (nodes[k], 0, seating[n]);

				if (nodes[k].done)
					++k;
			}

			for (int d = 0; d < D; ++d) {
				for (int n = 0; n < N; ++n)
					out.print (seating[n][d] + " ");

				out.println ();
			}
		}

		out.close ();
	}

	private static class Node {

		int v;
		List<Node> list;
		boolean done;

		public Node (int v) {
			this.v = v;
			this.list = new ArrayList<Node> ();
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