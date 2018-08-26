import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class TLE_16_P1_MATH_HELPER {

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int T = in.iscan ();

		for (int tt = 0; tt < T; ++tt) {
			in.sscan ();
			in.sscan ();
			String eqn = in.sscan ();

			t = eqn.split ("");

			//out.print (eqn + " ");
			//out.println (Arrays.toString (t));
			//out.flush ();

			long a = 0L, b = 0L;
			String ans = "";
			boolean neg = false;
			int i = 0;

			for (; i < t.length; ++i) {
				if (t[i].equals ("x"))
					break;
				else if (t[i].equals ("-"))
					neg = true;
				else {
					a *= 10L;
					a += Long.parseLong (t[i]);
				}
			}

			if (a == 0L && (t[0].equals ("x") || neg))
				a = 1L;

			if (neg)
				a *= -1L;

			++i;

			while (i < t.length && t[i].equals ("^"))
				++i;

			neg = false;

			for (; i < t.length; ++i) {
				if (t[i].equals ("-"))
					neg = true;
				else {
					b *= 10L;
					b += Long.parseLong (t[i]);
				}
			}

			if (eqn.indexOf ("x") == -1)
				b = 0;
			else if (eqn.indexOf ("^") == -1)
				b = 1;

			if (neg)
				b *= -1L;

			//out.println ("a: " + a + " b: " + b);
			long k = a * b, n = b - 1L;

			if (k == 0L) {
				ans = "0";
			}
			else if (n == 0L) {
				ans = k + "";
			}
			else if (k == 1L && n != 0L && n != 1L) {
				ans = "x^" + n;
			}
			else if (k == -1L && n != 0L && n != 1L) {
				ans = "-x^" + n;
			}
			else if (n == 1L && k != 0L && k != 1L) {
				ans = k + "x";
			}
			else if (n == 1L && k == 1L) {
				ans = "x";
			}
			else if (k == -1L && n == 1L) {
				ans = "-x";
			}
			else {
				ans = k + "x^" + n;
			}

			out.println ("y' = " + ans);
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