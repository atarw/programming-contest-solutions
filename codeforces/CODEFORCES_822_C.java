import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// atharva washimkar
// Jul 03, 2017

public class CODEFORCES_822_C {

	public static int bsearch (List<Voucher> list, int l, int r, int k) {
		if (l == r)
			return list.get (l).l > k ? l : -1;

		int mid = (l + r) / 2;

		if (list.get (mid).l > k)
			return bsearch (list, l, mid, k);

		return bsearch (list, mid + 1, r, k);
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan (), X = in.iscan ();
		Voucher[] arr = new Voucher[N];

		for (int n = 0; n < N; ++n)
			arr[n] = new Voucher (in.iscan (), in.iscan (), in.iscan ());

		Arrays.sort (arr);

		List<Voucher>[] list = new ArrayList[X + 1];

		for (int x = 0; x <= X; ++x)
			list[x] = new ArrayList<Voucher> ();

		for (int n = 0; n < N; ++n)
			if (arr[n].r - arr[n].l + 1 <= X)
				list[arr[n].r - arr[n].l + 1].add (arr[n]);

		for (int x = 0; x <= X; ++x)
			Collections.sort (list[x]);

		int[][] min = new int[X + 1][];

		for (int x = 0; x <= X; ++x) {
			if (!list[x].isEmpty ()) {
				min[x] = new int[list[x].size ()];
				min[x][list[x].size () - 1] = list[x].get (list[x].size () - 1).c;

				for (int n = list[x].size () - 2; n >= 0; --n)
					min[x][n] = Math.min (min[x][n + 1], list[x].get (n).c);
			}
		}

		long min_cost = 1L << 50;

		for (int n = 0; n < N; ++n) {
			int dur = arr[n].r - arr[n].l + 1;

			if (dur <= X && !list[X - dur].isEmpty ()) {
				int idx = bsearch (list[X - dur], 0, list[X - dur].size () - 1, arr[n].r);

				if (idx != -1)
					min_cost = Math.min (min_cost, arr[n].c + min[X - dur][idx]);
			}
		}

		if (min_cost != 1L << 50)
			out.print (min_cost);
		else
			out.print (-1);

		out.close ();
	}

	private static class Voucher implements Comparable<Voucher> {

		int l, r, c;

		public String toString () {
			return "{" + l + ", " + r + " = " + c + "}";
		}

		public int compareTo (Voucher v) {
			if (this.l == v.l)
				return Integer.compare (v.c, this.c);

			return Integer.compare (this.l, v.l);
		}

		public Voucher (int l, int r, int c) {
			this.l = l; this.r = r; this.c = c;
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