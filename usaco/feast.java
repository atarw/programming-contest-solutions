import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class feast {

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);
		UTILITIES util = UTILITIES.getInstance ();

		int T = in.iscan (), A = in.iscan (), B = in.iscan ();
		boolean[][] vis = new boolean[T + 1][2];

		Queue <State> q = new ArrayDeque <State> ();
		State curr = new State (0, 0);
		q.offer (curr);

		int max = 0;

		while (!q.isEmpty () && max < T) {
			curr = q.poll ();

			vis[curr.u][curr.w] = true;
			max = Math.max (max, curr.u);

			if (curr.w == 0) vis[curr.u][1] = true;

			if (curr.u + A <= T && !vis[curr.u + A][curr.w]) {
				vis[curr.u + A][curr.w] = true;
				q.offer (new State (curr.u + A, curr.w));
			}

			if (curr.u + B <= T && !vis[curr.u + B][curr.w]) {
				vis[curr.u + B][curr.w] = true;
				q.offer (new State (curr.u + B, curr.w));
			}

			if (curr.w == 0 && !vis[curr.u / 2][1]) {
				vis[curr.u / 2][1] = true;
				q.offer (new State (curr.u / 2, 1));
			}
		}

		out.print (max);
		out.close ();
	}

	private static class State {

		int u, w;

		public State (int u, int w) {
			this.u = u;
			this.w = w;
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
		private static UTILITIES util = new UTILITIES ();

		private UTILITIES () {}

		public static UTILITIES getInstance () {
			return util;
		}

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
