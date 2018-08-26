import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

// atharva washimkar
// August 11, 2018

public class CODEFORCES_994_C {

	// check if point m is inside rectangle (p1, p2, p3, p4)
	public static boolean inside (Point m, Point p1, Point p2, Point p3, Point p4) {
		Point p1p2 = new Point (p2.x - p1.x, p2.y - p1.y);
		Point p1m = new Point (m.x - p1.x, m.y - p1.y);

		Point p2p3 = new Point (p3.x - p2.x, p3.y - p2.y);
		Point p2m = new Point (m.x - p2.x, m.y - p2.y);

		int dotp1p2ANDp1m = dot (p1p2, p1m);
		int dotp1p2ANDp1p2 = dot (p1p2, p1p2);
		int dotp2p3ANDp2m = dot (p2p3, p2m);
		int dotp2p3ANDp2p3 = dot (p2p3, p2p3);

		return 0 <= dotp1p2ANDp1m && dotp1p2ANDp1m <= dotp1p2ANDp1p2
				       && 0 <= dotp2p3ANDp2m && dotp2p3ANDp2m <= dotp2p3ANDp2p3;
	}

	// check if lines (a, b) and (c, d) intersect (doesn't work if lines are colinear but that won't happen here)
	public static boolean intersect (Point a, Point b, Point c, Point d) {
		return ccw (a, c, d) != ccw (b, c, d) && ccw (a, b, c) != ccw (a, b, d);
	}

	public static boolean ccw (Point a, Point b, Point c) {
		return (c.y - a.y) * (b.x - a.x) > (b.y - a.y) * (c.x - a.x);
	}

	public static int dot (Point v1, Point v2) {
		return v1.x * v2.x + v1.y * v2.y;
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		Point[] s1 = new Point[4];

		for (int n = 0; n < 4; ++n)
			s1[n] = new Point (in.iscan (), in.iscan ());

		Point[] s2 = new Point[4];

		for (int n = 0; n < 4; ++n)
			s2[n] = new Point (in.iscan (), in.iscan ());

		boolean intersect = false;

		// check if points of s1 are in s2
		for (int n = 0; n < 4; ++n)
			intersect |= inside (s1[n], s2[0], s2[1], s2[2], s2[3]);

		// check if points of s2 are in s1
		for (int n = 0; n < 4; ++n)
			intersect |= inside (s2[n], s1[0], s1[1], s1[2], s1[3]);

		// check if sides of s1 intersect sides of s2

		for (int n = 0; n < 4; ++n)
			for (int n2 = 0; n2 < 4; ++n2)
				intersect |= intersect (s1[(n + 1) % 4], s1[n], s2[n2], s2[(n2 + 1) % 4]);

		out.print (intersect ? "YES" : "NO");
		out.close ();
	}

	private static class Point {

		int x, y;

		public Point (int x, int y) {
			this.x = x;
			this.y = y;
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