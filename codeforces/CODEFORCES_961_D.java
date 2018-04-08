import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

// atharva washimkar
// Apr 04, 2018

public class CODEFORCES_961_D {

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan ();
		long[] x = new long[N], y = new long[N];

		for (int n = 0; n < N; ++n) {
			x[n] = in.lscan ();
			y[n] = in.lscan ();
		}

		// check if only one line is needed
		Set<Fraction> set = new HashSet<Fraction> ();

		for (int n = 1; n < N; ++n)
			set.add (new Fraction (n, y[n] - y[0], x[n] - x[0]));

		boolean good = false;

		if (set.size () <= 1) {
			good = true;
		}
		else {
			// there exists two points i and j such that point 0 doesn't have the same slope with them
			int cnt = 0;
			Fraction[] arr = new Fraction[2];

			for (Fraction f : set) {
				if (cnt == 2)
					break;

				arr[cnt] = f;
				++cnt;
			}

			// check if point arr[0].n and arr[1].n are on two different lines
			// assume point 0 is on a line with point arr[i].n (slope is arr[i])

			for (int i = 0; i < 2 && !good; ++i) {
				Set<Integer> other = new HashSet<Integer> ();
				int pivot = -1;

				for (int n = 1; n < N; ++n) {
					if (n == arr[i].n)
						continue;

					Fraction slope = new Fraction (n, y[n] - y[0], x[n] - x[0]);

					if (!slope.equals (arr[i])) {
						other.add (n);
						pivot = n;
					}
				}

				// all points in other must be on the same line
				Set<Fraction> set2 = new HashSet<Fraction> ();

				for (int n : other) {
					if (pivot == n)
						continue;

					Fraction f = new Fraction (n, y[n] - y[pivot], x[n] - x[pivot]);
					set2.add (f);
				}

				if (set2.size () <= 1)
					good = true;
			}

			// check if points arr[0].n and arr[1].n are on same line, and point 0 on different line
			Fraction slope = new Fraction (-1, y[arr[0].n] - y[arr[1].n], x[arr[0].n] - x[arr[1].n]);
			Set<Integer> other = new HashSet<Integer> ();

			for (int n = 1; n < N; ++n) {
				if (n == arr[0].n || n == arr[1].n)
					continue;

				Fraction f = new Fraction (n, y[arr[0].n] - y[n], x[arr[0].n] - x[n]);

				if (!f.equals (slope))
					other.add (n);
			}

			// all in other2 must be on same line
			int piv = 0;

			Set<Fraction> set2 = new HashSet<Fraction> ();

			for (int n : other) {
				Fraction f = new Fraction (-1, y[piv] - y[n], x[piv] - x[n]);
				set2.add (f);
			}

			if (set2.size () <= 1)
				good = true;
		}

		if (good)
			out.print ("YES");
		else
			out.print ("NO");

		out.close ();
	}

	private static class Fraction {

		int n;
		long a, b;

		@Override
		public String toString () {
			return String.format ("%d: [%d / %d]", n, a, b);
		}

		@Override
		public boolean equals (Object o) {
			if (this == o) return true;
			if (o == null || getClass () != o.getClass ()) return false;

			Fraction fraction = (Fraction) o;

			if (a != fraction.a) return false;
			return b == fraction.b;
		}

		@Override
		public int hashCode () {
			int result = (int) (a ^ (a >>> 32));
			result = 31 * result + (int) (b ^ (b >>> 32));
			return result;
		}

		public Fraction (int n, long a, long b) {
			this.n = n;
			this.a = a;
			this.b = b;

			if (b == 0) {
				this.a = 1;
			}
			else {
				long gcd = UTILITIES.gcd (a, b);
				this.a /= gcd;
				this.b /= gcd;
			}
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

		public static long gcd (long a, long b) {
			return b == 0L ? a : gcd (b, a % b);
		}

		public static long lcm (long a, long b) {
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